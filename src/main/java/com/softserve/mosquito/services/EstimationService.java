package com.softserve.mosquito.services;

import com.softserve.mosquito.enitities.Estimation;
import com.softserve.mosquito.repositories.EstimationRepo;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.util.List;

public class EstimationService {
    private ServletContext context;
    private EstimationRepo repo;

    public EstimationService(ServletContext context) {
        this.context = context;
        this.repo = new EstimationRepo((Connection) context.getAttribute("DBConnection"));
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
