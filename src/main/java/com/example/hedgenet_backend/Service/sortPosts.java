package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.Post;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Comparator;

public class sortPosts implements Comparator<Post>
{

    @Override
    public int compare(Post post1, Post post2)
    {
        Timestamp t1=post1.getTimestamp();
        Timestamp t2=post2.getTimestamp();

        return t2.compareTo(t1);
    }
}
