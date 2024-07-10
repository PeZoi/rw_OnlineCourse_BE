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
}
