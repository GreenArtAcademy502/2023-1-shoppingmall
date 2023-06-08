package com.green.shoppingmall.product.model;

import lombok.Data;
import lombok.ToString;

@Data
public class ProductInsDto {
    private Integer price;
    private String ctnt;
    private String nm;
    private String brand;
}
