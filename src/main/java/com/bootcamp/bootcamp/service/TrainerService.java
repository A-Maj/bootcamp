package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public List<Trainer> getTrainersSortedByLastName() {
        return trainerRepository.findAllByOrderByLastName();
//        return trainerRepository.getTrainers("Darski");
    }

    public Optional<Trainer> getTrainer(long id) {
        //return trainerRepository.getOne(id);
        return trainerRepository.findById(id);
    }

    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public void delete(long id) {
        trainerRepository.deleteById(id);
    }
}
