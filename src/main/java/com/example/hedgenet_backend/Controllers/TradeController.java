package com.example.hedgenet_backend.Controllers;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.Trade;
import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Models.NewTrade;
import com.example.hedgenet_backend.Repository.FundRepo;
import com.example.hedgenet_backend.Repository.StockRepo;
import com.example.hedgenet_backend.Repository.TradeRepo;
import com.example.hedgenet_backend.Repository.UserRepo;
import com.example.hedgenet_backend.Service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Trades")
@CrossOrigin(origins={"http://20.82.224.21:3000/","http://localhost:3000"})
public class TradeController
{
    @Autowired
    TradeService tradeService;

    @PostMapping("/createNewTrade")
    public String createTrade(@RequestBody NewTrade newtrade)
    {
        tradeService.createNewTrade(newtrade);
        return "Trade created";
    }

    @GetMapping("/getOpenPositions")
    public Map<String,List<Trade>> getOpenPositions(@RequestParam String fundname)
    {
        List<Trade> trades=tradeService.getTrades(fundname);
        return Collections.singletonMap("trades",trades);
    }

    @GetMapping("/closeTrade")
    public String closeTrade(@RequestParam String tradeid)
    {

        tradeService.closeTrade(tradeid);
        return "Trade Closed";
    }

}
