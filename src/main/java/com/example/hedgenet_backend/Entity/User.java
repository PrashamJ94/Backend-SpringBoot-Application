package com.example.hedgenet_backend.Entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@Table(name = "appuser")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;

    @Column(name="accountBalance")
    private double accountBal=1000.00;


    @Column(name="funds_joined")
    @ElementCollection
    List<UUID> fundsjoined;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        fundsjoined=new ArrayList<UUID>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void addFundId(UUID fund_id) {
        fundsjoined.add(fund_id);
    }

    public void removeFundId(UUID fund_id) {
        fundsjoined.remove(fund_id);
    }

    public double getAccountBal() {
        return accountBal;
    }

    public void setAccountBal(double accountBal) {
        this.accountBal = accountBal;
    }


}

