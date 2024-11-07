package com.trashcode.crash.unrestricted;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.trashcode.crash.RecentProductServices.RecentProductManager;
import com.trashcode.crash.Service.ProductverificationService;
import com.trashcode.crash.Service.currentPasswordMatcher;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.auth.RoleRepo;
import com.trashcode.crash.auth.Roles;
import com.trashcode.crash.auth.roleConfig;
import com.trashcode.crash.orederManagerService.orderManager;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.productRepo.recentProductRepo;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.product_entity.recentProduct;
import com.trashcode.crash.product_entity.tempOrderEntity;
import com.trashcode.crash.security.givemepassword;
import com.trashcode.crash.services.FileUploadUtil;
import com.trashcode.crash.temp.temp_user_Data;
import com.trashcode.crash.userServices.getCurrentUser;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class unRestrictedController {
    @Autowired
    private user_repository user_repo;
    @Autowired
    private user_entity userInfo;
    @Autowired
    private temp_user_Data tud;
    @Autowired
    private productRepo prod_repo;
    @Autowired
    private product_entity productEntity;
    @Autowired
    givemepassword givemepassword;
    @Autowired
    private getCurrentUser gCU;

    @Autowired
    private currentPasswordMatcher cPM;

    @Autowired
    private recentProductRepo rcentproductRepo;

    @Autowired
    private getCurrentUserId GcUid;

    @Autowired
    private ProductverificationService pvs;

    @Autowired
    private RecentProductManager recentProductManager;

    @Autowired
    private orderManager oManager;

    @Autowired
    private roleConfig RoleConfig;

    @Autowired
    private RoleRepo rRpo;

    boolean logintest = false;

    // upload Dir
    String path = "C:\\Users\\NITESH AHIRE\\web app\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image";

    @GetMapping("/add_Seller")
    public ModelAndView addUser() {
        ModelAndView mv = new ModelAndView("regiSeller");
        System.out.println("Add User");
        mv.addObject("userInfo", userInfo);
        return mv;
    }

    // @GetMapping("/logout")
    // public ModelAndView logout(){

    // }

    @GetMapping("/loginSeller")
    public ModelAndView loginPage() {
        System.out.println("hello there form login controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            System.out.println("Your are not logged in");
            ModelAndView mv = new ModelAndView("SellersignIn");
            mv.addObject("userInfo", tud);
            return mv;
        }
        System.out.println("login Successfull");
        ModelAndView mv = new ModelAndView("temp");
        return mv;

    }

    @GetMapping("/sellerlogout")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView("signOut");
        return mv;
    }

    @PostMapping("/saveSeller")
    public String saveUser(@ModelAttribute user_entity userEntity, @RequestParam("Role") String Role) {

        System.out.println("adding user role : " + Role);
        // adding roles

        // setting encoded password
        String password = userEntity.getPassword();
        String encodedPassword = givemepassword.getPassword(password);
        userEntity.setPassword(encodedPassword);
        userEntity.setRoles(RoleConfig.addRole(Role));
        System.out.println("secode last check");
        user_repo.save(userEntity);
        return "redirect:/Admin";
    }

}
