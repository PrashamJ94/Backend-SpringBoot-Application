package com.example.hedgenet_backend.Repository;

import com.example.hedgenet_backend.Entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FundRepo extends JpaRepository<FundEntity, UUID>
{
    public FundEntity findByFundname(String fundname);

    public List<FundEntity> findAllByFundname(String fundname);

    public void deleteByFundname(String fundname);
}
