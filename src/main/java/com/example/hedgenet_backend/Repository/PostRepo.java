package com.example.hedgenet_backend.Repository;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepo extends JpaRepository<Post, UUID>
{
    public List<Post> findByFund_Fundname(String fundname);
}
