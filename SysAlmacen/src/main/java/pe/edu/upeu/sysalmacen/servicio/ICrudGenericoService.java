package pe.edu.upeu.sysalmacen.servicio;

import pe.edu.upeu.sysalmacen.excepciones.CustomResponse;
import java.util.List;

public interface ICrudGenericoService<E, K> {  // Cambié T por E y ID por K para cumplir con la convención
    E save(E e);
    E update(K k, E e);
    List<E> findAll();
    E findById(K k);
    CustomResponse delete(K k);
}
