package com.trashcode.crash.controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trashcode.crash.RecentProductServices.RecentProductManager;
import com.trashcode.crash.Service.RecentProductService;
import com.trashcode.crash.Service.getCurrentUserAddress;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.cartRepo.cartRepo;
import com.trashcode.crash.cartServices.cartManager;
import com.trashcode.crash.orederManagerService.orderManager;
import com.trashcode.crash.productRepo.OrderRepo;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.productRepo.recentProductRepo;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.cartEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.temp.tCartRepo;
import com.trashcode.crash.temp.tProductRepo;
import com.trashcode.crash.temp.temp_user_Data;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// TODO:
// 1> Line 167 : Prevent User to Add same product again

@Controller
@RequestMapping("/u")
public class productController {

    @Autowired
    private productRepo pRepo;

    @Autowired
    private cartEntity cEntity;

    @Autowired
    private cartEntity cartEnity;

    @Autowired
    private cartRepo cRepo;

    @Autowired
    private temp_user_Data tud;

    @Autowired
    private product_entity pEntity;

    @Autowired
    private getCurrentUserId gCUId;

    @Autowired
    private RecentProductService recentProductService;

    @Autowired
    private getCurrentUserAddress GCUA;

    @Autowired
    private recentProductRepo rProductRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private user_repository uRpo;

    // delete it later

    @Autowired
    private tProductRepo tpr;

    @Autowired
    private tCartRepo tcr;

    @Autowired
    private cartManager cManager;

    @Autowired
    private RecentProductManager recentProductManager;

    @Autowired
    private orderManager oManager;

    @Autowired
    private freeForAll ffl;

    // @ResponseBody
    // @GetMapping("/add")
    // public String addOne(){

    // System.out.println("this is from non paramatrized method controller");
    // return "hello";
    // }

    // @ResponseBody
    // @GetMapping("/add")
    // public String addTwo(){

    // System.out.println("this is from ############### method controller");
    // return "hello";
    // }

    // ADDIng Items to cart
    @ResponseBody
    @GetMapping("/cart")
    public ModelAndView cart(@RequestParam("productID") int id) {

        System.out.println("cart Cantroller *************************");
        ModelAndView mv = new ModelAndView("cart");

        cManager.addItem(id);

        // getRequest Useing spring boot

        List<cartEntity> cartItems = cManager.goToCart();

        int totalPrice = cManager.totalPrice(cartItems);
        System.out.println("net price" + totalPrice);
        mv.addObject("price", totalPrice);
        totalPrice = totalPrice + 150;
        mv.addObject("totalPrice", totalPrice);
        mv.addObject("cartEntity", cartItems);
        System.out.println("last word");

        return mv;
    }

    @ResponseBody
    @GetMapping("/buy")
    public ModelAndView buy(@RequestParam("productID") int id) {

        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");
        System.out.println("buy Cantroller *************************");

        ModelAndView mv = new ModelAndView("Checkout");
        String address = GCUA.getAddress();
        pEntity = pRepo.findByproductid(id);
        Integer price = pEntity.getProductPrice();
        int totalPrice = price + 150;

        mv.addObject("totalPrice", totalPrice);
        mv.addObject("info", pEntity);
        mv.addObject("address", address);

        return mv;
    }

    @ResponseBody
    @GetMapping("/goTocart")
    public ModelAndView goTocart() {

        System.out.println("productController.goTocart()");
        ModelAndView mv = new ModelAndView("cart");

        List<cartEntity> cartItems = cManager.goToCart();

        int totalPrice = cManager.totalPrice(cartItems);

        mv.addObject("price", totalPrice);

        totalPrice = totalPrice + 150;
        mv.addObject("totalPrice", totalPrice);
        mv.addObject("cartEntity", cartItems);
        System.out.println("last word");
        return mv;
    }

