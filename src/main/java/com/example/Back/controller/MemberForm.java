package com.example.Back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class MemberForm
{
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
