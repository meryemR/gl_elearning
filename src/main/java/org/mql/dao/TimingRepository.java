package org.mql.dao;

import org.mql.models.Timing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimingRepository extends JpaRepository<Timing, Integer> {

}
