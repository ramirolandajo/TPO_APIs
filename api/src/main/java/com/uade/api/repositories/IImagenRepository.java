package com.uade.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.api.models.ImagenModel;
public interface IImagenRepository extends JpaRepository<ImagenModel, Long> {

}
