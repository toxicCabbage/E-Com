package com.trashcode.crash.temp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "fakeproduct")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class tProducts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String productname;
    String sellername;
    
    @ManyToMany(mappedBy = "pDUCt")
    List <tCart> cart;

    public void addCart(tCart c){

        cart.add(c);

    }
   
}
