package pe.edu.upeu.sysalmacen.servicio;

import java.util.List;

public interface IUsuarioRolService {
    List<UsuarioRol> findOneByUsuarioUser(String user);
    UsuarioRol save(UsuarioRol ur);
}
