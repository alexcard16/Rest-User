package com.cursojava.curso.Controllers;
import com.cursojava.curso.Models.Usuario;
import com.cursojava.curso.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuariodao;

    @Autowired
    private com.cursojava.curso.utils.JWTUtil jwtUtils;

    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

    Usuario usuarioLog = usuariodao.obtenerUsuarioCredenciales(usuario);
        if (usuarioLog != null){
        String tokenJwt = jwtUtils.create(String.valueOf(usuarioLog.getId()),usuarioLog.getEmail());
        return tokenJwt;
        }else {

            return "fail";
        }


    }

}
