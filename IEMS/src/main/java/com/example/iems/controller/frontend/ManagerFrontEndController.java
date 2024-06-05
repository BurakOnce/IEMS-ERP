package com.example.iems.controller.frontend;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerFrontEndController {

    @GetMapping("/appProduct/manager/create-product")
    public String createProduct(){
        return "/manager/product/create-product.html";
    }

    @GetMapping("/appProduct/manager/update-product")
    public String updateProduct(){
        return "/manager/product/update-product.html";
    }

    @GetMapping("/appProduct/manager/delete-product")
    public String deleteProduct(){
        return "/manager/product/delete-product.html";
    }

    @GetMapping("/appProduct/manager/get-all-product")
    public String getAllProduct(){
        return "/manager/product/get-all-product.html";
    }

    @GetMapping("/appProduct/manager/manager-questions")
    public String getQuestions(){
        return "/manager/help/manager-questions.html";
    }

    @GetMapping("/appProduct/manager/manager-mail")
    public String getMail(){
        return "/manager/help/manager-mail.html";
    }

    @GetMapping("/appSupply/manager/create-order")
    public String CreateOrder(){
        return "/manager/supply/create-order.html";
    }

    @GetMapping("/appSupply/manager/order-statement")
    public String getOrderStatement(){
        return "/manager/supply/order-statement.html";
    }

    @GetMapping("/appSupply/manager/open-order")
    public String getOpenOrder(){
        return "/manager/supply/order-statement/open-order.html";
    }

    @GetMapping("/appSupply/manager/close-order")
    public String getCloseOrder(){
        return "/manager/supply/order-statement/close-order.html";
    }

    @GetMapping("/appSupply/manager/confirmed-orders")
    public String getSupplyCloseAndConfirmed(){
        return "/manager/supply/confirmed-orders.html";
    }

    @GetMapping("/appSupply/manager/pending-order")
    public String getPendingOrder(){
        return "/manager/supply/order-statement/pending-order.html";
    }

    @GetMapping("/appSupply/manager/duration-order")
    public String getDuringOrder(){
        return "/manager/supply/order-statement/duration-order.html";
    }

    @GetMapping("/appSupply/manager/complete-order")
    public String getCompleteOrder(){
        return "/manager/supply/order-statement/complete-order.html";
    }

    @GetMapping("/appSupply/manager/unsuccessful-order")
    public String getUnsuccessfulOrder(){
        return "/manager/supply/order-statement/unsuccessful-order.html";
    }



    @GetMapping("/manager")
    public String managerPage() {
        return "/manager/manager.html"; // Manager sayfasına yönlendirme
    }



}
