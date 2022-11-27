package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;

import java.util.Comparator;

public class sortFundsWPL implements Comparator<FundEntity>
{
    @Override
    public int compare(FundEntity f1,FundEntity f2)
    {
        float weeklyPL1=f1.getWeeklyPl();
        float weeklyPL2=f2.getWeeklyPl();

       return ((int)weeklyPL2-(int)weeklyPL1);
    }
}
