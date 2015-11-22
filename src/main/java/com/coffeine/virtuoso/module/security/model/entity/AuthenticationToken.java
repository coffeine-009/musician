/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 08/22/14 3:29 PM
 */

package com.coffeine.virtuoso.module.security.model.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Token for authenticated users.
 *
 * @version 1.0
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

    /// *** Properties  *** ///
    /**
     * Principal
     */
    protected final Object principal;

    /**
     * Credentials
     */
    protected Object credential;


    /// *** Methods     *** ///
    /**
     * Constructor for create new Auth token
     *
     * @param principal
     * @param credential
     * @param authorities
     */
    public AuthenticationToken(
        Object principal,
        Object credential,
        Collection < ? extends GrantedAuthority > authorities
    ) {
        //- Delegate authorities -//
        super( authorities );

        //- Init -//
        this.principal = principal;
        this.credential = credential;

        //- Mark user as authenticated -//
        super.setAuthenticated( true );
    }

    //- SECTION :: GET -//
    /**
     * Get Credentials
     *
     * @return Object
     */
    @Override
    public Object getCredentials() {
        return this.credential;
    }

    /**
     * Get principal
     *
     * @return Object
     */
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
