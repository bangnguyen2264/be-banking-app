package com.example.springsecurity.controller;

import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.form.ChangePasswordForm;
import com.example.springsecurity.model.form.UserForm;
import com.example.springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getById(Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/user/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam(name = "query") String query) {
        return ResponseEntity.ok(userService.searchUser(query));
    }

    @GetMapping("/get/me")
    public ResponseEntity<UserDto> getMe(Principal connectedUser){
        return ResponseEntity.ok(userService.getMe(connectedUser));
    }
    @PutMapping("/update/me")
    public ResponseEntity<String> updateMe(Principal principal,@RequestBody UserForm form){
        return ResponseEntity.ok(userService.updateMe(principal,form));
    }
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordForm request, Principal connectedUser) {
        return ResponseEntity.ok(userService.changePassword(request, connectedUser));
    }


}
