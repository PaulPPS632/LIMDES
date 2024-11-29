package upn.pe.inventario_back.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upn.pe.inventario_back.models.ClienteModel;
import upn.pe.inventario_back.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService cs;

    @GetMapping
    public ArrayList<ClienteModel> listarTodos(){
        return cs.listarCliente();
    }

    @PostMapping
    public ClienteModel registrar(@RequestBody ClienteModel clie){
        clie.setFecha_registro(LocalDateTime.now());
        return cs.registrarCliente(clie);
    }
    
    @GetMapping(path= "/{id}")
    public  Optional<ClienteModel> obtenercliente(@PathVariable("id") int id){
        return cs.consultarPorId(id);
    }

    @PutMapping(path= "/{id}")
    public  ClienteModel actualizar( @RequestBody ClienteModel cliente, @PathVariable("id") Integer id){
        return cs.actualizarCliente(cliente, id);
    }

    @CrossOrigin(origins = "http://localhost:4200")    
    @DeleteMapping(path= "/{id}")
    public  String eliminar(@PathVariable("id") Integer id){
        boolean r = this.cs.eliminarCliente(id);
        if(r){
            return "Curso Eliminado";
        }
        else{
            return "no se pudo eliminar cliente";
        }
    }
}
