package fr.diginamic.springsecurity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

public interface Authentification extends Principal, Serializable {
    Collection<GrantedAuthority> getAuthorities();
    Object getCredentials();
    Object getDetails();
    Object getPrincipal();
    boolean isAuthenticated();
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;
}
