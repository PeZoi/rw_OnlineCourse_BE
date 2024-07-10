package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Blog;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.blog.BlogRequest;
import com.example.backend_rw.entity.dto.blog.BlogResponse;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.BlogRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.BlogService;
import com.example.backend_rw.utils.UploadFile;
import com.example.backend_rw.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    private final ModelMapper modelMapper;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final UploadFile uploadFile;

    public BlogServiceImpl(ModelMapper modelMapper, BlogRepository blogRepository, UserRepository userRepository, UploadFile uploadFile) {
        this.modelMapper = modelMapper;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.uploadFile = uploadFile;
    }

    @Override
    public BlogResponse save(BlogRequest blogRequest, MultipartFile img) {
        User user = userRepository.findById(blogRequest.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        if(blogRepository.existsBlogByTitle(blogRequest.getTitle())){
            throw new CustomException( "Tên blog này đã tồn tại!", HttpStatus.CONFLICT);
        }

        Blog blog = modelMapper.map(blogRequest, Blog.class);
        blog.setUser(user);
        blog.setCreatedAt(Instant.now());

        String slug = Utils.removeVietnameseAccents(blogRequest.getTitle());
        if(blogRepository.existsBlogBySlug(slug)){
            throw new CustomException( "Vui lòng thay đổi tên blog: Tên Slug bị trùng", HttpStatus.CONFLICT);
        }
        blog.setSlug(slug);

        String thumbnail = uploadFile.uploadFileOnCloudinary(img);
        blog.setThumbnail(thumbnail);

        Blog savedBlog = blogRepository.save(blog);

        return convertToBlogResponse(savedBlog);
    }

    @Override
    public List<BlogResponse> getAll() {
        return blogRepository.findAll().stream().map(this::convertToBlogResponse).toList();
    }

    @Override
    public List<BlogResponse> getAllByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        List<Blog> listBlogs = blogRepository.findAllByUser(user);
        return listBlogs.stream().map(this::convertToBlogResponse).toList();
    }

    @Override
    public BlogResponse get(String slug) {
        Blog blog = blogRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Slug không tồn tại"));
        return convertToBlogResponse(blog);
    }

    @Override
    public String view(Integer blogId) {
        Blog blogInDB = blogRepository.findById(blogId)
                .orElseThrow(() -> new NotFoundException("Blog ID không tồn tại"));
        int view = blogInDB.getView();

        blogInDB.setView(++view);

        blogRepository.save(blogInDB);

        return "Cập nhật lượt xem thành công";
    }

    private BlogResponse convertToBlogResponse(Blog savedBlog) {
        BlogResponse response = modelMapper.map(savedBlog, BlogResponse.class);
        Instant now = Instant.now();
        response.setCreatedAtFormat(Utils.formatDuration(Duration.between(savedBlog.getCreatedAt(), now)));
        response.setUsername(savedBlog.getUser().getUsername());
        response.setAvatarUser(savedBlog.getUser().getPhoto());
        response.setCreatedAt(savedBlog.getCreatedAt().toString());

        return response;
    }
}
