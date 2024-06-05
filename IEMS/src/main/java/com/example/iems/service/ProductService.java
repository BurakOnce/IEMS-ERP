package com.example.iems.service;

import com.example.iems.dto.Product.CreateProductRequest;
import com.example.iems.dto.Product.UpdateProductRequest;
import com.example.iems.exceptions.SQLIntegrityConstraintViolationException;
import com.example.iems.model.Product;
import com.example.iems.model.Supply;
import com.example.iems.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductService  {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }


    public ResponseEntity<Map<String, String>> CreateProduct(CreateProductRequest request)throws SQLIntegrityConstraintViolationException  {
        Map<String, String> response = new HashMap<>();

        try{
        String barcodName = request.getBarcode();
        if (productRepository.findByName(barcodName).isPresent()) {
            response.put("message", "Barcode is already used");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (request.getBarcode().length()!=8) {
            response.put("message", "Barcode must be 8 digits");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (request.getDiscount()>100 || request.getDiscount()<0) {
            response.put("message", "Discount rate must be between 0 and 100");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {

            Product newProduct = Product.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .quantityUnits(request.getQuantityUnits())
                    .stock(request.getStock())
                    .demand(request.getDemand())
                    .barcode(request.getBarcode())
                    .discount(request.getDiscount())
                    .price(request.getPrice())
                    .company(request.getCompany())
                    .category(request.getCategory())
                    .build();

            productRepository.save(newProduct);
            response.put("message", "Product created successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception message){
            response.put("message", "Make sure that the entered values are unique.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    public ResponseEntity<Map<String, String>> updateProduct(String barcode, UpdateProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findByBarcode(barcode);
        if (optionalProduct.isPresent()) {
            Product  existingProduct = optionalProduct.get();

            existingProduct.setName(request.getName() != null ? request.getName() : existingProduct.getName());
            existingProduct.setDescription(request.getDescription() != null ? request.getDescription() : existingProduct.getDescription());
            existingProduct.setQuantityUnits(request.getQuantityUnits() != null ? request.getQuantityUnits() : existingProduct.getQuantityUnits());
            existingProduct.setStock(request.getStock() != null ? request.getStock() : existingProduct.getStock());
            existingProduct.setDemand(request.getDemand() != null ? request.getDemand() : existingProduct.getDemand());
            existingProduct.setBarcode(request.getBarcode() != null ? request.getBarcode() : existingProduct.getBarcode());
            existingProduct.setDiscount(request.getDiscount() != null ? request.getDiscount() : existingProduct.getDiscount());
            existingProduct.setPrice(request.getPrice() != null ? request.getPrice() : existingProduct.getPrice());
            existingProduct.setCompany(request.getCompany() != null ? request.getCompany() : existingProduct.getCompany());
            existingProduct.setCategory(request.getCategory() != null ? request.getCategory() : existingProduct.getCategory());


            productRepository.save(existingProduct);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product successfully created");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Product not found 'Barcode' : " + barcode);
        }
    }

    public ResponseEntity<String> deleteProduct(String barcode){
        Optional<Product> optionalProduct = productRepository.findByBarcode(barcode);

        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            productRepository.delete(existingProduct);
            return ResponseEntity.ok( "Product deleted successfully");

        }else {
            return ResponseEntity.ok( "Product has not found in database");

        }
    }

    public ResponseEntity<String> deleteAllProduct(){
        productRepository.deleteAll();
        return ResponseEntity.ok( "All Product's deleted successfully");

    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getMyProducts(Long companyId){
        return productRepository.findByCompanyId(companyId);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);}

    public List<Product> sortByPriceDown(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    public List<Product> sortByPriceUp(){
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    public List<Product> sortByDiscountDown(){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "discount"));
    }

    public List<Product> sortByDiscountUp(){
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "discount"));
    }

    public String getDescription(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            return existingProduct.getDescription();

        } else {
            return "ürün bulunamadı";
        }
    }

}
