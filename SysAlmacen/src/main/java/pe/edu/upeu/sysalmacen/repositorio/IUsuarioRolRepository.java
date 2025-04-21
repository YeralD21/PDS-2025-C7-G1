package pe.edu.upeu.sysalmacen.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.sysalmacen.modelo.UsuarioRol;
import pe.edu.upeu.sysalmacen.modelo.UsuarioRolPK;

import java.util.List;

public interface IUsuarioRolRepository extends ICrudGenericoRepository<UsuarioRol, UsuarioRolPK>{
    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.usuario.user = :user")//Consulta JPQL
    List<UsuarioRol> findOneByUsuarioUser(@Param("user") String user);
}