    public List<product_entity> cartManager(long currentUserId) {

        // List <cartEntity> centity = cRepo.findByuserId(currentUserId);
        // List <cartEntity> centity = new cartEntity<>();
        // List <product_entity> pentity = new ArrayList<>();
        // product_entity x;

        // for(cartEntity i : centity){

        // System.out.println(" values : " + i);
        // // int tp = (int) i.getProductId();
        // // x = pRepo.findByproductid((int)i.getProductId());

        // // System.out.println(x);
        // // pentity.add(x);

        // }

        // for(product_entity p : pentity){
        // System.out.println("from last object");
        // System.out.println(p);
        // }

        return null;
    }

    @ResponseBody
    @GetMapping("/delete/{productID}")
    public ModelAndView delete(@PathVariable("productID") int id) {

        System.out.println("this is working fine right now");
        cRepo.deleteById((long) id);

        ModelAndView mv = new ModelAndView();

        System.out.println("hello mf :" + id);
        return mv;
    }

    // @GetMapping("/recentItems")
    // @ResponseBody
    // public ModelAndView recentItems(){
    // ModelAndView mv = new ModelAndView("temp");
    // int userId = gCUId.getCurrentUserID();
    // List <recentProduct> recentItems = rProductRepo.findTop4ByuserId(userId);

    // for(recentProduct rp : recentItems){
    // System.out.println("recent PRoducts : " + rp.getProductId());
    // }

    // return mv;
    // }

    @PostMapping("/addOrder")
    public String addToORder(OrderEntity orderEntity, @RequestParam("pId") String Sid,
            @RequestParam("paymentValue") String paymentMethod) {
        System.out.println("addddddddddd oreder");
        System.out.println("addddddddddd oreder");
        System.out.println("addddddddddd oreder");
        System.out.println("addddddddddd oreder");
        System.out.println("addddddddddd oreder");
        System.out.println("addddddddddd oreder");

        int id = Integer.parseInt(Sid);

        System.out.println("productId " + id);
        System.out.println("paymentmethod : " + paymentMethod);

        oManager.addOrder(id);
        System.out.println("order Added successfully");
        return "redirect:/productProfile/" + id;
    }

    // @GetMapping("/tempCart")
    // public ModelAndView createJoinTable(){

    // System.out.println("this is temp cart controller product");
    // System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    // cartEntity cart = new cartEntity();
    // product_entity item = new product_entity();
    // System.out.println("find product");
    // item = pRepo.findByproductid(15);
    // System.out.println("find cart");
    // cart = cRepo.findByIid((long) 1);
    // System.out.println("LAST");

    // item.addCart(cart);
    // cart.addProduct(item);

    // cRepo.save(cart);

    // return null;
    // }
    @GetMapping("/buyAll")
    public ModelAndView buyAll() {
        ModelAndView mv = new ModelAndView("CheckoutAll");
        System.out.println("buy ALl Controller &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        List<cartEntity> ce = cManager.BuyEverything();
        int totalPrice = cManager.totalPrice(ce);

        totalPrice = totalPrice + 150;
        mv.addObject("price", (totalPrice - 150));
        mv.addObject("totalPrice", totalPrice);
        mv.addObject("info", ce);
        mv.addObject("address", GCUA.getAddress());
        return mv;
    }

    @GetMapping("/checkoutAll")
    public void checkoutAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reDirect = request.getContextPath();
        reDirect += "/show";
        oManager.checkoutAllItems();
        response.sendRedirect(reDirect);
    }

    @GetMapping("/removeCartItem/{pId}")
    public ModelAndView removeCartItem(@PathVariable("pId") int id) {

        ModelAndView mv = new ModelAndView("cart");
        cManager.DeleteCartItemByUserAndProduct(gCUId.getCurrentUserID(), id);

        List<cartEntity> cartItems = cManager.goToCart();

        int totalPrice = cManager.totalPrice(cartItems);

        mv.addObject("price", totalPrice);

        totalPrice = totalPrice + 150;
        mv.addObject("totalPrice", totalPrice);
        mv.addObject("cartEntity", cartItems);
        System.out.println("last word");
        return mv;
    }

}
