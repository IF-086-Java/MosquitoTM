package com.softserve.mosquito.services;

import com.softserve.mosquito.enitities.Priority;
import com.softserve.mosquito.enitities.Specialization;
import com.softserve.mosquito.exceptions.EntityNotFoundException;
import com.softserve.mosquito.repositories.SpecializationRepo;

import java.util.List;

public class SpecializationService {
    private SpecializationRepo specializationRepo = new SpecializationRepo();

    public SpecializationService(){
    }

    public Specialization create(Specialization specialization) {
        return specializationRepo.create(specialization);
    }

    public Specialization read(Long id) {
        Specialization read = specializationRepo.read(id);
        if (read == null) {
            throw new EntityNotFoundException("Specialization with this Id " + id + " didn't found ");
        }else {
            return read;
        }
    }

    public Specialization update(Specialization specialization) {
        return specializationRepo.update(specialization);
    }

    public void delete(Specialization specialization) {
        specializationRepo.delete(specialization);
    }

    public List<Specialization> readAll() {
        return specializationRepo.readAll();
    }
}

