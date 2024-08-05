package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.dto.UserDto;
import com.example.springsecurity.model.entity.User;
import com.example.springsecurity.model.form.ChangePasswordForm;
import com.example.springsecurity.model.form.UpdateForm;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.util.Ultilities;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll() .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->  new IllegalArgumentException("User not exist"));
        return UserDto.toDto(user) ;
    }

    @Override
    public List<UserDto> searchUser(String query) {
        return userRepository.searchUser(query)
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String update(Long id, UpdateForm form) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User not exist"));
        user.setFullName(form.getFullName());
        user.setEmail(form.getEmail());
        user.setPhone(form.getPhone());
        user.setAddress(form.getAddress());
        userRepository.save(user);
        return "Update user completed";
    }

    @Override
    public String delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User not exist"));
        userRepository.deleteById(id);
        return "Delete user complete";
    }

    @Override
    public UserDto getMe(Principal principal) {
        User user = Ultilities.getMe(principal);
        return UserDto.toDto(user);
    }
    @Override
    public String changePassword(ChangePasswordForm request, Principal connectedUser) {
        User user = Ultilities.getMe(connectedUser);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Password are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
        return "Change password complete";
    }
    @Override
    public String updateMe(Principal principal, UpdateForm form) {
    User user = Ultilities.getMe(principal);
        user.setFullName(form.getFullName());
        user.setEmail(form.getEmail());
        user.setPhone(form.getPhone());
        user.setAddress(form.getAddress());
        userRepository.save(user);
        return "Update user completed";
    }
}
