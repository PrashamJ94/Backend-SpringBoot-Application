package com.example.hedgenet_backend.Repository;

import com.example.hedgenet_backend.Entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TradeRepo extends JpaRepository<Trade, UUID>
{
    public List<Trade> findByUser_UsernameAndFund_Fundname(String username, String fundname);
}
