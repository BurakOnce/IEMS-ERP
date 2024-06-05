package com.example.iems.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierFrontEndContoller {

    @GetMapping("/appSupply/supplier/open-pending-orders")
    public String openPendingOrders(){
        return "/supplier/open-pending-orders.html";
    }

    @GetMapping("/appSupply/supplier/my-supplies/duration-order")
    public String durationOrder(){
        return "/supplier/my-supplies/duration-order.html";
    }

    @GetMapping("/appSupply/supplier/my-supplies/complete-order")
    public String completeOrder(){
        return "/supplier/my-supplies/complete-order.html";
    }

    @GetMapping("/appSupply/supplier/my-supplies/unsuccessful-order")
    public String unsuccessfulOrder(){
        return "/supplier/my-supplies/unsuccessful-order.html";
    }

    @GetMapping("/appSupply/supplier/my-supplies/confirmed-order")
    public String confirmedOrder(){
        return "/supplier/my-supplies/confirmed-order.html";
    }

    @GetMapping("/appSupply/supplier/help/supplier-questions")
    public String supplierQuestions(){
        return "/supplier/help/supplier-questions.html";
    }

    @GetMapping("/appSupply/supplier/help/supplier-mail")
    public String supplierMail(){
        return "/supplier/help/supplier-mail.html";
    }

    @GetMapping("/supplier")
    public String supplierPage() {
        return "/supplier/supplier.html";
    }



}
