/// *** User :: Model :: Entity :: SongText *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-28 21:21:55 :: 2014-04-28 23:07:30
     *
     * @address /Ukraine/Ivano-Frankivsk/Chornovola/104
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect song_text table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song_text" )
public class SongText implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @Valid
    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "id_song", columnDefinition = "BIGINT( 20 )" )
    protected Song song;

    @NotNull
    @NotEmpty
    @Size( max = 5 )
    @Column( name = "locale", columnDefinition = "VARCHAR( 5 )" )
    protected String locale;

    @Column( 
        name = "creation", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" 
    )
    protected Calendar creation;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public SongText() {
       
    }

    /**
     * Create text for song
     *
     * @param locale 
     */
    public SongText(
        String locale
    ) {
        //- Initialization -//
        this.locale = locale;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of song text
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Get composer for this song text
     *
     * @return Song
     */
    public Song getSong() {
        return song;
    }

    /**
     * Get locale
     *
     * @return String
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get time of create
     *
     * @return Calendar
     */
    public Calendar getCreation() {
        return creation;
    }

    //- SECTION :: SET -//
    /**
     * Set ID of song text
     *
     * @param id 
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set song of text
     *
     * @param song 
     */
    public void setSong( Song song ) {
        this.song = song;
    }

    /**
     * Set locale
     *
     * @param locale 
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
