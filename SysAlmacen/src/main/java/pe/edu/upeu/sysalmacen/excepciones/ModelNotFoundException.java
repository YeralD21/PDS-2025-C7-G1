package pe.edu.upeu.sysalmacen.excepciones;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ModelNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public ModelNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND); // Asigna NOT_FOUND como estado por defecto
    }

    public ModelNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}