package org.mql.dao;

import org.mql.models.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<Streaming, Integer> {

	Streaming findFirstByOrderByIdDesc();

}
