package com.ecommerce.sb_ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
 private Long productId;
 private String productName;
 private String image;
 private Integer quantity;
 private double price;
 private double specialPrice;
 private double discount;
private String description;

}
