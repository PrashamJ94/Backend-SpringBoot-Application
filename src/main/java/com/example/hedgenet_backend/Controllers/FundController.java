package com.example.hedgenet_backend.Controllers;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Repository.FundRepo;
import com.example.hedgenet_backend.Repository.UserRepo;
import com.example.hedgenet_backend.Service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins={"http://20.82.224.21:3000/","http://localhost:3000"})

@RequestMapping("/Funds")
public class FundController {

    @Autowired private FundService fundService;


    @PostMapping("/createFund")
    public String create(@RequestBody FundEntity fund)
    {
        try{
        fundService.createFund(fund);
        return "Fund Created Successfully";
        }
        catch(IOException e)
        {
            System.out.println("Fund already exists");
        }

        return null;
    }

    //endpoint to get leaderboard by weeklyPL
    @GetMapping("/getLeaderboardByWPL")
    public List<FundEntity> leaderboardByWPL()
    {
        List<FundEntity> funds=fundService.getLeaderBoardByWPL();
        return funds;
    }

    //endpoint to get leaderboard by overallPL
    @GetMapping("/getLeaderboardByOPL")
    public List<FundEntity> leaderboardByOPL()
    {
        List<FundEntity> funds=fundService.getLeaderBoardByOPL();
        return funds;
    }

    @GetMapping("/getAll")
    public List<FundEntity> getAllFunds()
    {
        return fundService.getAllFunds();
    }

    @GetMapping("/getUserJoinedFund")
    public Map<String,List<FundEntity>> getUserJoinedFunds()
    {
          List<FundEntity> funds=fundService.getFundsJoinedByUser();
          return Collections.singletonMap("funds",funds);
    }

    @GetMapping("/getFund")
    public Map<String,FundEntity> getFundByFundname(@RequestParam String fundname)
    {
        try {
            FundEntity fund=fundService.getFund(fundname);
            return Collections.singletonMap("Fund",fund);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/searchFund")
    public Map<String,List<FundEntity>> search(@RequestParam String fundname)
    {

        try {
            FundEntity fund= fundService.getFund(fundname);
            if(fund==null)
            {
                return null;
            }
            List<FundEntity> funds=new ArrayList<FundEntity>();
            funds.add(fund);
            return Collections.singletonMap("fund",funds);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/joinFund")
    public String joinFund(@RequestParam String fundname)
    {
        fundService.joinFundByName(fundname);
        return "Fund Joined";
    }

    @GetMapping("/leaveFund")
    public String leaveFund(@RequestParam String fundname)
    {
        fundService.leaveFundByName(fundname);
        return "Fund Left";
    }


    @PutMapping("/updateFundName")
    public String updateFundName(@RequestParam String fundname,@RequestParam String newname)
    {
        try {
            fundService.editFundName(fundname,newname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Fund Name Updated";
    }

    @PutMapping("/updateFundDescription")
    public String updateFundDesc(@RequestParam String name, @RequestParam String desc)
    {
        try {
            fundService.editFundDesc(name,desc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Fund Description Updated";
    }

    @DeleteMapping("/deleteFund")
    public String delete(@RequestParam String fundname) throws Exception
    {
        fundService.deleteFund(fundname);
        return "Fund:"+fundname+" Deleted Successfully";
    }

}
