package com.example.finalrober;

import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.Date;

public class Tarea {
    private int id;
    private String categoria;
    private String titulo;
    private LocalDate fecha;
    private String estado;

    public Tarea(int id, String categoria, String titulo, LocalDate fecha, String estado) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
