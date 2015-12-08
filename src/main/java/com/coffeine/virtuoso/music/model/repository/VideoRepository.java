/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Repository :: Video    *** *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.Video;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface VideoRepository extends JpaRepository <Video, Long > {

    /// *** Methods     *** ///
}