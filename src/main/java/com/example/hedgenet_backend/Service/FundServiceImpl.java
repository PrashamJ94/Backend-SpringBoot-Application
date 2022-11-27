package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Repository.FundRepo;
import com.example.hedgenet_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class FundServiceImpl implements FundService{

    @Autowired private FundRepo fundRepo;
    @Autowired private UserRepo userRepo;

    @Autowired private RefreshService refreshService;

    @Override
    public void createFund(FundEntity fund) throws IOException
    {
        String user_name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user=userRepo.findByUsername(user_name);

        String fundname=fund.getFundname();


        if(fundRepo.findByFundname(fundname)==null){
        UUID user_id=user.get().getUser_id();
        fund.addUser(user_id);
        fundRepo.save(fund);

        UUID fund_id=fund.getId();
        user.get().addFundId(fund_id);
        userRepo.save(user.get());}
        else
        {
            throw new IOException();
        }
    }



    @Override
    public List<FundEntity> getFundsJoinedByUser()
    {
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user=userRepo.findByUsername(username);
        List<FundEntity> funds=new ArrayList<FundEntity>();
        if (user!=null)
        {
            List<UUID> funds_joined = user.get().getFundsjoined();
            for (UUID id : funds_joined)
            {
                Optional<FundEntity> fundres = fundRepo.findById(id);
                if (fundres.isPresent())
                {
                    FundEntity fund = fundres.get();
                    funds.add(fund);
                }

            }
            return funds;
        }

        return null;
    }

    public FundEntity searchFund(String fundname)
    {
        FundEntity fund=fundRepo.findByFundname(fundname);
        return fund;
    }
    @Override
    public FundEntity getFund(String fundname) throws Exception {
       FundEntity fund= fundRepo.findByFundname(fundname);
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> res=userRepo.findByUsername(username);
        if(res!=null)
        {
            User user=res.get();
            UUID user_id=user.getUser_id();
            if(fund.checkUser(user_id))
            {
                System.out.println("here at null fund");
                return null;
            }
        }

        return fund;
    }

    @Override
    public List<FundEntity> getAllFunds()
    {
        return (List<FundEntity>) fundRepo.findAll();
    }

    @Override
    public FundEntity editFundName(String fundname,String newname) throws Exception {
        FundEntity funds= fundRepo.findByFundname(fundname);


        FundEntity fund=new FundEntity();
        return fundRepo.save(fund);
    }

    @Override public FundEntity editFundDesc(String fundname, String desc) throws Exception
    {

        FundEntity fund=new FundEntity();
        fund.setFund_desc(desc);
        return fundRepo.save(fund);
    }


    @Override
    public void deleteFund(String fundname) throws Exception {


        FundEntity fund=new FundEntity();
        fundRepo.deleteAllById(Collections.singleton(fund.getId()));
    }

    @Override
    public void joinFundByName(String fundname)
    {
        FundEntity fund=fundRepo.findByFundname(fundname);

        String user_name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user=userRepo.findByUsername(user_name);

        UUID user_id=user.get().getUser_id();
        fund.addUser(user_id);
        fundRepo.save(fund);

        UUID fund_id=fund.getId();
        user.get().addFundId(fund_id);
        userRepo.save(user.get());
    }

    @Override
    public void leaveFundByName(String fundname)
    {
        FundEntity fund=fundRepo.findByFundname(fundname);

        String user_name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user=userRepo.findByUsername(user_name);

        UUID user_id=user.get().getUser_id();
        fund.removeUser(user_id);
        fundRepo.save(fund);

        UUID fund_id=fund.getId();
        user.get().removeFundId(fund_id);
        userRepo.save(user.get());
    }

    @Override
    public List<FundEntity> getLeaderBoardByWPL()
    {
        List<FundEntity> funds=getAllFunds();
        Collections.sort(funds,new sortFundsWPL());
        return funds;
    }

    @Override
    public List<FundEntity> getLeaderBoardByOPL()
    {
        List<FundEntity> funds=getAllFunds();
        Collections.sort(funds,new sortFundsOPL());
        return funds;
    }
}
