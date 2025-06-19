package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.payload.CartDTO;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation  implements  CartService{
    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {
        return null;
    }
}
