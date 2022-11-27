package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.Trade;
import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Models.NewTrade;
import com.example.hedgenet_backend.Repository.FundRepo;
import com.example.hedgenet_backend.Repository.StockRepo;
import com.example.hedgenet_backend.Repository.TradeRepo;
import com.example.hedgenet_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TradeService
{

    @Autowired
    UserRepo userRepo;

    @Autowired
    FundRepo fundRepo;

    @Autowired
    TradeRepo tradeRepo;

    @Autowired
    StockRepo stockRepo;


    public void createNewTrade(NewTrade newtrade)
    {
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ticker_id=newtrade.getTickerid();
        UUID fundid=newtrade.getFundid();
        float price=newtrade.getPrice();
        int quantity=newtrade.getQuantity();

        float amount=price*Math.abs(quantity);

        Optional<User> userRes=userRepo.findByUsername(username);
        Optional<FundEntity> fundRes=fundRepo.findById(fundid);

        Trade trade=new Trade();
        trade.setPrice(price);
        trade.setTickerid(ticker_id);
        trade.setQuantity(quantity);


        if(userRes!=null && fundRes!=null)
        {
            User user=userRes.get();
            FundEntity fund=fundRes.get();
            boolean res=alterAccountBalance(user,amount);
            if (res==false)
            {
                return;
            }

            trade.setUser(user);
            trade.setFund(fund);
            userRepo.save(user);
            tradeRepo.save(trade);

        }
    }

    private boolean alterAccountBalance(User user,float amount)
    {
        double acc_balance= user.getAccountBal();
        if (acc_balance>amount)
        {acc_balance=acc_balance-amount;
        user.setAccountBal(acc_balance);
        userRepo.save(user);
        return true;
        }

        return false;
    }

    public List<Trade> getTrades(String fundname)
    {
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Trade> trades=tradeRepo.findByUser_UsernameAndFund_Fundname(username,fundname);
        for(Trade trade:trades)
        {
            String tickerid=trade.getTickerid();
            double currentPrice=stockRepo.findByTickerId(tickerid).getPrice();
            double purchasePrice=trade.getPrice();
            int quantity=trade.getQuantity();
            float personalPl=(float) ((currentPrice-purchasePrice)*quantity);
            personalPl= (float) (Math.round(personalPl * 100.0) /100.0);
            trade.setPersonalPL(personalPl);
            tradeRepo.save(trade);
        }

        return trades;
    }

    public void closeTrade(String tradeid)
    {
        String username= (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Trade> traderes=tradeRepo.findById(UUID.fromString(tradeid));
        Optional<User> userRes=userRepo.findByUsername(username);
        if(traderes!=null && userRes!=null)
        {
            Trade trade=traderes.get();
            User user=userRes.get();

            FundEntity fund= fundRepo.findByFundname(trade.getFund().getFundname());
            float weeklyPl=fund.getWeeklyPl();
            float overallPl=fund.getOverallPL();
            double accountBalance= user.getAccountBal();
            int quantity=trade.getQuantity();
            float price=trade.getPrice();
            float amount=price*Math.abs(quantity);

            float personalPl=trade.getPersonalPL();
            weeklyPl=weeklyPl+personalPl;
            overallPl=overallPl+personalPl;
            fund.setWeeklyPl(weeklyPl);
            fund.setOverallPL(overallPl);


            accountBalance=accountBalance+amount+personalPl;
            user.setAccountBal(accountBalance);
            tradeRepo.delete(trade);
            userRepo.save(user);

            fundRepo.save(fund);
        }

    }
}
