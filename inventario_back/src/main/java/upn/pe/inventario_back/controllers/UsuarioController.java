package upn.pe.inventario_back.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import upn.pe.inventario_back.models.UsuarioModel;
import upn.pe.inventario_back.models.dto.LoginRequest;
import upn.pe.inventario_back.models.dto.UsuarioRequest;
import upn.pe.inventario_back.models.dto.UsuarioResponse;
import upn.pe.inventario_back.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /*
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/login")
    public UsuarioModel login(@RequestBody UsuarioModel usuario) {
        return usuarioService.autenticar(usuario);
        
    }
*/
    @PostMapping("/register")
    public UsuarioResponse create(@RequestBody UsuarioRequest usuario) {
        return usuarioService.createUsuario(usuario);
    }

    // Listar todos los usuarios
    @GetMapping("/listar")
    public List<UsuarioResponse> list() {
        return usuarioService.listUsuarios();
    }

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.login(loginRequest);
    }
}
