package com.example.hedgenet_backend.Models;

import java.util.UUID;

public class NewComment
{
    private String content;
    private String postid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
