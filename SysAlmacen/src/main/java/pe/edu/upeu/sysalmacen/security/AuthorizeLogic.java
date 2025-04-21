package pe.edu.upeu.sysalmacen.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizeLogic {

    public boolean hasAccess(String path) {
        boolean result = false;

        String methodRole = switch (path) {
            case "findAll" -> "ADMIN";
            case "findById", "getBydId" -> "USER,DBA";
            default -> "ROOT";
        };

        String[] methodRoles = methodRole.split(",");  // Fixed array declaration

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username is: {}", auth.getName());  // Changed to parameterized logging

        for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
            String roleUser = grantedAuthority.getAuthority();
            log.info("Role is: {}", roleUser);  // Changed to parameterized logging

            for (String role : methodRoles) {
                if (roleUser.equalsIgnoreCase(role)) {
                    result = true;
                    break;
                }
            }
            if (result) {  // Early exit if match found
                break;
            }
        }

        return result;
    }
}