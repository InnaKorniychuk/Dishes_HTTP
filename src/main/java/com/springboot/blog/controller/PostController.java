package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
         return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //create blog post rest api
    @PostMapping("/{id}")
    public ResponseEntity<PostDto> createPostId(@RequestBody PostDto postDto){
        //return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
            PostDto postResponse= postService.createPost(postDto);
            return new ResponseEntity<>(postResponse,HttpStatus.OK);

    }
    //get all posts rest api
    @GetMapping
    public List<PostDto> getAllPosts(){
        return  postService.getAllPosts();
    }

    //get post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //get post by id rest api
    @GetMapping("/find/{id}")
    public ResponseEntity<List<PostDto>>getById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.findById(id));
    }
    //update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){
       PostDto postResponse= postService.updatePost(postDto,id);
       return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    //delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
    }

    /*  @PostMapping
    public Post getDish(@RequestParam Long id, Map<Long, Object> model){
        postService.f
    }*/

    /* @PostMapping("filter")
    public String getDish(@RequestParam Long id, Map<Long, Object> model){
       List<PostDto> posts=postService.findById(id);
        model.put(id,posts);
        return  "main";
    }*/
}
