package com.uade.api.repositories;

import com.uade.api.models.ReclamoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReclamoRepository extends JpaRepository<ReclamoModel, Long> {
}
