package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.*;
import com.codecool.stackoverflowtw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserCardDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserPageDTO getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
    @GetMapping("/auth")
    public UserPageDTO getUserByNameAndPassword(@RequestParam String name, @RequestParam String password,
                                                @RequestParam String colorHex) {
        return userService.getUserByNameAndPassword(new NewUserDTO(name, password, colorHex));
    }

    @PostMapping("/")
    public int addNewUser(@RequestBody NewUserDTO user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/{id}/questions")
    public List<QuestionCardDTO> getQuestionsByUser(@PathVariable int id) { return userService.getQuestionsByUser(id);}

    @GetMapping("/{id}/answers")
    public List<AnswerDTO> getAnswersByUser(@PathVariable int id) { return userService.getAnswersByUser(id);}
}
