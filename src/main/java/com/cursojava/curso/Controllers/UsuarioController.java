package com.cursojava.curso.Controllers;
import com.cursojava.curso.Models.Usuario;
import com.cursojava.curso.dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuariodao;

    @Autowired
    private com.cursojava.curso.utils.JWTUtil jwtUtils;

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(2,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuariodao.registrar(usuario);

    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.PUT)
    public void editarUsuarios(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(2,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuariodao.editar(usuario);

    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtils.getKey(token);
        return (usuarioId != null);

    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization")String token){
       if(!validarToken(token)){return null;}
        return usuariodao.getUsuarios();

    }

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id, @RequestHeader(value="Authorization")String token){
        if(!validarToken(token)){return;}
        usuariodao.eliminar(id);


    }
}
