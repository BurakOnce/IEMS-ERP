package com.example.iems.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminFrontEndController {

    @GetMapping("/appUser/admin/get-all-user")
    public String getAllUser(){
        return "/admin/user/get-all-user.html";
    }

    @GetMapping("/appUser/admin/create-user")
    public String createUser(){
        return "/admin/user/create-user.html";
    }

    @GetMapping("/appUser/admin/delete-user")
    public String deleteUser(){
        return "/admin/user/delete-user.html";
    }

    @GetMapping("/appUser/admin/update-user")
    public String updateUser(){
        return "/admin/user/update-user.html";
    }

    @GetMapping("/appUser/admin/get-all-company")
    public String getAllCompany(){
        return "/admin/company/get-all-company.html";
    }

    @GetMapping("/appUser/admin/create-company")
    public String createCompany(){
        return "/admin/company/create-company.html";
    }

    @GetMapping("/appUser/admin/delete-company")
    public String deleteCompany(){
        return "/admin/company/delete-company.html";
    }

    @GetMapping("/appUser/admin/update-company")
    public String updateCompany(){
        return "/admin/company/update-company.html";
    }

    @GetMapping("/hello")
    public String helloPage() {
        return "/hello.html"; // Admin sayfasına yönlendirme
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "/admin/admin.html"; // Admin sayfasına yönlendirme
    }


    /*
    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String targetPage = "";

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                targetPage = "redirect:/admin";
                break;
            } else if (authority.getAuthority().equals("ROLE_MANAGER")) {
                targetPage = "redirect:/manager";
                break;
            } else if (authority.getAuthority().equals("ROLE_EMPLOYEE")) {
                targetPage = "redirect:/employee";
                break;
            }
        }

        modelAndView.setViewName(targetPage);
        return modelAndView;
    }*/

}
