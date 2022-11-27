package com.example.hedgenet_backend.Repository;

import com.example.hedgenet_backend.Entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<StockEntity,String>
{
    public StockEntity findByTickerId(String tickerid);


}
