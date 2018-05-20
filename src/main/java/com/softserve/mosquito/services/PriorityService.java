package com.softserve.mosquito.services;

import java.util.List;

import com.softserve.entities.Priority;
import com.softserve.mosquito.exceptions.EntityNotFoundException;
import com.softserve.mosquito.repositories.PriorityRepo;

public class PriorityService {
    private PriorityRepo priorityRepo = new PriorityRepo();

    public PriorityService() {
    }

    public Priority create(Priority priority) {
        return priorityRepo.create(priority);
    }

    public Priority read(Long id){
        Priority read = priorityRepo.read(id);
        if (read == null) {
            throw new EntityNotFoundException("Priority with this Id " + id + " didn't found ");
        }else {
            return read;
        }
    }

    public Priority update(Priority priority) {
        return priorityRepo.update(priority);
    }

    public void delete(Priority priority) {
        priorityRepo.delete(priority);
    }

    public List<Priority> readAll() {
        return priorityRepo.readAll();
    }
}
