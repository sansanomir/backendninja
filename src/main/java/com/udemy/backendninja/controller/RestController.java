package com.udemy.backendninja.controller;

import com.udemy.backendninja.entity.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @GetMapping("/checkrest")
    public ResponseEntity<Contact> checkRest() {
        Contact contactModel = new Contact(2,
                "Mikerl", "Perez", "66666", "Elx");
        return new ResponseEntity<Contact>(contactModel, HttpStatus.OK);
    }
}
