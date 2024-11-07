// package com.trashcode.crash.controller;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;

// import com.trashcode.crash.CrashApplication;
// import com.trashcode.crash.cartRepo.cartRepo;
// import com.trashcode.crash.product_entity.cartEntity;
// import com.trashcode.crash.product_entity.tempCart;
// import com.trashcode.crash.temp.tempCartRepo;
// import com.trashcode.crash.temp.temp_user_Data;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.SpringApplication;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.servlet.ModelAndView;








// @Controller
// public class cartController {
 
//     @Autowired
//     private temp_user_Data tud;

//     @Autowired
//     private cartEntity cartentity;

//     @Autowired
//     private cartRepo cRepo;

//     @Autowired
//     private tempCart tc;

//     @Autowired
//     private tempCartRepo tcr;


//     // @PostMapping("/saveUser")
//     // public String saveUser(@ModelAttribute user_entity userEntity){
//     //     user_repo.save(userEntity);
//     //     return "redirect:/show";
//     // }



// @ResponseBody
// @GetMapping("/cart")
// public String cart(@ModelAttribute tempCart tempc){
 
//     // @RequestParam("productID") int id
//     System.out.println("new created cart controller");
//     tc.setProductId(12);
//     tc.setUserId(2);
//     tcr.save(tempc);
//       return "redirect:/show";
//  }

//  @GetMapping("/temper")
//  public ModelAndView show(){
//      ModelAndView mv = new ModelAndView("tempr");
//      mv.addObject("info", tc);
//      System.out.println("show somting");
    
//      return mv;
    
//  }






    
// }
