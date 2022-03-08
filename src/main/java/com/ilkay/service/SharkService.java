package com.ilkay.service;

import com.ilkay.entity.Shark;
import com.ilkay.repo.SharkRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharkService implements ServiceMethods<Shark>{

    private SharkRepo repo;

    public SharkService(SharkRepo repo) {
        this.repo = repo;
    }

    @Override
    public Shark create(Shark shark) {
        return this.repo.save(shark);
    }

    @Override
    public List<Shark> readAll() {
        return this.repo.findAll();
    }

    @Override
    public Shark readById(long id) {
        Optional<Shark> getShark = this.repo.findById(id);
        if(getShark.isPresent()) {
            return getShark.get();
        }
        return null;
    }

    //When setting values, DO NOT set the id
    @Override
    public Shark update(long id, Shark shark) {
        Optional<Shark> existingShark = this.repo.findById(id);
        if(existingShark.isPresent()) {
            Shark exists = existingShark.get();
            exists.setAge(shark.getAge());
            exists.setGender(shark.getGender());
            exists.setHabitat(shark.getHabitat());
            exists.setName(shark.getName());

            return this.repo.saveAndFlush(exists);
        }
        return null;
    }

    //Deletes the id, and checks to see if it it still exists (should return true if it has worked)
    @Override
    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
