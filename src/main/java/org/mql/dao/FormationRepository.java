package org.mql.dao;

import java.util.List;

import org.mql.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Integer>{
    public List<Formation> findAllByOrderByIdDesc();
}
