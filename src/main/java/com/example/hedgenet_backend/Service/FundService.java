package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;

import java.io.IOException;
import java.util.List;

public interface FundService
{
    public void createFund(FundEntity fund) throws IOException;

    public List<FundEntity> getFundsJoinedByUser();

    public FundEntity getFund(String fundname) throws Exception;

    public List<FundEntity> getAllFunds();

    public FundEntity editFundName(String fundname,String newname) throws Exception;

    public FundEntity editFundDesc(String fundname, String desc) throws Exception;

    public void deleteFund(String fundname) throws Exception;

    public void joinFundByName(String fundname);

    public void leaveFundByName(String fundname);

    public List<FundEntity> getLeaderBoardByWPL();

    public List<FundEntity> getLeaderBoardByOPL();

    public FundEntity searchFund(String fundname);

}
