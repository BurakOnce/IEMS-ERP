package com.example.iems.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendEmail")
public class PublicController {

    @Autowired
    private EmailSenderService senderService;


    @PostMapping
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        senderService.sendSimpleEmail(emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getBody());
        return "Mail sent successfully";
    }

    @GetMapping("/get-message")
    public String getMessage(){
        return "get message";
    }
}
