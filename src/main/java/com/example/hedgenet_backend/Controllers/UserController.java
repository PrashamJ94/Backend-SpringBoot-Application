package com.example.hedgenet_backend.Controllers;


import com.example.hedgenet_backend.Entity.User;
import com.example.hedgenet_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins={"http://20.82.224.21:3000/","http://localhost:3000"})
public class UserController {

    @Autowired private UserRepo userRepo;

    @GetMapping("/info")
    public User getUserDetails(){
        String user_name = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByUsername(user_name).get();
    }

   @GetMapping("/getAccountBalance")
    public Map<String,Float> getUserAccountBalance()
   {
       String username=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Optional<User> userRes=userRepo.findByUsername(username);
       if(userRes!=null)
       {
           User user=userRes.get();
           float acc_balance= (float) user.getAccountBal();
           return Collections.singletonMap("accBal",acc_balance);
       }

      return Collections.singletonMap("String",(float)0.0);
   }


}
