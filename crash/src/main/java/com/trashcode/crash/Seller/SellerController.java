package com.trashcode.crash.Seller;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.trashcode.crash.SellerService.SellerService;
import com.trashcode.crash.SellerService.SellerServiceImpal;
import com.trashcode.crash.Service.PageHandler;
import com.trashcode.crash.Service.ProductverificationService;
import com.trashcode.crash.Service.confromProductRequestService;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.Stats.StatisticsService;
import com.trashcode.crash.activity.Activity;
import com.trashcode.crash.activity.ActivityServices;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.productStats.ProductStatsServices;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.services.FileUploadUtil;
import com.trashcode.crash.temp.temp_user_Data;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.io.IOException;

@Controller
@RequestMapping("/Seller")
public class SellerController {

    @Autowired
    private getCurrentUserId gCUId;

    @Autowired
    private productRepo pRepo;

    @Autowired
    private product_entity pe;

    @Autowired
    private SellerServiceImpal sellerServiceImpal;

    @Autowired
    private SellerService sellerService;

    @Autowired
    PageHandler pageHandler;

    @Autowired
    private ProductverificationService pvs;

    @Autowired
    private confromProductRequestService CPRS;

    @Autowired
    private temp_user_Data tud;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private ProductStatsServices pStatsService;

    @Autowired
    private ActivityServices activityServices;

    // upload Dir
    String path = "D:\\Projikt\\repo\\Toxic_Cabbage-flashdeal-6958b03de5c0\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image";
    // String path =
    // "D:\\Projikt\\repo\\Toxic_Cabbage-flashdeal-6958b03de5c0\\Spring_boot_web_app\\crash\\src\\main\\resources\\static\\image";

    @GetMapping("/")
    public ModelAndView SellerHome() {
        ModelAndView mv = new ModelAndView("SellerDashBoard");
        Map<String, Integer> data = statisticsService.test();
        mv.addObject("month", data.keySet());
        mv.addObject("value", data.values());

        int totalProducts = statisticsService.getAllProuductCountByUserId();
        mv.addObject("AllProducts", totalProducts);

        int totalOrders = statisticsService.getAllOrderCountByUserId();
        mv.addObject("AllOrders", totalOrders);

        int totalProudctRequestes = statisticsService.getAllProductRequestCountByUserId();
        mv.addObject("ProudctRequests", totalProudctRequestes);

        long totalIncome = statisticsService.getSelletTotalEarning();
        mv.addObject("totalIncome", totalIncome);

        List<product_entity> products = pStatsService.getTopOrderedProducts();
        mv.addObject("TopProducts", products);

        List<Activity> activities = activityServices.getActivityBySellerId();
        mv.addObject("Activities", activities);

        List<product_entity> LowStock = statisticsService.lowOnStock();
        mv.addObject("lowOnStock", LowStock);

        List<user_entity> recentBuyers = statisticsService.getRecentBuyers();
        mv.addObject("recentBuyers", recentBuyers);

        System.out.println("Seller Home");
        return mv;
    }

    @GetMapping("/add_product")
    public ModelAndView addProduct() {
        ModelAndView mv = new ModelAndView("productRegistration");
        System.out.println("Add Product");
        product_entity productEntity = new product_entity();
        mv.addObject("productEntity", productEntity);
        return mv;
    }

    @PostMapping("/save_product")
    public String saveUser(product_entity prod_entity,
            @RequestParam("Picture1") MultipartFile pic1,
            @RequestParam("Picture2") MultipartFile pic2,
            @RequestParam("Picture3") MultipartFile pic3) throws IOException, java.io.IOException {

        System.out.println("PRODUCT SAVE");
        System.out.println(prod_entity);

        String uploadDir = path + File.separator + pic1.getOriginalFilename();
        String uploadDir1 = path + File.separator + pic2.getOriginalFilename();
        String uploadDir2 = path + File.separator + pic3.getOriginalFilename();

        String Dbpath = "/image/" + pic1.getOriginalFilename();
        String Dbpath1 = "/image/" + pic2.getOriginalFilename();
        String Dbpath2 = "/image/" + pic3.getOriginalFilename();

        int userId;

        System.out.println(Dbpath);
        System.out.println(Dbpath1);
        System.out.println(Dbpath2);

        System.out.println("path Originam" + uploadDir);
        System.out.println("path Originam" + uploadDir1);
        System.out.println("path Originam" + uploadDir2);

        prod_entity.setProductImgUrl0(Dbpath);
        prod_entity.setProductImgUrl1(Dbpath1);
        prod_entity.setProductImgUrl2(Dbpath2);

        // adding userId
        try {
            userId = gCUId.getCurrentUserID();
            prod_entity.setUserid(userId);
        } catch (Exception e) {
            System.out.println("failed the get userid");
            e.printStackTrace();

        }

        pRepo.save(prod_entity);

        FileUploadUtil.saveFile(path, pic1.getOriginalFilename(), pic1);
        FileUploadUtil.saveFile(path, pic2.getOriginalFilename(), pic2);
        FileUploadUtil.saveFile(path, pic3.getOriginalFilename(), pic3);

        System.out.println("path : " + uploadDir);
        System.out.println(pic1.getOriginalFilename());

        return "redirect:/Seller/add_product";
    }

