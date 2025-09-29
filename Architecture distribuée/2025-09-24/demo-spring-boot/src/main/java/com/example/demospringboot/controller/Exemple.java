package com.example.demospringboot.controller;

import com.example.demospringboot.dto.AgeDto;
import com.example.demospringboot.dto.DateDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("test")
public class Exemple {

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("intro")
    public ResponseEntity<String> intro(@RequestParam String name){
        String message =  "Hello "+name;
        return ResponseEntity.ok(message);
    }

    @GetMapping("myage/{age}")
    public ResponseEntity<String> myage(@PathVariable int age){
        try{
            Integer myage = Integer.valueOf(age);
            return ResponseEntity.ok(myage.toString());
        }catch(Exception e){
            return new ResponseEntity<>("Attribut age incorrect", HttpStatusCode.valueOf(403));
        }
    }

    @PostMapping("getAge")
    public ResponseEntity getAge(@RequestParam DateDto dateDto){
        try {
            LocalDate birthdate = LocalDate.of(dateDto.getAnnee(), dateDto.getMois(), dateDto.getJour());
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            return ResponseEntity.ok(age);
        } catch (Exception e) {
            return new ResponseEntity<>("Attribut age incorrect", HttpStatusCode.valueOf(403));
        }
    }
}
