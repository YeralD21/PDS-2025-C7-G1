package pe.edu.upeu.sysalmacen.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
    private Long idUsuario;
    
    @NotNull
    private String user;
    
    @NotNull
    private String estado;
    
    private String token;

    public record CredencialesDto(String user, char[] clave) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CredencialesDto that = (CredencialesDto) o;
            return user.equals(that.user) && Arrays.equals(clave, that.clave);
        }

        @Override
        public int hashCode() {
            int result = user.hashCode();
            result = 31 * result + Arrays.hashCode(clave);
            return result;
        }

        @Override
        public String toString() {
            return "CredencialesDto{user='" + user + "', clave=[PROTECTED]}";
        }
    }

    public record UsuarioCrearDto(String user, char[] clave, String rol, String estado) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UsuarioCrearDto that = (UsuarioCrearDto) o;
            return user.equals(that.user) && 
                   Arrays.equals(clave, that.clave) &&
                   rol.equals(that.rol) &&
                   estado.equals(that.estado);
        }

        @Override
        public int hashCode() {
            int result = user.hashCode();
            result = 31 * result + Arrays.hashCode(clave);
            result = 31 * result + rol.hashCode();
            result = 31 * result + estado.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "UsuarioCrearDto{user='" + user + 
                   "', clave=[PROTECTED], rol='" + rol + 
                   "', estado='" + estado + "'}";
        }
    }
}