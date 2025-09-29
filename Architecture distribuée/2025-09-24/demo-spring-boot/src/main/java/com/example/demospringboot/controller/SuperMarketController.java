package com.example.demospringboot.controller;

import com.example.demospringboot.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("supermarket")
public class SuperMarketController {

    @PostMapping("totalprice")
    public ResponseEntity<String> getTotalPrice(@RequestBody List<ProductDto> productList){
        try{
            Double totalPrice = 0.0;
            for (ProductDto productDto : productList) {
                totalPrice += productDto.getPrice();
            }
            return ResponseEntity.ok(totalPrice.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid product list");
        }
    }

    @PostMapping("priceForOneCategory")
    public ResponseEntity<String> getPricePerCategory(@RequestBody Map<String, Object> bodyRequest) {
        try {
            String category = (String) bodyRequest.get("cat");
            List<Map<String, Object>> productList = (List<Map<String, Object>>) bodyRequest.get("products");

            Double total = productList.stream()
                    .filter(p -> category.equalsIgnoreCase((String) p.get("category")))
                    .mapToDouble(p -> ((Number) p.get("price")).doubleValue())
                    .sum();

            return ResponseEntity.ok(total.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
