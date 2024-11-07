package com.trashcode.crash.temp;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class temp_user_Data {
    
    private String username;
    private String password;
}
