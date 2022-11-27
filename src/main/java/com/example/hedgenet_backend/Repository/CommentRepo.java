package com.example.hedgenet_backend.Repository;

import com.example.hedgenet_backend.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface CommentRepo extends JpaRepository<Comment, UUID>
{
    public List<Comment> findByPost_Postid(UUID postid);
}
