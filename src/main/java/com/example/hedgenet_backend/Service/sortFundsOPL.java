package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;

import java.util.Comparator;

public class sortFundsOPL implements Comparator<FundEntity>
{
    @Override
    public int compare(FundEntity f1, FundEntity f2)
    {
        float opl1=f1.getOverallPL();
        float opl2=f2.getOverallPL();

        return (int)opl2-(int)opl1;
    }
}
