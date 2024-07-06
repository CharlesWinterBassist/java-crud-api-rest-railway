package com.curso.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.apirest.Entities.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}