    // @GetMapping("/Sellerlogin")
    // public ModelAndView loginPage() {
    // System.out.println("hello there form login controller");

    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();

    // if (authentication == null || authentication instanceof
    // AnonymousAuthenticationToken) {
    // System.out.println("Your are not logged in");
    // ModelAndView mv = new ModelAndView("SellersignIn");
    // mv.addObject("userInfo", tud);
    // return mv;
    // }
    // System.out.println("login Successfull");
    // ModelAndView mv = new ModelAndView("temp");
    // return mv;

    // }

    @ResponseBody
    @GetMapping("/editProduct/{pId}")
    public ModelAndView editProduct(@PathVariable("pId") int productId) {
        ModelAndView mv = new ModelAndView("EditProduct");
        System.out.println("Seller controller");
        product_entity productEntity = pRepo.findByproductid(productId);
        mv.addObject("info", productEntity);
        mv.addObject("productEntity", pe);
        return mv;
    }

    @PostMapping("/Updateproduct")
    public String UpdateProduct(product_entity prod_entity,
            @RequestParam("Picture1") MultipartFile pic1,
            @RequestParam("Picture2") MultipartFile pic2,
            @RequestParam("Picture3") MultipartFile pic3) throws IOException, java.io.IOException {

        System.out.println("EDIT PRODUCT CONTROLLER");

        String uploadDir = path + File.separator + pic1.getOriginalFilename();
        String uploadDir1 = path + File.separator + pic2.getOriginalFilename();
        String uploadDir2 = path + File.separator + pic3.getOriginalFilename();

        String Dbpath = "/image/" + pic1.getOriginalFilename();
        String Dbpath1 = "/image/" + pic1.getOriginalFilename();
        String Dbpath2 = "/image/" + pic1.getOriginalFilename();

        int userId;

        System.out.println(Dbpath);

        System.out.println("path Originam" + uploadDir);
        System.out.println("path Originam" + uploadDir1);
        System.out.println("path Originam" + uploadDir2);

        prod_entity.setProductImgUrl0(Dbpath);
        prod_entity.setProductImgUrl1(Dbpath1);
        prod_entity.setProductImgUrl2(Dbpath2);

        // adding userId
        try {
            userId = gCUId.getCurrentUserID();
            prod_entity.setUserid(userId);
        } catch (Exception e) {
            System.out.println("failed the get userid");
            e.printStackTrace();

        }

        pRepo.save(prod_entity);

        FileUploadUtil.saveFile(path, pic1.getOriginalFilename(), pic1);
        FileUploadUtil.saveFile(path, pic2.getOriginalFilename(), pic2);
        FileUploadUtil.saveFile(path, pic3.getOriginalFilename(), pic3);

        System.out.println("path : " + uploadDir);
        System.out.println(pic1.getOriginalFilename());

        return "redirect:/add_product";
    }

    @PostMapping(value = "/changePic/{pId}")
    public String getMethodName(@PathVariable("pId") int productId, @RequestParam("term") String term,
            @RequestParam("profilePic") MultipartFile pic) throws IOException, java.io.IOException {

        System.out.println("this is UPdate pic controller");

        String uploadPath = path + File.separator + pic.getOriginalFilename();
        String Dbpath = "/image/" + pic.getOriginalFilename();

        // String term = "profilePic";
        System.out.println("term" + term);
        System.out.println("upload path : " + uploadPath);

        switch (term) {

            case "Pic1":
                pRepo.updateProductPicture1(Dbpath, productId);
                break;

            case "Pic2":
                System.out.println("PIC2");
                pRepo.updateProductPicture2(Dbpath, productId);
                break;

            case "Pic3":
                System.out.println("PIC3");
                pRepo.updateProductPicture3(Dbpath, productId);
                break;

            default:
                System.out.println("something went wrong :)");
        }

        FileUploadUtil.saveFile(path, pic.getOriginalFilename(), pic);

        System.out.println(pic.getOriginalFilename());

        return "redirect:/editProduct/" + productId;
    }

