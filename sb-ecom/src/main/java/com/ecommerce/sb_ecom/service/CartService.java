package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.payload.CartDTO;
import com.ecommerce.sb_ecom.payload.CartItemDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartService {
     CartDTO addProductToCart(Long productId, Integer quantity);
     List<CartDTO> getAllCarts();
     CartDTO getCart(String emailId, Long cartId);
     @Transactional
     CartDTO updateProductQuantityInCart(Long productId, Integer quantity);

     String deleteProductFromCart(Long cartId, Long productId);

     void updateProductInCarts(Long cartId, Long productId);

     String createOrUpdateCartWithItems(List<CartItemDTO> cartItems);
}
