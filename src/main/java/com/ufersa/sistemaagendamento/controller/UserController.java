package com.ufersa.sistemaagendamento.controller;

import com.ufersa.sistemaagendamento.model.requests.UserRequest;
import com.ufersa.sistemaagendamento.model.entities.User;
import com.ufersa.sistemaagendamento.model.responses.UserResponse;
import com.ufersa.sistemaagendamento.service.interfaces.IUserService;
import com.ufersa.sistemaagendamento.service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UserResponse> AddUser(@RequestBody UserRequest userRequest){
        var response = userService.AddUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/usuarios/getall")
    public ResponseEntity<List<User>> GetAllUsers() {
        var response = userService.GetAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
