package com.example.hedgenet_backend.Models;

import java.util.UUID;

public class NewPost {
    private String postContent;
    private UUID fundid;

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public UUID getFundid() {
        return fundid;
    }

    public void setFundid(UUID fundid) {
        this.fundid = fundid;
    }
}
