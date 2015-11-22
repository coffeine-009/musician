/// *** Security :: Model :: Entity :: Roles    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-07-28 16:46:06 :: 2014-07-28 16:54:39
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.security.model.entity;

/**
 * List of roles in system.
 * //FIXME: use persistence layout
 *
 * @version 1.0
 */
public enum Roles {
    ADMINISTRATOR,
    MODERATOR,
    MUSICIAN,
    COMPOSER,
    POET,
    TEACHER,
    STUDENT
}
