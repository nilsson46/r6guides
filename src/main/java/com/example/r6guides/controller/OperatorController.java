package com.example.r6guides.controller;


import com.example.r6guides.models.Operator;
import com.example.r6guides.service.OperatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class OperatorController {

    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    // Get all operators
    @GetMapping()
    public ResponseEntity<List<Operator>> getAllOperators() {
        return ResponseEntity.ok(operatorService.getAll());
    }

    // Get operator by id
    @GetMapping("/{id}")
    public ResponseEntity<Operator> getOperatorById(@PathVariable Long id) {
        return ResponseEntity.ok(operatorService.findById(id));
    }

    // Get operator by name
    @GetMapping("/{name}")
    public ResponseEntity<Operator> getOperatorByName(@PathVariable String name) {
        return ResponseEntity.ok(operatorService.getOperatorByName(name));
    }

    // Create operator
    @PostMapping()
    public ResponseEntity<Operator> createOperator(@RequestBody Operator operator) {
        return ResponseEntity.ok(operatorService.createOperator(operator));
    }

    // Update operator
    @PutMapping("/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable Long id, @RequestBody Operator operator) {
        return ResponseEntity.ok(operatorService.updateOperator(id, operator));
    }

    // Delete operator by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Long id) {
        operatorService.deleteOperator(id);
        return ResponseEntity.noContent().build();
    }
}
