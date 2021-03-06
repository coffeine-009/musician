/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 3/20/16 10:49 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.thecoffeine.virtuoso.module.util.WithMockSecurityUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Functional tests for SongController.
 *
 * @version 1.0
 * @see StyleController
 */
public class FunctionalSongControllerTest extends AbstractRestControllerTest {

    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();
    }

    /**
     * Clean environment.
     */
    @After
    @Override
    public void tearDown() {
        //- Clean environment after run tests -//
    }


    /**
     * Test successful getting list of songs.
     *
     * @throws Exception    General application exception.
     */
    @Ignore
    @WithMockSecurityUser
    @Test
    public void testListActionSuccess() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs?page={page}&limit={limit}", 1, 10 )
                .with( security() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 2 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1, 2 ) ) )
            //- Song -//
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$[*].data", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffs", notNullValue() ) )
            .andExpect( jsonPath( "$[*].lyrics", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videos", notNullValue() ) )
            .andDo( document( "songs-list-example" ) );
    }

    /**
     * Test of retrieving song by id.
     * Success.
     *
     * @throws Exception    Exception    General application exception.
     */
    @Ignore
    @Test
    public void testRetrieveSongActionSuccess() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 1 )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id" ).value( 1 ) )
            //- Song -//
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$data", notNullValue() ) )
            .andExpect( jsonPath( "$staffs", notNullValue() ) )
            .andExpect( jsonPath( "$lyrics", notNullValue() ) )
            .andExpect( jsonPath( "$videos", notNullValue() ) )
            .andDo(
                document(
                    "songs-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of song." ),
                        fieldWithPath( "data" ).description( "Localized data of song." ),
                        fieldWithPath( "staffs" ).description( "List of musical notes." ),
                        fieldWithPath( "lyrics" ).description( "List of lyrics." ),
                        fieldWithPath( "videos" ).description( "List of videos." ),
                        fieldWithPath( "locale" ).description( "Locale of song." ),
                        fieldWithPath( "writeDate" ).description( "Write date of song." ),
                        fieldWithPath( "creation" ).description( "Time of creation of this record." )
                    )
                )
            );
    }

    /**
     * Test of retrieving song by id.
     * Failure.
     *
     * @throws Exception    General application exception.
     */
    @Ignore
    @Test
    public void testRetrieveSongActionFailure() throws Exception {

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 64 )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "songs-retrieve-failure-example" ) );
    }

    @Ignore
    @Test
    public void testCreateSongActionSuccess() throws Exception {
        //- Success. Create a new style -//
        this.mockMvc.perform(
            post( "/music/songs" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"data\": [ {" +
                            "\"title\": \"Rose\"," +
                            "\"locale\": \"en-US\"" +
                        "} ]," +
                        "\"staffs\": [ {" +
                            "\"composerIds\": [ 1 ]," +
                            "\"musicNotesTypeId\": 1," +
                            "\"styleId\": 1," +
                            "\"file\": \"<xml></xml>\"" +
                        "} ]," +
                        "\"lyrics\": [ {" +
                            "\"poetIds\": [ 1 ]," +
                            "\"locale\": \"en-US\"," +
                            "\"content\": \"Слова, слова,  \\n" +
                                "Немов вуаль,  \\n" +
                                "Там, де тонка діагональ  \\n" +
                                "Звучить кришталь.  \\n" +
                                "А ніч летить туди у даль,  \\n" +
                                "Не залишаючи для нас  \\n" +
                                "Путів, на жаль...  \\n" +
                                "\\n" +
                                "#### Приспів:  \\n" +
                                "Стріляй!  \\n" +
                                "Скажи, чому боїшся ти  \\n" +
                                "Зробити цей останній крок?!?  \\n" +
                                "Давай!  \\n" +
                                "Най буде так, як хочеш ти,  \\n" +
                                "Я заплатив за свій урок!  \\n" +
                                "Прощай, мій Ангелок...  \\n" +
                                "Давай! Тисни гачок!  \\n" +
                                "\\n" +
                                "Слова, слова - \\n" +
                                "Алмаз вини -  \\n" +
                                "Мов зачаїлися в думках  \\n" +
                                "На мить вони...  \\n" +
                                "І от дуель, і от фінал  \\n" +
                                "Там, де навколо Колізей  \\n" +
                                "Звучить метал...  \\n" +
                                "\\n" +
                                "#### Приспів. (2)\\n" +
                                "\\n" +
                                ">Прощай, мій Ангелок...  \\n" +
                                "Стріляй!\"" +
                        "} ]," +
                        "\"videos\": [ {" +
                            "\"videoTypeId\": 1," +
                            "\"locale\": \"en-US\"," +
                            "\"title\": \"Rose\"," +
                            "\"description\": \"Rose.\"," +
                            "\"link\": \"rose.mp4\"" +
                        "} ]," +
                        "\"locale\": \"en-US\"," +
                        "\"writeDate\": \"2016-05-08\"" +
                    "}"
                )
        ).andDo( print() )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            //- Song -//
            .andExpect( jsonPath( "$locale", notNullValue() ) )
//            .andExpect( jsonPath( "$writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$data", notNullValue() ) )
            .andExpect( jsonPath( "$staffs", notNullValue() ) )
            .andExpect( jsonPath( "$lyrics", notNullValue() ) )
            .andExpect( jsonPath( "$videos", notNullValue() ) )
            .andDo(
                document(
                    "songs-create-success-example",
                    requestFields(
                        fieldWithPath( "data" ).description( "Localized data of song." ),
                        fieldWithPath( "staffs" ).description( "List of musical notes." ),
                        fieldWithPath( "lyrics" ).description( "List of lyrics." ),
                        fieldWithPath( "videos" ).description( "List of videos." ),
                        fieldWithPath( "locale" ).description( "Locale of song." ),
                        fieldWithPath( "writeDate" ).description( "Write date of song." )
                    )
                )
            );
    }
}
