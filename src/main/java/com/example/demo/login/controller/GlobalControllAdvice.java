package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllAdvice {
  
  @ExceptionHandler(DataAccessException.class)
  public String dataAccessExceptionHandler(DataAccessException e, Model model) {
    model.addAttribute("error", "内部サーバエラー（DB）　：ExceptionHandler");
    model.addAttribute("message", "SignupControllerでDataAccessExceptionｇが発生しました");
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
    return "error";
  }
  
  @ExceptionHandler(Exception.class)
  public String exceptionHandler(DataAccessException e, Model model) {
    model.addAttribute("error", "内部サーバエラー：ExceptionHandler");
    model.addAttribute("message", "SignupControllerでExceptionｇが発生しました");
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
    return "error";
  }

}
