package com.trashcode.crash.Admin;

import java.util.List;
import java.util.Map;

import com.trashcode.crash.ProudctServices.productServices;
import com.trashcode.crash.Service.PageHandler;
import com.trashcode.crash.Service.ProductverificationService;
import com.trashcode.crash.Stats.StatisticsService;
import com.trashcode.crash.activity.Activity;
import com.trashcode.crash.activity.ActivityServices;
import com.trashcode.crash.productStats.ProductStatsServices;
import com.trashcode.crash.product_entity.OrderEntity;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.sellerStatus.sellerStatusService;
import com.trashcode.crash.user_entity.user_entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/a")
public class AdminController {

    @Autowired
    public PageHandler pageHandler;

    @Autowired
    private StatisticsService service;

    @Autowired
    private ProductStatsServices pServices;

    @Autowired
    private sellerStatusService sStatusService;

    @Autowired
    private ActivityServices activityServices;

    @Autowired
    private ProductverificationService pvs;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/")
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("Admin");

        long allProduct = service.getAllProductsCount();
        mv.addObject("allProudct", allProduct);

        long totalOrders = service.getAllOrderCount();
        mv.addObject("totalOrders", totalOrders);

        long TotalCustomer = service.getUserCountByRole();
        mv.addObject("totalCustomer", TotalCustomer);

        long TotalEarning = service.getSelletTotalEarning();
        mv.addObject("totalEarning", TotalEarning);

        Map<String, Integer> data = service.test();
        mv.addObject("month", data.keySet());
        mv.addObject("value", data.values());

        List<product_entity> products = pServices.getTopProducts();
        mv.addObject("TopProducts", products);

        List<user_entity> topSeller = sStatusService.topSeller();
        mv.addObject("topSeller", topSeller);

        List<Activity> activities = activityServices.getRecentActivity();
        mv.addObject("Activities", activities);

        int count = statisticsService.getPendingProducts();
        mv.addObject("pending", count);

        System.out.println("Pending count " + count);

        return mv;
    }

    @GetMapping("/allProduct")
    @ResponseBody
    public ModelAndView similarProduct() {

        // Debug
        System.out.println("this is not parametraized method");

        ModelAndView mv = new ModelAndView("AllProduct");
        int pageNo = 1;
        String sortField = "productName";
        String sortDir = "ase";
        // Page<product_entity> productPage = similarproduct.getPage(pageNo,
        // term,sortField,sortDir);
        Page<product_entity> productPage = pageHandler.getPage(pageNo, sortField, sortDir);

        List<product_entity> pD = productPage.getContent();

        for (product_entity p : pD) {
            System.out.println("Page ITems : " + p.getProductName());
        }

        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());

        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        System.out.println("empty");
        return mv;
    }

    @GetMapping(value = "/allProduct/{pageNo}")
    @ResponseBody
    public ModelAndView similarProductWithTerm(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            ModelAndView mv) {
        System.out.println("This is from PARametrized method");

        mv = new ModelAndView("AllProduct");
        // Page <product_entity> productPage = similarproduct.getPage(pageNo,
        // searchTerm, sortField, sortDir);
        Page<product_entity> productPage = pageHandler.getPage(pageNo, sortField, sortDir);
        List<product_entity> pD = productPage.getContent();

        for (product_entity p : pD) {
            System.out.println(p.getProductName());
        }

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

    @GetMapping("/allUser")
    @ResponseBody
    public ModelAndView allUser() {

        // Debug
        System.out.println("this is not parametraized method");

        ModelAndView mv = new ModelAndView("AllUser");
        int pageNo = 1;
        String sortField = "username";
        String sortDir = "ase";
        // Page<product_entity> productPage = similarproduct.getPage(pageNo,
        // term,sortField,sortDir);
        Page<user_entity> productPage = pageHandler.getAllUser(pageNo, sortField, sortDir);

        List<user_entity> pD = productPage.getContent();

        for (user_entity p : pD) {
            System.out.println("Page ITems : " + p.getUsername());
        }

        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());

        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        System.out.println("empty");
        return mv;
    }

    @GetMapping(value = "/allUser/{pageNo}")
    @ResponseBody
    public ModelAndView allUser(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            ModelAndView mv) {
        System.out.println("This is from PARametrized method");

        mv = new ModelAndView("AllUser");
        // Page <product_entity> productPage = similarproduct.getPage(pageNo,
        // searchTerm, sortField, sortDir);
        Page<user_entity> productPage = pageHandler.getAllUser(pageNo, sortField, sortDir);
        List<user_entity> pD = productPage.getContent();

        for (user_entity p : pD) {
            System.out.println(p.getUsername());
        }

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

    @GetMapping("/pending")
    @ResponseBody
    public ModelAndView getMethodName() {
        ModelAndView mv = new ModelAndView("AllUser");
        System.out.println("pending");
        List<OrderEntity> pe = pvs.getPendingProducts();
        mv.addObject("Info", pe);
        System.out.println(pe);
        return mv;
    }

}
