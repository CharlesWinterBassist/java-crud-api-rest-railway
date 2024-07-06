package com.curso.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.apirest.Repositories.ProductoRepositorio;
import com.curso.apirest.Entities.Producto;


@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    @Autowired
    private ProductoRepositorio productoRepositorio ;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll() ;
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id)) ;
    }
    

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepositorio.save(producto) ;
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoDetalle) {
        Producto producto = productoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id)) ;

        producto.setNombre(productoDetalle.getNombre());
        producto.setPrecio(productoDetalle.getPrecio());

        return productoRepositorio.save(producto) ;
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto producto = productoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id)) ;

        productoRepositorio.delete(producto) ;

        return String.format("El producto con el ID: %s fue eliminado correctamente", id) ;
    }


}
