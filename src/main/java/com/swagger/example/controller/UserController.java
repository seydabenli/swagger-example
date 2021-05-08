package com.swagger.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swagger.example.entity.User;

@RestController
@RequestMapping("/user")
@Api(value = "User API doc")
public class UserController {

  private List<User> users = new ArrayList<>();

  @PostConstruct
  public void init() {
    users.add(new User(1, "Test kullanıcı", new Date()));
  }

  @PostMapping
  @PreAuthorize("hasAuthority('USER.CREATE')")
  @ApiOperation(value = "Yeni kullanıcı ekleme metodu", notes = "Kullanıcı ekleme metodu ile ilgili notlar.")
  public ResponseEntity<User> save(@RequestBody @ApiParam(value = "kullanici") User user) {
    users.add(user);
    return ResponseEntity.ok(user);
  }

  @GetMapping
  @PreAuthorize("hasAuthority('USER.SEARCH')")
  @ApiOperation(value = "Kullanıcıları getiren metod", notes = "Kullanıcıları getiren metod ile ilgili notlar.")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(users);
  }

}
