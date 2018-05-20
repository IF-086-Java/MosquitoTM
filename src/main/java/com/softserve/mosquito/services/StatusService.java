package com.softserve.mosquito.services;

import java.util.List;

import com.softserve.entities.Status;
import com.softserve.mosquito.exceptions.EntityNotFoundException;
import com.softserve.mosquito.repositories.StatusRepo;

public class StatusService {
    private StatusRepo statusRepo = new StatusRepo();

    public  StatusService(){
    }

    public Status create(Status status) {
        return statusRepo.create(status);
    }

    public Status read(Long id) {
        Status read = statusRepo.read(id);
        if (read == null) {
            throw new EntityNotFoundException("tatus with this Id " + id + " didn't found ");
        }else {
            return read;
        }
    }

    public Status update(Status status) {
        return statusRepo.update(status);
    }

    public void delete(Status status) {
        statusRepo.delete(status);
    }

    public List<Status> readAll() {
        return statusRepo.readAll();
    }


}
