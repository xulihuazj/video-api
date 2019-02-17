package com.yinfeixing.video.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * This component returns the username of the authenticated user.
 *
 * @author Petri Kainulainen
 */
public class UsernameAuditorAware implements AuditorAware<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsernameAuditorAware.class);

    @Override
    public Optional<String> getCurrentAuditor() {
//        LOGGER.debug("Getting the username of authenticated user.");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            LOGGER.debug("Current user is anonymous. Returning null.");
//            return null;
//        }
//
//        String username = ((User) authentication.getPrincipal()).getUsername();
//        LOGGER.debug("Returning username: {}", username);
//
//        return username;
        return null;
    }
}
