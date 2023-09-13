package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.AnswerVoteDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/")
    public AnswerDTO addNewAnswer(@RequestBody NewAnswerDTO newAnswerDTO) {
        return answerService.addNewAnswer(newAnswerDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAnswer(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }

    @PutMapping("/vote")
    public boolean voteToAnswer(@RequestBody AnswerVoteDTO answerVoteDTO) {
        return answerService.voteToAnswer(answerVoteDTO);
    }
}
