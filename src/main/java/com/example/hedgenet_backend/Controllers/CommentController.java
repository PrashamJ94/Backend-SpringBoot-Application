package com.example.hedgenet_backend.Controllers;

import com.example.hedgenet_backend.Entity.Comment;
import com.example.hedgenet_backend.Entity.Post;
import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Models.NewComment;
import com.example.hedgenet_backend.Repository.CommentRepo;
import com.example.hedgenet_backend.Repository.PostRepo;
import com.example.hedgenet_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Comments")
@CrossOrigin(origins={"http://20.82.224.21:3000/","http://localhost:3000"})
public class CommentController
{
    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommentRepo commentRepo;

    @PostMapping("/createComment")
    public String createComment(@RequestBody NewComment newComment)
    {
        String content= newComment.getContent();
        String id=newComment.getPostid();
        UUID postid=UUID.fromString(id);

        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userRes=userRepo.findByUsername(username);
        Optional<Post> postRes=postRepo.findById(postid);

        Comment comment=new Comment();

        if(userRes!=null && postRes!=null)
        {
            User user=userRes.get();
            Post post=postRes.get();

            comment.setContent(content);
            comment.setPost(post);
            comment.setUser(user);

            commentRepo.save(comment);
        }

        return "Comment Created";
    }

    @GetMapping("/getComments")
    public Map<String,List<Comment>> getComments(@RequestParam String id)
    {
        UUID postid=UUID.fromString(id);
        List<Comment> comment= (List<Comment>) commentRepo.findByPost_Postid(postid);
        return Collections.singletonMap("Comments",comment);

    }
}
