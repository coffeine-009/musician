/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.security.model.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import static org.springframework.util.Assert.notNull;

/**
 * SongController.
 *
 * @version 1.0
 */
@Controller
@RequestMapping( value = "/music/songs" )
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;


    //- SECTION :: ACTIONS -//
    /**
     * GET list of songs.
     *
     * @param page Number of page.
     * @param limit Count items per page.
     *
     * @return List of songs for requested page
     */
    @GET
    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( value = HttpStatus.OK )
    @ResponseBody
    public List<Song> listAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        //- Get list of song from persistence layout -//
        return this.songService.findAll(
            Math.max( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create new song.
     *
     * @param song    Song for create.
     *
     * @return Song
     */
    @POST
    @RequestMapping(
        method = RequestMethod.POST,
        produces = {
            "application/json"
        }
    )
    @ResponseBody
    public Song createAction(
        @RequestBody
        @Valid
        Song song,

        Locale locale
    ) {
        //- Save song -//
        return this.songService.create( song );
    }

    /**
     * Get data about song by ID.
     *
     * @param Id        Id of song.
     * @param response  UserService for work with HTTP.
     *
     * @return Song.
     */
    @GET
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Song readAction( 
        @PathVariable( value = "id" )
        Long Id,

        HttpServletResponse response
    ) {
        try {
            //- Search -//
            Song song = this.songService.find( Id );

            //- Check -//
            notNull( song );

            return song;
        } catch ( IllegalArgumentException e ) {
            //- Failure. cannot find song -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update song by ID.
     *
     * @param Id    Id of song.
     * @return Song
     */
    @Secured( "MUSICIAN" )
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public Song updateAction(
        @PathVariable( value = "id" )
        Long Id
    ) {
        //TODO: to implement
        return null;
    }

    /**
     * Delete song by ID.
     *
     * @param id        Id of song.
     * @param response  UserService for work with HTTP.
     */
    @Secured( "ADMINISTRATOR" )
    @DELETE
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseBody
    public void deleteAction(
        @PathVariable( value = "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            this.songService.delete( id );
        } catch ( EmptyResultDataAccessException e ) {
            //- Failure. Cannot find this song -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
