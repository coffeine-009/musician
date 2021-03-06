/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/8/15 9:03 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.music.model.entity.Song;
import com.thecoffeine.virtuoso.music.model.entity.Staff;
import com.thecoffeine.virtuoso.music.model.entity.StaffType;
import com.thecoffeine.virtuoso.music.model.entity.Style;
import com.thecoffeine.virtuoso.music.model.service.SongService;
import com.thecoffeine.virtuoso.music.model.service.StaffService;
import com.thecoffeine.virtuoso.music.model.service.StaffTypeService;
import com.thecoffeine.virtuoso.music.model.service.StyleService;
import com.thecoffeine.virtuoso.music.view.form.StaffForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for work with staffs.
 *
 * @version 1.0
 */
@RestController
@RequestMapping( value = "/music/staffs" )
public class StaffController {

    /// *** Properties  *** ///
    @Autowired
    private SongService songService;

    @Autowired
    private StaffTypeService staffTypeService;

    @Autowired
    private StyleService styleService;

    /**
     * Service for work with staffs.
     */
    @Autowired
    private StaffService staffService;


    /// *** Methods     *** ///
    /**
     * Find all and filtering by page.
     *
     * @param page     Number of page.
     * @param limit    Count items per page.
     *
     * @return List of staffs.
     */
    @RequestMapping( method = RequestMethod.GET )
    @ResponseBody
    public List<Staff> findAllAction(
        @RequestParam( value = "page", required = false, defaultValue = "1" )
        int page,

        @RequestParam( value = "limit", required = false, defaultValue = "10" )
        int limit
    ) {
        return this.staffService.findAll(
            Math.min( page - 1, 0 ),
            limit
        );
    }

    /**
     * Create a new Staff.
     *
     * @param staffForm    Input.
     * @param response     Use for work with HTTP.
     *
     * @return Create staff.
     */
    @RequestMapping( method = RequestMethod.POST )
    @ResponseBody
    public Staff createAction(
        @Valid
        @RequestBody
        StaffForm staffForm,

        HttpServletResponse response
    ) {
        try {
            //- Search depended entities -//
            Song song = this.songService.find( staffForm.getSongId() );
            Style style = this.styleService.find( staffForm.getStyleId() );
            StaffType staffType = this.staffTypeService.find( staffForm.getStaffTypeId() );

            //- Check -//
            notNull( song );
            notNull( style );
            notNull( staffType );

            //- Set status -//
            response.setStatus( HttpServletResponse.SC_CREATED );

            //- Create a new Staff -//
            return this.staffService.create(
                new Staff(
                    song,
                    staffType,
                    style,
                    staffForm.getLocale()
                )
            );
        } catch ( IllegalArgumentException | DataIntegrityViolationException e ) {
            //- Failure. Cannot find depended entities -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    /**
     * Find.
     *
     * @param id          Id of staff.
     * @param response    Use for work with HTTP.
     *
     * @return Found staff.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public Staff find(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Search staff -//
            Staff staff = this.staffService.find( id );

            //- Check -//
            notNull( staff );

            return staff;
        } catch ( IllegalArgumentException e ) {
            //- Failure. Cannot find staff -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }

        return null;
    }

    /**
     * Update staff.
     *
     * @param id           Id of staff.
     * @param staffForm    Input.
     * @param response     Use for work with HTTP.
     *
     * @return Updated staff.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public Staff updateAction(
        @PathVariable( "id" )
        Long id,

        @Valid
        @RequestBody
        StaffForm staffForm,

        HttpServletResponse response
    ) {
        try {
            Staff staff = this.staffService.find( id );

            notNull( staff );

            //- Search depended entities -//
            Song song = this.songService.find( staffForm.getSongId() );
            Style style = this.styleService.find( staffForm.getStyleId() );
            StaffType staffType = this.staffTypeService.find( staffForm.getStaffTypeId() );

            //- Check -//
            notNull( song );
            notNull( style );
            notNull( staffType );

            staff.setSong( song );
            staff.setStyle( style );
            staff.setStaffType( staffType );

            return this.staffService.update( staff );
        } catch ( IllegalArgumentException | DataIntegrityViolationException e ) {
            //- Failure. Cannot update staff -//
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
        }

        return null;
    }

    /**
     * Delete.
     *
     * @param id          Id of staff.
     * @param response    Use for work with HTTP.
     */
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseBody
    public void deleteAction(
        @PathVariable( "id" )
        Long id,

        HttpServletResponse response
    ) {
        try {
            //- Delete staff -//
            this.staffService.delete( id );
        } catch ( DataAccessException e ) {
            //- Failure. Cannot find staff -//
            response.setStatus( HttpServletResponse.SC_NOT_FOUND );
        }
    }
}
