/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/8/15 8:57 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.entity.StaffType;
import com.coffeine.virtuoso.music.model.persistence.mock.StaffTypeMock;
import com.coffeine.virtuoso.music.model.service.StaffTypeService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for StaffTypeController.
 *
 * @version 1.0
 */
public class StaffTypeControllerTest extends AbstractRestControllerTest {

    @Mock
    private StaffTypeService staffTypeService;

    @InjectMocks
    private StaffTypeController staffTypeController;


    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup(
            this.staffTypeController
        ).build();
    }

    /**
     * Test get list of staff types.
     *
     * @throws Exception
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffTypeService.findAll( anyInt(), anyInt() ) ).thenReturn( StaffTypeMock.findAll() );

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/staffs/types?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/staffs/types/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Mock -//
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( null );

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/staffs/types/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test create a staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffTypeService.create( any( StaffType.class ) ) ).thenReturn( StaffTypeMock.find() );

        // Success. Get list of styles
        this.mockMvc.perform(
            post( "/music/staffs/types" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() );
    }

    /**
     * Test create a staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Mock -//
        doThrow( DataIntegrityViolationException.class ).when(
            this.staffTypeService
        ).create( any( StaffType.class ) );

        // Success. Get list of styles
        this.mockMvc.perform(
            post( "/music/staffs/types" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isConflict() );
    }

    /**
     * Test update a staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Mock -//
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );
        when( this.staffTypeService.update( any( StaffType.class ) ) ).thenReturn( StaffTypeMock.find() );

        // Success. Get list of styles
        this.mockMvc.perform(
            put( "/music/staffs/types/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test update a staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Mock -//
        when( this.staffTypeService.find( anyLong() ) ).thenReturn( StaffTypeMock.find() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.staffTypeService
        ).create( any( StaffType.class ) );

        // Success. Get list of styles
        this.mockMvc.perform(
            put( "/music/staffs/types/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );//FIXME: 409
    }

    /**
     * Test delete a staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mock -//
        doNothing().when( this.staffTypeService ).delete( anyLong() );

        // Success. Get list of styles
        this.mockMvc.perform(
            delete( "/music/staffs/types/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test delete a staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mock -//
        doThrow( EmptyResultDataAccessException.class ).when(
            this.staffTypeService
        ).delete( anyLong() );

        // Success. Get list of styles
        this.mockMvc.perform(
            delete( "/music/staffs/types/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }
}
