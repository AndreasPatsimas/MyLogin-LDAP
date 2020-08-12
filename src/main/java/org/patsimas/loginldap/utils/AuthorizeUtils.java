package org.patsimas.loginldap.utils;

import org.patsimas.loginldap.exceptions.authorization.AuthorizationFailedException;
import org.springframework.util.ObjectUtils;

import java.security.Principal;

public class AuthorizeUtils {

    public static void authorizeRequest(String username, Principal principal){

        if(ObjectUtils.isEmpty(principal) || !principal.getName().equals(username))
            throw new AuthorizationFailedException("Unauthorized for this action");
    }
}
