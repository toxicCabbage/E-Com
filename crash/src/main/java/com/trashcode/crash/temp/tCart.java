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
@Table(name="fakecart")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class tCart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int userid;

    @ManyToMany
    private List <tProducts> pDUCt;

    public void addproduct(tProducts p) {
        System.out.println("hei p : " + p.getSellername());
        this.pDUCt.add(p);
        System.out.println("not working anymote");
    }
}
