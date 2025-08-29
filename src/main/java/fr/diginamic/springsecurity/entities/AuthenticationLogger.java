package fr.diginamic.springsecurity.entities;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationLogger {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        System.out.println("==> Connexion réussie : " + event.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
        System.out.println("==> Échec de connexion : " + event.getAuthentication().getName());
    }
}