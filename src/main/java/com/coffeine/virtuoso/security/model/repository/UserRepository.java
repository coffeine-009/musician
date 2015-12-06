/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/5/15 2:04 PM
 */

package com.coffeine.virtuoso.security.model.repository;

import com.coffeine.virtuoso.security.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface for work with persistence layout.
 *
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /// *** Methods     *** ///
    /**
     * Find user by email and hash os password.
     *
     * @param username    Username of user(e-mail).
     * @param password    Hash of a password.
     *
     * @return User
     */
    @Query(
        "SELECT " +
            "u " +
        "FROM " +
            "User u " +
            "LEFT JOIN " +
            "u.emails e " +
            "LEFT JOIN " +
            "u.access a " +
        "WHERE " +
            "e.address = :username " +
            "AND " +
            "a.password = :password"
    )
    User findByUsernameAndPassword(
        @Param( "username" )
        String username,

        @Param( "password" )
        String password
    );

    /**
     * Find user by email.
     *
     * @param username    Username of user(e-mail).
     *
     * @return User
     */
    @Query(
        "SELECT " +
            "u " +
        "FROM " +
            "User u " +
            "LEFT JOIN " +
            "u.emails e " +
        "WHERE " +
            "e.address = :username"
    )
    User findByUsername( @Param( "username" ) String username );
}
