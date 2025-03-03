package com.example.r6guides.service;

import com.example.r6guides.models.Operator;
import com.example.r6guides.repository.OperatorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    //Get all operators
    public List<Operator> getAll() {
        return operatorRepository.findAll();
    }

    //Get operator by id
    public Operator findById(Long id) {
        return operatorRepository.findById(id).orElse(null);
    }

    // Get operator by name
    public Operator getOperatorByName(String name) {
        return operatorRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Operator not found with name: " + name));
    }

    // Create operator

    public Operator createOperator(Operator operator) {
        return operatorRepository.save(operator);
    }

    // Update operator
    public Operator updateOperator(Long id, Operator operator) {
        Operator existingOperator = operatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Operator not found with id: " + id));

        existingOperator.setName(operator.getName());
        existingOperator.setType(operator.getType());
        existingOperator.setOrganization(operator.getOrganization());
        existingOperator.setDescription(operator.getDescription());
        existingOperator.setPrimaryWeapon(operator.getPrimaryWeapon());
        existingOperator.setSecondaryWeapon(operator.getSecondaryWeapon());
        existingOperator.setGadget(operator.getGadget());
        existingOperator.setAbility(operator.getAbility());
        existingOperator.setImageUrl(operator.getImageUrl());

        return operatorRepository.save(existingOperator);
    }
    // Delete operator by id
    public void deleteOperator(Long id) {
        if (!operatorRepository.existsById(id)) {
            throw new EntityNotFoundException("Operator not found with id: " + id);
        }
        operatorRepository.deleteById(id);
    }
}
