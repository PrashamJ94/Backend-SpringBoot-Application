package com.example.hedgenet_backend.Entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="comment")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="comment_id")
    private UUID commentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "comment_content")
    String content;

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
