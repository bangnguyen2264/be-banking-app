package com.example.springsecurity.admin;

import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.form.UserForm;
import com.example.springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }


    @PutMapping("/user/update/{userId}")
    public ResponseEntity<String> update(@PathVariable Long userId, @RequestBody UserForm form){
        return ResponseEntity.ok(userService.update(userId,form));
    }
    @DeleteMapping("/user/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(userService.delete(id));
    }

}
