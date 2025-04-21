package pe.edu.upeu.sysalmacen.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CORS.class);
    private static final List<String> ALLOWED_ORIGINS = Arrays.asList(
            "https://example.com", 
            "https://another-allowed-origin.com"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización si es necesario (en este caso, no se requiere)
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");

        // Verificar si el origen está permitido
        if (isAllowedOrigin(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            logger.info("CORS allowed origin: {}", origin);  // Usando formato en lugar de concatenación
        } else {
            response.setHeader("Access-Control-Allow-Origin", "https://default-allowed-origin.com");
            logger.warn("CORS origin not allowed, defaulting to: {}", "https://default-allowed-origin.com");  // Usando formato en lugar de concatenación
        }

        // Métodos permitidos
        response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");

        // Cabeceras permitidas
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

        // Tiempo de vida de la respuesta CORS
        response.setHeader("Access-Control-Max-Age", "3600");

        // Manejar las solicitudes OPTIONS (pre-vuelo)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res); // Continuar con el procesamiento de la solicitud
        }
    }

    // Método para verificar si el origen está permitido
    private boolean isAllowedOrigin(String origin) {
        return ALLOWED_ORIGINS.contains(origin); // Verifica si el origen está en la lista de permitidos
    }

    @Override
    public void destroy() {
        // Limpiar recursos si es necesario (actualmente no se necesitan recursos que liberar)
    }
}
