package com.softserve.mosquito.services;

import com.softserve.mosquito.enitities.Status;
import com.softserve.mosquito.repositories.StatusRepo;

import java.util.List;

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
            //throw new EntityNotFoundException("tatus with this Id " + id + " didn't found ");
        }else {
            return read;
        }
        return null;
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
