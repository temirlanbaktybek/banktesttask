package com.example.banktesttask.service;

import com.example.banktesttask.entity.AppUser;

import javax.servlet.http.HttpServletRequest;

public interface UserService {


    String signin(String username, String password);

    String signup(AppUser appUser);

    void delete(String username);

    AppUser search(String username);

    AppUser whoami(HttpServletRequest req);

    String refresh(String username);
}
