package pe.edu.upeu.sysalmacen.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    // Declarar la constante como estática y corregir el tipo long
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L; // 5 horas

    @Value("${jwt.secret}") // Carga la clave secreta desde la configuración
    private String secret;

    // Generación del token con la información del usuario
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))); // ADMIN, USER, DBA
        claims.put("test", "syscenterlife-value-test");  // Otro dato de ejemplo

        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Método privado para generar el token a partir de los claims y el nombre de usuario
    private String doGenerateToken(Map<String, Object> claims, String username) {
        // Asegurarse de que la clave secreta sea lo suficientemente segura
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key)
                .compact();
    }

    // Métodos utilitarios para extraer datos del token

    // Obtener todos los claims del token
    public Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
        
        // Actualizamos aquí para usar parserBuilder() en lugar de parser()
        return Jwts.parserBuilder()
                .setSigningKey(key)  // Establecemos la clave secreta
                .build()  // Creamos el objeto parser
                .parseClaimsJws(token) // Parseamos el JWT
                .getBody(); // Retornamos los claims
    }

    // Obtener un claim específico del token usando un Function
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Obtener el nombre de usuario desde el token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Obtener la fecha de expiración del token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Validar el token asegurándose de que el nombre de usuario coincida y que no haya expirado
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
