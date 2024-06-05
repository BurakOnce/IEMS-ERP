package com.example.iems.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeFrontEndController {

    @GetMapping("/employee/create-product")
    public String createProduct(){
        return "/employee/product/create-product.html";
    }

    @GetMapping("/employee/update-product")
    public String updateProduct(){
        return "/employee/product/update-product.html";
    }

    @GetMapping("/employee/get-all-product")
    public String getAllProduct(){
        return "/employee/product/get-all-product.html";
    }


    @GetMapping("/employee/employee-questions")
    public String getQuestions(){
        return "/employee/help/employee-questions.html";
    }

    @GetMapping("/employee/employee-mail")
    public String getMail(){
        return "/employee/help/employee-mail.html";
    }





    @GetMapping("/employee")
    public String employeePage() {
        return "/employee/employee.html"; // Manager sayfasına yönlendirme
    }



}
