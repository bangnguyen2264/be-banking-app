package com.example.springsecurity.service;

import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.form.ChangePasswordForm;
import com.example.springsecurity.model.form.UserForm;

import java.security.Principal;
import java.util.List;

public interface UserService {
    public List<UserDto> getAll();
    public List<UserDto> searchUser(String query);
    public UserDto getById(Long id);
    public String changePassword(ChangePasswordForm request, Principal connectedUser) ;
    public String update(Long id, UserForm form);
    public String delete(Long id);
    public UserDto getMe(Principal principal);
    public String updateMe(Principal principal, UserForm form);

}