    @PostMapping("/changeField/{pId}")
    public String changeField(@PathVariable("pId") int productId, @RequestParam("term") String term,
            @RequestParam("Value") String Value) {
        System.out.println("Change Fiedld )))))))))))))))))))");

        System.out.println("term :" + term);

        switch (term) {
            case "Name":
                pRepo.updateproductName(Value, productId);
                break;

            case "Price":
                pRepo.updateproductPrice(Value, productId);
                break;

            case "Cate":
                pRepo.updateproductCate(Value, productId);
                break;

            case "Description":
                pRepo.updateDescription(Value, productId);
                break;

            default:
                System.out.println("nothing matched at all");
                break;
        }

        return "redirect:/Seller/editProduct/" + productId;
    }

    @GetMapping("/SellerProducts")
    public ModelAndView SellerProduct() {
        System.out.println("SELLER PRODUCT");

        int pageNo = 1;
        String sortField = "productName";
        String sortDir = "ase";

        int CurrentUSerid = gCUId.getCurrentUserID();
        ModelAndView mv = new ModelAndView("SellerAllProduct");
        Page<product_entity> productPage = pageHandler.getProductSByuserId(pageNo, CurrentUSerid, sortField, sortDir);
        List<product_entity> pD = productPage.getContent();
        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());

        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return mv;

    }

    @ResponseBody
    @GetMapping("/SellerProducts/{pageNo}")
    public ModelAndView electronic(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            ModelAndView mv) {
        int CurrentuserId = gCUId.getCurrentUserID();
        mv = new ModelAndView("SellerAllProduct");
        System.out.println("pageno :" + pageNo);
        Page<product_entity> productPage = pageHandler.getProductSByuserId(pageNo, CurrentuserId, sortField, sortDir);
        List<product_entity> pD = productPage.getContent();

        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());

        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);

        mv.addObject("asc", "asc");
        mv.addObject("desc", "desc");
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        System.out.println("HardCore Handler");
        return mv;
    }

    @GetMapping("/ProductVerify")
    public ModelAndView ProductVerify() {
        System.out.println("Verifu PRODUCT +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<OrderEntity> pe = pvs.verifiactionService();
        ModelAndView mv = new ModelAndView("SellerProductcheckout");

        mv.addObject("prouctInfo", pe);
        return mv;

    }

    @GetMapping("/conformProductRequest/{pId}")
    public String confromProductRequst(@PathVariable("pId") int primeryId) {
        System.out.println("order Id : " + primeryId);

        boolean flag = CPRS.confromProductRequest(primeryId);

        if (flag) {
            System.out.println("product conform successfully Where orderID : " + primeryId);
        } else {
            System.out.println("something went Wrong");
        }

        return "redirect:/Seller/ProductVerify/";
    }

    @GetMapping("/Sold")
    public ModelAndView Sold() {
        System.out.println("Sold PRODUCT +++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<OrderEntity> pe = pvs.SoldProduct();
        ModelAndView mv = new ModelAndView("Sold");

        mv.addObject("prouctInfo", pe);
        return mv;

    }

    @GetMapping("/DeleteProductSeller/{pId}")
    public ModelAndView DeleteProductBYSeller(@PathVariable("pId") int productId) {
        sellerServiceImpal.DeleteProductByProudctId(productId);

        System.out.println("DELETE PRODUCT");

        int pageNo = 1;
        String sortField = "productName";
        String sortDir = "ase";

        int CurrentUSerid = gCUId.getCurrentUserID();
        ModelAndView mv = new ModelAndView("SellerAllProduct");
        Page<product_entity> productPage = pageHandler.getProductSByuserId(pageNo, CurrentUSerid, sortField, sortDir);
        List<product_entity> pD = productPage.getContent();
        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());

        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return mv;

    }

}
