/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/21/15 6:28 PM
 */

package com.coffeine.virtuoso.module.security.model.entity;

import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests of AuthenticationToken model.
 * @see AuthenticationToken
 *
 * @version 1.0
 */
public class AuthenticationTokenTest {

    /**
     * Test of constructor and accessors.
     */
    @Test
    public void testCreationSuccess() {

        //- Assumptions -//
        final String CREDENTIALS = "Unit";
        final String PRINCIPAL = "Test";
        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority( "UNIT_TEST" );

        //- Evaluation -//
        AuthenticationToken token = new AuthenticationToken(
            PRINCIPAL,
            CREDENTIALS,
            new ArrayList< SimpleGrantedAuthority >() {{
                add( authority );
            }}
        );

        //- Assertions -//
        assertEquals( CREDENTIALS, token.getCredentials() );
        assertEquals( PRINCIPAL, token.getPrincipal() );
        assertTrue( token.getAuthorities().contains( authority ) );
        //- Token must be created only for authenticated users -//
        assertTrue( token.isAuthenticated() );
    }
}
