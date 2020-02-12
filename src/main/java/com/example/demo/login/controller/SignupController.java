package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {
  private Map<String, String> radioMarriage;

  private Map<String, String> initRadioMarriage() {
    Map<String, String> radio = new LinkedHashMap<>();
    radio.put("既婚", "true");
    radio.put("未婚", "false");
    return radio;
  }

  @GetMapping("/signup")
  public String getSignup(Model model) {
    radioMarriage = initRadioMarriage();
    model.addAllAttributes("radioMarriage", radioMarriage);
    return "login/signup";
  }

}   
