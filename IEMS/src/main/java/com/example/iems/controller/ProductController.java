package com.example.iems.controller;

import com.example.iems.dto.Product.CreateProductRequest;
import com.example.iems.dto.Product.UpdateProductRequest;
import com.example.iems.exceptions.SQLIntegrityConstraintViolationException;
import com.example.iems.model.Product;
import com.example.iems.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/appProduct")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/manEmp/createProduct")
    public ResponseEntity<Map<String, String>> CreateProduct(@RequestBody CreateProductRequest request) throws SQLIntegrityConstraintViolationException {
        return productService.CreateProduct(request);
    }

    @PostMapping("/manEmp/updateProduct")
    public ResponseEntity<Map<String, String>>  UpdateProduct(String barcode, @RequestBody UpdateProductRequest request){
        return productService.updateProduct(barcode,request);
    }

    @DeleteMapping("/manEmp/deleteProduct")
    public ResponseEntity<String> DeleteProduct(String barcode){
        return productService.deleteProduct(barcode);
    }

    @DeleteMapping("/manager/deleteAllProduct")
    public ResponseEntity<String> DeleteAllProduct(){
        return productService.deleteAllProduct();
    }

    @GetMapping("/manEmp/getProduct/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/manEmp/getProduct/my")
    public List<Product> getMyProducts(Long companyId){
        return productService.getMyProducts(companyId);
    }

    @GetMapping("/manEmp/getProduct")
    public Optional<Product> getProductById(Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/manEmp/sortProduct/byPrice/down")
    public List<Product> SortProductByPriceDown(){
        return productService.sortByPriceDown();
    }
    @GetMapping("/manEmp/sortProduct/byPrice/up")
    public List<Product> SortProductByPriceUp(){
        return productService.sortByPriceUp();
    }
    @GetMapping("/manEmp/sortProduct/byDiscount/down")
    public List<Product> SortProductByDiscountDown(){
        return productService.sortByDiscountDown();
    }
    @GetMapping("/manEmp/sortProduct/byDiscount/up")
    public List<Product> SortProductByDiscountUp(){
        return productService.sortByDiscountUp();
    }

    @GetMapping("/manEmp/getDescription")
    public String  getDescription(Long productId) {
        return productService.getDescription(productId);
    }


}
