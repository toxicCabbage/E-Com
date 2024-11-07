package com.trashcode.crash.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.trashcode.crash.RecentProductServices.RecentProductManager;
import com.trashcode.crash.Service.ProductverificationService;
import com.trashcode.crash.Service.currentPasswordMatcher;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.auth.RoleRepo;
import com.trashcode.crash.auth.roleConfig;
import com.trashcode.crash.orederManagerService.orderManager;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.productRepo.recentProductRepo;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.security.givemepassword;
import com.trashcode.crash.services.FileUploadUtil;
import com.trashcode.crash.temp.temp_user_Data;
import com.trashcode.crash.userServices.getCurrentUser;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/u")
@Controller
// @RequestMapping("/hello")
public class user_controller {

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
    // String path = "C:\\Users\\NITESH AHIRE\\web
    // app\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image";
    // D:\\Projikt\\repo\\Toxic_Cabbage-flashdeal-6958b03de5c0\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image
    String path = "D:\\Projikt\\repo\\Toxic_Cabbage-flashdeal-6958b03de5c0\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image";
    // @GetMapping("/profile")
    // public ModelAndView verifyUser(@ModelAttribute temp_user_Data
    // tud,HttpServletRequest req){
    // ModelAndView mv = new ModelAndView("profile");
    // String tempUserName = (String)tud.getUsername();
    // String tempPassword = (String)tud.getPassword();
    // user_entity ue = user_repo.findByusername(tempUserName);
    // System.out.println(tempUserName);
    // System.out.println(tempPassword);

    // // Database Detail
    // String unfdb = ue.getUsername();
    // String upfdb = ue.getPassword();
    // long userID = ue.getUser_id();

    // // Adding current user id to session layer
    // HttpSession session = req.getSession();
    // boolean login;
    // session.setAttribute("currentUserId", userID);

    // System.out.println("userid : " + userID);
    // System.out.println(unfdb);
    // System.out.println(upfdb);

    // if(tempPassword.equals(upfdb)){
    // System.out.println("what up");
    // System.out.println(tempUserName);
    // login = true;
    // session.setAttribute("login", login);
    // return mv;
    // }
    // else{
    // System.out.println("what is this dude password in not matching");
    // }

    // return mv;
    // }

    // Add new product

    @GetMapping("/profile")
    public ModelAndView profile(HttpServletRequest request) {
        System.out.println("this is new profile controller");

        ModelAndView mv = new ModelAndView("/profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int userId = gCU.getUserId(username);
        userInfo = user_repo.findAllByuserid(userId);
        String address = userInfo.getArea() + " " + userInfo.getDistrict() + " " + userInfo.getState() + " "
                + userInfo.getCounty();
        System.out.println("THIS IS USERNAME  : " + userInfo.getName());
        mv.addObject("userInfo", userInfo);
        mv.addObject("userAddress", address);
        return mv;

    }

    @PostMapping(value = "/addPic")
    public String getMethodName(@RequestParam("term") String term, @RequestParam("profilePic") MultipartFile pic1)
            throws IOException {

        System.out.println("this is addpic controller");

        String uploadPath = path + File.separator + pic1.getOriginalFilename();
        String Dbpath = "/image/" + pic1.getOriginalFilename();

        // String term = "profilePic";
        System.out.println("term" + term);
        System.out.println("upload path : " + uploadPath);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int userId = gCU.getUserId(username);

        switch (term) {

            case "profilePic":
                user_repo.updateProfilePicture(Dbpath, userId);
                break;

            case "BGPic":
                user_repo.updateprofileBGPic(Dbpath, userId);
                break;

            default:
                System.out.println("something went wrong :)");

        }

        // if(term == "profilePic"){
        // System.out.println("inside");
        // user_repo.updateProfilePicture(userId, Dbpath);
        // }
        // else{
        // System.out.println("Not Matched");
        // }
        FileUploadUtil.saveFile(path, pic1.getOriginalFilename(), pic1);

        System.out.println(pic1.getOriginalFilename());

        return "redirect:/u/profile";
    }

    @PostMapping("/UpdatePassword")
    public String updatePassword(@RequestParam("oldPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword) {
        System.out.println("**********************upadatePassword**********************************");
        if (cPM.passwordMatched(currentPassword, newPassword)) {
            System.out.println("this is working absolutly fine :) ");
        }
        return "redirect:/u/profile";
    }

    @GetMapping("/order")
    public ModelAndView userOrders() {

        List<OrderEntity> toe = oManager.getAllOrderByUser();
        ModelAndView mv = new ModelAndView("userOrder");
        mv.addObject("prouctInfo", toe);
        return mv;
    }

}
