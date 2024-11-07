package com.trashcode.crash.autoComplete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class autoCompleteRestController {

@Autowired
private autoCompleteService autoComp;

@GetMapping("/search/{term}")
public List <String> Product (@PathVariable(name = "term")String term){

List <String> product = autoComp.getProductsName(term);
System.out.println("term :" + term);

  
  for (String x : product) {
      System.out.println("array items : " + x );
      
  }
    
    // return new autoCompleteService().getProductsName(term);
    return product;
}


    

}