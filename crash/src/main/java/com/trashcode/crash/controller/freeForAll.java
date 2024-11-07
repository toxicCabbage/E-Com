package com.trashcode.crash.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.trashcode.crash.RecentProductServices.RecentProductManager;
import com.trashcode.crash.Service.PageHandler;
import com.trashcode.crash.Service.ProductService;
import com.trashcode.crash.Service.getCurrentUserId;
import com.trashcode.crash.Service.similarProductService;
import com.trashcode.crash.Stats.StatisticsService;
import com.trashcode.crash.Transaction.TransactionRepo;
import com.trashcode.crash.Transaction.Transactions;
import com.trashcode.crash.auth.roleConfig;
import com.trashcode.crash.cartServices.cartManager;
import com.trashcode.crash.enums.transactionCondition;
import com.trashcode.crash.productRepo.productRepo;
import com.trashcode.crash.productStats.ProductStatsServices;
import com.trashcode.crash.product_entity.product_entity;
import com.trashcode.crash.product_entity.recentProduct;
import com.trashcode.crash.security.givemepassword;
import com.trashcode.crash.temp.temp_user_Data;
import com.trashcode.crash.user_entity.user_entity;
import com.trashcode.crash.user_repository.user_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class freeForAll {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private user_repository user_repo;
    @Autowired
    private user_entity userInfo;
    @Autowired
    private temp_user_Data tud;
    @Autowired
    private productRepo prod_repo;
    // @Autowired
    // private product_entity productEntity;
    @Autowired
    givemepassword givemepassword;
    // @Autowired
    // private getCurrentUser gCU;

    @Autowired
    private similarProductService similarproduct;

    String searchTerm;

    // @Autowired
    // private currentPasswordMatcher cPM;

    // @Autowired
    // private recentProductRepo rcentproductRepo;

    @Autowired
    private getCurrentUserId GcUid;

    // @Autowired
    // private ProductverificationService pvs;

    @Autowired
    private RecentProductManager recentProductManager;

    // @Autowired
    // private orderManager oManager;

    @Autowired
    private roleConfig RoleConfig;

    // @Autowired
    // private RoleRepo rRpo;

    @Autowired
    PageHandler pageHandler;

    @Autowired
    private cartManager cManager;

    @Autowired
    private ProductStatsServices pServices;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private ProductService productService;

    boolean logintest = false;

    @GetMapping({ "/show", "/" })
    public ModelAndView show() {

        ModelAndView mv = new ModelAndView("temp");
        System.out.println("show somting");

        // List <user_entity> list = user_repo.findAll();
        List<product_entity> pentity = new ArrayList<>();
        List<product_entity> x = productService.getFeaters();
        mv.addObject("feature", x);
        boolean login = true;

        try {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println(GcUid.getCurrentUserID());
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
        } catch (Exception e) {
            login = false;
            e.printStackTrace();
        }

        try {

            List<recentProduct> rProduct = recentProductManager.getRecentItems();
            // System.out.println("Recent products :::::::::::::::::::" + rProduct);

            user_entity user = user_repo.findByuserid(GcUid.getCurrentUserID());
            System.out.println("user ::::::" + user.getRoles());

            System.out.println("BEFORE SORTING.........................................");

            Collections.sort(pentity, new Comparator<product_entity>() {
                public int compare(product_entity o1, product_entity o2) {
                    return (int) (o2.getProductid() - o1.getProductid());
                }
            });

            // System.out.println("+++++++++++++++++++++++++ Aftar sorting List
            // +++++++++++++++++++++++++++++++++++");

            // for (product_entity p : pentity) {
            // System.out.println(" product entity " + p.getProductid());
            // }

            mv.addObject("recentItems", rProduct);
            mv.addObject("login", login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // mv.addObject("obj", list);
        return mv;

    }

    @GetMapping("/add_user")
    public ModelAndView addUser() {
        ModelAndView mv = new ModelAndView("regi");
        System.out.println("Add User");
        mv.addObject("userInfo", userInfo);
        return mv;
    }

    // @GetMapping("/logout")
    // public ModelAndView logout(){

    // }

    @GetMapping("/login")
    public ModelAndView loginPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            System.out.println("Your are not logged in");
            ModelAndView mv = new ModelAndView("signIn");
            mv.addObject("userInfo", tud);
            return mv;
        }
        System.out.println("login Successfull");
        ModelAndView mv = new ModelAndView("temp");
        return mv;

    }

    @PostMapping("/saveUser")
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
        return "redirect:/show";
    }

    @GetMapping(value = "/similarProduct")
    @ResponseBody
    public ModelAndView similarProduct(@RequestParam(name = "search") String term) {

        // Debug
        System.out.println("this is not parametraized method");

        ModelAndView mv = new ModelAndView("searchPage");
        int pageNo = 1;
        String sortField = "productName";
        String sortDir = "ase";
        searchTerm = term;
        Page<product_entity> productPage = similarproduct.getPage(pageNo, term, sortField, sortDir);
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

    // @RequestParam(name = "search")String term,
    @GetMapping(value = "/similarProduct/{pageNo}")
    @ResponseBody
    public ModelAndView similarProductWithTerm(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            ModelAndView mv) {
        System.out.println("Keyword :" + searchTerm);
        System.out.println("This is from PARametrized method");

        mv = new ModelAndView("searchPage");
        Page<product_entity> productPage = similarproduct.getPage(pageNo, searchTerm, sortField, sortDir);
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

    @ResponseBody
    @GetMapping("/electronic/{pageNo}")
    public ModelAndView electronic(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            ModelAndView mv) {
        String cate = "electronic";
        mv = new ModelAndView("productProfile");
        System.out.println("pageno :" + pageNo);
        Page<product_entity> productPage = pageHandler.getPage(pageNo, cate, sortField, sortDir);
        List<product_entity> pD = productPage.getContent();

        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());
        mv.addObject("title", "electronic");
        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);

        mv.addObject("asc", "asc");
        mv.addObject("desc", "desc");
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        System.out.println("HardCore Handler");
        return mv;
    }

    @ResponseBody
    @GetMapping("/allProduct")
    public ModelAndView allProduct() {
        ModelAndView mv = new ModelAndView("productProfile");
        Page<product_entity> products = pageHandler.getPage(1, "productName", "ase");
        List<product_entity> pd = products.getContent();
        mv.addObject("prouctInfo", pd);
        mv.addObject("currentPage", 1);
        mv.addObject("totalPages", products.getTotalPages());
        mv.addObject("totalItems", products.getTotalElements());
        mv.addObject("sortField", "productName");
        mv.addObject("sortDir", "ase");
        mv.addObject("reverseSortDir", "ase".equals("asc") ? "desc" : "asc");
        return mv;
    }

    @ResponseBody
    @GetMapping("/electronic")
    public ModelAndView electronic0() {
        String cate = "electronic";
        ModelAndView mv = new ModelAndView("productProfile");
        int pageNo = 1;
        String sortField = "productName";
        String sortDir = "ase";
        Page<product_entity> productPage = pageHandler.getPage(pageNo, cate, sortField, sortDir);
        List<product_entity> pD = productPage.getContent();
        mv.addObject("prouctInfo", pD);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", productPage.getTotalPages());
        mv.addObject("totalItems", productPage.getTotalElements());
        mv.addObject("title", "electronic");
        mv.addObject("sortField", sortField);
        mv.addObject("sortDir", sortDir);
        mv.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return mv;
    }

    // prodile product page cotroller

    // @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    @GetMapping("/productProfile/{pId}")
    public ModelAndView productProfile(recentProduct repProduct, @PathVariable("pId") long id) {
        System.out.println(id);
        System.out.println("this is product profile");
        // Adding product to Recent table
        ModelAndView mv = new ModelAndView("profileProduct");
        product_entity pe = prod_repo.findByproductid(id);

        // Recent product Modual
        try {
            recentProductManager.addItem(id);

        } catch (Exception e) {
            // TODO: handle exception
        }

        String cartStatus = "show";

        // Stack check
        int stock = pe.getStock();

        boolean inStock;
        if (stock == 0) {
            System.out.println("instoakc ins false ___________________________________");
            inStock = false;
        } else {
            System.out.println("instock in tureeeeeeeeeeeeeeeee+++++++++++++++++++++++++");
            inStock = true;
        }

        System.out.println("breakeing POINt_____________________");
        try {

            if (cManager.alreadyInCart(id)) {
                cartStatus = "show";
            }

            else {
                cartStatus = "hide";
            }
            if (!inStock) {
                cartStatus = "dont";
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(cartStatus);

        // product View count
        pServices.addViewCount((int) id);

        mv.addObject("buy", inStock);
        mv.addObject("info", pe);
        mv.addObject("cStatus", cartStatus);
        return mv;
    }

    // sortDir.value +
    // "&minValue=" +
    // minValue.value +
    // "&maxValue=" +
    // maxValue.value;

    @ResponseBody
    @GetMapping("/FillterProducts/{pageNo}")
    public ModelAndView FilterPrice(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            @RequestParam("minValue") String minValue,
            @RequestParam("maxValue") String maxValue,
            ModelAndView mv) {

        System.out.println("-------------------------test Log ------------------------------------------");
        System.out.println("SORT FIELD VALUE :" + sortField);
        System.out.println("SORT Dir VALUE :" + sortDir);
        System.out.println("MIN VALUE : " + minValue);
        System.out.println("Max VALUE : " + maxValue);
        System.out.println("CURRENT PAGE :  " + pageNo);

        String cate = "electronic";
        mv = new ModelAndView("productProfile");
        System.out.println("pageno :" + pageNo);
        Page<product_entity> productPage = pageHandler.getProductPageByPriceFillter(pageNo, cate, sortField, sortDir,
                Integer.parseInt(minValue), Integer.parseInt(maxValue));
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

    @GetMapping("/userConstruction")
    public String test() {

        System.out.println("hellow oooooooooooooooooooooooooooooooooooooooooo");

        Map<String, Integer> data = statisticsService.test();
        return "temp";

    }
}
