package com.softserve.mosquito.services;

import com.softserve.entities.*;
import com.softserve.mosquito.repositories.EstimationRepo;

import java.util.List;

public class EstimationService {
    private EstimationRepo repo;

    public EstimationService() {
        this.repo = new EstimationRepo();
    }

    public List<Estimation> getAllEstimations() {
        return repo.readAll();
    }

    public Estimation getEstimationById(Long id) {
        return repo.read(id);
    }

    public Estimation createEstimation(int estimation) {
        return repo.create(new Estimation(estimation));
    }

    public void updateEstimation(Estimation estimation) {
        repo.update(estimation);
    }

    public void deleteEstimation(Estimation estimation) {
        repo.delete(estimation);
    }
}
