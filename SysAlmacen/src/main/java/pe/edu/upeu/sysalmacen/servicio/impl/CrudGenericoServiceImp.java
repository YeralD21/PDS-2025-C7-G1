package pe.edu.upeu.sysalmacen.servicio.impl;

import pe.edu.upeu.sysalmacen.excepciones.CustomResponse;
import pe.edu.upeu.sysalmacen.excepciones.ModelNotFoundException;
import pe.edu.upeu.sysalmacen.repositorio.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.servicio.ICrudGenericoService;

import java.time.LocalDateTime;
import java.util.List;

public abstract class CrudGenericoServiceImp<T, K> implements ICrudGenericoService<T, K> {
    private static final String ID_NOT_FOUND_MSG = "ID NOT FOUND: ";
    
    // Método abstracto para obtener el repositorio
    protected abstract ICrudGenericoRepository<T, K> getRepo();

    @Override
    public T save(T entity) {
        return getRepo().save(entity);
    }

    @Override
    public T update(K id, T entity) {
        findById(id); // Reutiliza el método existente
        return getRepo().save(entity);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(K id) {
        return getRepo().findById(id)
                .orElseThrow(() -> new ModelNotFoundException(ID_NOT_FOUND_MSG + id));
    }

    @Override
    public CustomResponse delete(K id) {
        T entity = findById(id); // Reutiliza el método existente
        getRepo().delete(entity);
        
        return CustomResponse.builder()
                .statusCode(200)
                .datetime(LocalDateTime.now())
                .message("true")
                .details("Todo Ok")
                .build();
    }
}
