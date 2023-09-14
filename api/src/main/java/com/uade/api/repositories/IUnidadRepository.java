package com.uade.api.repositories;

import com.uade.api.models.UnidadModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadRepository extends JpaRepository<UnidadModel, Long> {
}
