package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.blog.BlogRequest;
import com.example.backend_rw.entity.dto.blog.BlogResponse;
import com.example.backend_rw.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> listAll(){
        List<BlogResponse> listBlogs = blogService.getAll();
        if(listBlogs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listBlogs);
    }

    @GetMapping("/get-all/user/{id}")
    public ResponseEntity<?> listAllByUser(@PathVariable(value = "id") Integer userId){
        List<BlogResponse> listBlogs = blogService.getAllByUser(userId);
        if(listBlogs.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listBlogs);
    }

    @GetMapping("/get/{slug}")
    public  ResponseEntity<?> get(@PathVariable(value = "slug") String slug){
        return ResponseEntity.ok(blogService.get(slug));
    }


    @PutMapping("/update/view/{id}")
    public ResponseEntity<?> view(@PathVariable(value = "id") Integer blogId){
        return ResponseEntity.ok(blogService.view(blogId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestPart(value = "blog") @Valid BlogRequest blogRequest,
                                  @RequestParam(value = "img") MultipartFile img){
        return new ResponseEntity<>(blogService.save(blogRequest, img), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer blogId,
                                    @RequestPart(value = "blog") @Valid BlogRequest blogRequest,
                                    @RequestParam(value = "img", required = false) MultipartFile img) {
        return ResponseEntity.ok(blogService.update(blogId, blogRequest, img));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer blogId){
        return ResponseEntity.ok(blogService.delete(blogId));
    }

    @GetMapping("/check-author")
    public ResponseEntity<?> checkAuthor(@RequestParam(value = "user") Integer userId,
                                         @RequestParam(value = "blog") Integer blogId){
        return ResponseEntity.ok(blogService.checkAuthorOfBlog(blogId, userId));
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "keyword") String keyword){
        List<BlogResponse> listBlogs = blogService.search(keyword);
        if(listBlogs.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listBlogs);
    }
}
