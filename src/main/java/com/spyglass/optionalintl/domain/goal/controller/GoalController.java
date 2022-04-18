package com.spyglass.optionalintl.domain.goal.controller;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
import com.spyglass.optionalintl.domain.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goal")
@Slf4j
public class GoalController {

    private GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Goal>> getAll() throws GoalNotFoundException {
        Iterable<Goal> all = goalService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goal> requestGoal(@PathVariable Long id) throws GoalNotFoundException {
        Goal response = goalService.findById(id);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Goal> create(@RequestBody Goal goal){
        goal = goalService.create(goal);
        return new ResponseEntity<>(goal,HttpStatus.CREATED);
    }


}
