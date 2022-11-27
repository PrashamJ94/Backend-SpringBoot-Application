package com.example.hedgenet_backend.Entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="funds")
public class FundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID fundid;


    private String fundname;

    private String fund_desc;

    @Column(name = "focus_areas")
    private String focus_areas;

   @Column(name="weekly_pl")
   private float weeklyPl;

   @Column(name="overall_pl")
   private float overallPL;

    public float getWeeklyPl() {
        return weeklyPl;
    }

    public void setWeeklyPl(float weeklyPl) {
        this.weeklyPl = weeklyPl;
    }

    public float getOverallPL() {
        return overallPL;
    }

    public void setOverallPL(float overallPL) {
        this.overallPL = overallPL;
    }

    @ElementCollection
    private List<UUID> fundUsers=new ArrayList<UUID>();

    @ElementCollection
    private List<UUID> fundstocks;

    public String getFundname()
    {
        return fundname;
    }

    public String getFund_desc()
    {
        return fund_desc;
    }

    public UUID getId() {
        return fundid;
    }

    public void setFundname(String fundname)
    {
        this.fundname=fundname;
    }

    public void setFund_desc(String fund_desc)
    {
        this.fund_desc=fund_desc;
    }

    public void setFocus_areas(String focus_areas){this.focus_areas=focus_areas;}

    public String getFocus_areas(){return focus_areas;}

    public List<UUID> getFundUsers()
    {
        return fundUsers;
    }

    public void addUser(UUID id)
    {
        fundUsers.add(id);
    }

    public void removeUser(UUID id){
        fundUsers.remove(id);
    }

    public boolean checkUser(UUID id)
    {
        return fundUsers.contains(id);
    }


}
