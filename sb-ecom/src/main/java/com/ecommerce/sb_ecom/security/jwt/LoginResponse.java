package com.ecommerce.sb_ecom.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

        private  String username;
        private String jwtToken;
        private List<String>roles;
}
