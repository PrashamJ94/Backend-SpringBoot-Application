package com.example.hedgenet_backend.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="trade")
public class Trade
{
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="trade_id")
 UUID tradeid;

 @Column(name="ticker_id")
 private String tickerid;


 @Column(name="quantity")
 private int quantity;

 @Column(name="price")
 private float price;

 @Column(name="PersonalPL")
 private float personalPL;



    @ManyToOne
 @JoinColumn(name = "user_id")
    private User user;

 @ManyToOne
 @JoinColumn(name = "fundid")
 private FundEntity fund;

 @CreationTimestamp
 @Column(name = "Time")
 private Timestamp timestamp;

    public UUID getTradeid() {
        return tradeid;
    }

    public void setTradeid(UUID tradeid) {
        this.tradeid = tradeid;
    }

    public String getTickerid() {
        return tickerid;
    }

    public void setTickerid(String tickerid) {
        this.tickerid = tickerid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FundEntity getFund() {
        return fund;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getPersonalPL() {
        return personalPL;
    }

    public void setPersonalPL(float personalPL) {
        this.personalPL = personalPL;
    }
}
