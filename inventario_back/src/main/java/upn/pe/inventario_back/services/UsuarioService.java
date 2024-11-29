package upn.pe.inventario_back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import upn.pe.inventario_back.models.UsuarioModel;
import upn.pe.inventario_back.models.dto.LoginRequest;
import upn.pe.inventario_back.models.dto.UsuarioRequest;
import upn.pe.inventario_back.models.dto.UsuarioResponse;
import upn.pe.inventario_back.repositories.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Transactional
    public UsuarioResponse createUsuario(UsuarioRequest usuario) {
        // Codificar la contraseña antes de guardar
        //String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        UsuarioModel usuarionuevo = UsuarioModel.builder()
                .username(usuario.username())
                .password(usuario.password())
                .documento(usuario.documento())
                .nombre(usuario.nombre())
                .direccion(usuario.direccion())
                .telefono(usuario.telefono())
                .rol(usuario.rol())
                .build();
        UsuarioModel nuevoregistrado = usuarioRepository.save(usuarionuevo);

        return mapToUsuarioResponse(nuevoregistrado);
    }
    public List<UsuarioResponse> listUsuarios() {
        List<UsuarioModel> listusuarios = usuarioRepository.findAll();
        return listusuarios.stream().map(this::mapToUsuarioResponse).toList();
    }
    /*
    public UsuarioModel autenticar(UsuarioModel usr){
        UsuarioModel r = null;
        ArrayList<UsuarioModel> lista = (ArrayList<UsuarioModel>) usuarioRepository.findAll();
        for(UsuarioModel u:lista){
            if(u.getUsername().equals(usr.getUsername()) && 
                                        u.getPassword().equals(usr.getPassword())){
                r=u;
            }
        }
        return r;
    }
    */

    public UsuarioResponse login(LoginRequest loginRequest) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByUsername(loginRequest.username());


        UsuarioModel usuario = usuarioOpt.orElseThrow();

        // Comparar la contraseña ingresada con la almacenada
        /*
        if (passwordEncoder.matches(loginRequest.password(), usuario.getPassword())) {
            return "Login exitoso";
        } else {
            return "Contraseña incorrecta";
        }*/
        if (loginRequest.password().equals(usuario.getPassword())) {
            return mapToUsuarioResponse(usuario);
        }else {
            return UsuarioResponse.builder().build();
        }
    }
    private UsuarioResponse mapToUsuarioResponse(UsuarioModel usuario){
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .documento(usuario.getDocumento())
                .nombre(usuario.getNombre())
                .direccion(usuario.getDireccion())
                .telefono(usuario.getTelefono())
                .rol(usuario.getRol())
                .build();
    }
}
