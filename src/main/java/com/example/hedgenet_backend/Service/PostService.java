package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.Post;
import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Models.NewPost;
import com.example.hedgenet_backend.Repository.FundRepo;
import com.example.hedgenet_backend.Repository.PostRepo;
import com.example.hedgenet_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    @Autowired private PostRepo postRepo;
    @Autowired private FundRepo fundRepo;
    @Autowired private UserRepo userRepo;


    public void create(NewPost newpost)
    {
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID fundid=newpost.getFundid();
        String postcontent= newpost.getPostContent();

        Post post=new Post();

        Optional<User> userRes=userRepo.findByUsername(username);
        Optional<FundEntity> fundRes=fundRepo.findById(fundid);

        if(userRes!=null && fundRes!=null)
        {
            FundEntity fund=fundRes.get();
            User user=userRes.get();

            post.setPostContent(postcontent);
            post.setFund(fund);
            post.setUser(user);

             postRepo.save(post);
        }

    }

    public List<Post> getFundPosts(String fundname)
    {
        List<Post> posts=postRepo.findByFund_Fundname(fundname);
        Collections.sort(posts,new sortPosts());
        return posts;
    }

    public List<Post> getNewsFeedPosts()
    {
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userRes=userRepo.findByUsername(username);
        List<Post> posts=new ArrayList<Post>();

        if(userRes!=null)
        {
            User user=userRes.get();
            List<UUID> fundsjoined=user.getFundsjoined();
            for(UUID id: fundsjoined)
            {
                Optional<FundEntity> fundRes=fundRepo.findById(id);
                if(fundRes!=null)
                {
                    FundEntity fund=fundRes.get();
                    String fundname=fund.getFundname();
                    List<Post> fundPosts=postRepo.findByFund_Fundname(fundname);
                    for(Post post:fundPosts)
                    {
                        if(post.within24hrs()==true)
                        posts.add(post);

                    }
                }

            }
        }
        Collections.sort(posts,new sortPosts());
        return posts;
    }
}
