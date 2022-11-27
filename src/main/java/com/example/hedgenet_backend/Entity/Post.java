package com.example.hedgenet_backend.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private UUID postid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fundid")
    private FundEntity fund;

    @CreationTimestamp
    @Column(name = "Time")
    private Timestamp timestamp;

    @Column(name = "post_content")
    private String postContent;


    public UUID getId() {
        return postid;
    }

    public String getPostContent() {
        return postContent;
    }

    public User getUser() {
        return user;
    }

    public FundEntity getFund() {
        return fund;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean within24hrs()
    {
        long day=24*60*60*1000;
        return this.timestamp.getTime()>(System.currentTimeMillis()-day);
    }

}
