package com.example.hedgenet_backend.Controllers;


import com.example.hedgenet_backend.Entity.Post;

import com.example.hedgenet_backend.Models.NewPost;

import com.example.hedgenet_backend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Posts")
@CrossOrigin(origins={"http://20.82.224.21:3000/","http://localhost:3000"})
public class PostController
{
    @Autowired
    PostService postService;

    @PostMapping("/createPost")
    public String createPost(@RequestBody NewPost post)
    {
        postService.create(post);
        return "Post Successfully created";
    }


    @GetMapping("/getFundPosts")
    public Map<String,List<Post>> getPosts(@RequestParam String fundname) {
        List<Post> posts = postService.getFundPosts(fundname);
        return Collections.singletonMap("Posts", posts);
    }

    @GetMapping("/newsFeed")
    public Map<String,List<Post>> getNewsFeed()
    {
        List<Post> newsfeedPosts=postService.getNewsFeedPosts();
        return Collections.singletonMap("Newsfeed",newsfeedPosts );
    }
}
