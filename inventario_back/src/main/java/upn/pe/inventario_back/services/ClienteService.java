package upn.pe.inventario_back.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upn.pe.inventario_back.models.ClienteModel;
import upn.pe.inventario_back.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repositorioCliente;

    public ArrayList<ClienteModel> listarCliente(){
        return (ArrayList<ClienteModel>) repositorioCliente.findAll();
    }

    public ClienteModel registrarCliente(ClienteModel cli){
        return repositorioCliente.save(cli);
    }

    public Optional<ClienteModel> consultarPorId(int id){
        return repositorioCliente.findById(id);
    }

    public ClienteModel actualizarCliente(ClienteModel cliente, Integer id){
        ClienteModel c = repositorioCliente.findById(id).get();
        c.setRazon_social(cliente.getRazon_social());
        c.setDireccion(cliente.getDireccion());
        c.setTelefono(cliente.getTelefono());
        c.setRuc(cliente.getRuc());
        c.setEmail(cliente.getEmail());
        repositorioCliente.save(c);
        return c;
    }

    public Boolean eliminarCliente(Integer id){
        try{
            repositorioCliente.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


}
