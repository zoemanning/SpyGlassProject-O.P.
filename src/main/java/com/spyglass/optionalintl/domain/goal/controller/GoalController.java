package com.spyglass.optionalintl.domain.goal.controller;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
import com.spyglass.optionalintl.domain.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goal")
@Slf4j
public class GoalController {

    private final Logger logger = LoggerFactory.getLogger(GoalController.class);

    private GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("")
    public ResponseEntity<List<Goal>> getAllGoals() throws GoalNotFoundException {
        List<Goal> allGoals = goalService.findAll();
        ResponseEntity<List<Goal>> response = new ResponseEntity<>(allGoals,HttpStatus.OK);
        return response;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Goal> requestGoal(@PathVariable Long id) throws GoalNotFoundException {
        try {
            Goal updatedGoal = goalService.findById(id);
            return new ResponseEntity(updatedGoal, HttpStatus.OK);
        } catch (GoalNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                            .build();
        }

    }

    @PostMapping("")
    public ResponseEntity<Goal> create(@RequestBody Goal goal){
        goal = goalService.create(goal);
        return new ResponseEntity<>(goal,HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateGoal ( @RequestBody Goal goal){
        try{
            Goal updatedGoal = goalService.update(goal);
            ResponseEntity response = new ResponseEntity(updatedGoal, HttpStatus.OK);
            return response;
        } catch (GoalNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoalByID (@PathVariable Long id){
        try {
            goalService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (GoalNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();

        }
    }

    @DeleteMapping("/title")
    public ResponseEntity<?> deleteGoalByTitle (@RequestParam(value="title") String title){
        try {
            goalService.deleteGoalByTitle(title);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (GoalNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}



