package com.cursojava.curso.dao;

import com.cursojava.curso.Models.Usuario;

import java.util.List;

public interface UsuarioDao {


    void eliminar(Long id);

    List<Usuario> getUsuarios();


    void registrar(Usuario usuario);

    Usuario obtenerUsuarioCredenciales(Usuario usuario);

    void editar(Usuario usuario);
}
