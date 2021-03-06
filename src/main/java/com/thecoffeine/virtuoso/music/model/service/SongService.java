/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service;

import com.thecoffeine.virtuoso.music.model.entity.Song;

import java.util.List;
import java.util.Set;

/**
 * Service for work with song.
 *
 * @version 1.0
 */
public interface SongService {

    //- SECTION :: MAIN -//
    /**
     * Get list of Song for page.
     *
     * @param page Number of page for return.
     * @param limit Count items per page.
     *
     * @return List of found songs
     */
    List<Song> findAll( int page, int limit );

    /**
     * Create a new song.
     *
     * @param song Data for create.
     *
     * @return Song
     */
    Song create( Song song );

    /**
     * Get song by ID.
     *
     * @param id Identificator for search.
     *
     * @return Song
     */
    Song find( Long id );

    /**
     * Find songs by their ids.
     *
     * @param songsIds    List of ids.
     *
     * @return List of songs.
     */
    Set<Song> findByIds( Set<Long> songsIds );

    /**
     * Update.
     *
     * @param song Data for update.
     *
     * @return Song
     */
    Song update( Song song );

    /**
     * Delete song.
     *
     * @param id Unique identificator.
     */
    void delete( Long id );
}
