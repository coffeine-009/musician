/// *** User :: Model :: Entity :: VideoType    *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-04-28 22:28:50 :: 2014-04-09 22:39:40
     *
     * @address /Ukraine/Ivano-Frankivsk
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class for reflect table VideoType from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( 
    name = "video_type", 
    uniqueConstraints = @UniqueConstraint( 
        columnNames = {
            "code"
        } 
    ) 
)
public class VideoType implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column( name = "id" )
    protected Long id;

    @NotNull
    @NotEmpty
    @Size( max = 16 )
    @Column( name = "code", length = 16 )
    protected String code;

    @NotNull
    @NotEmpty
    @Size( max = 32 )
    @Column( name = "title", length = 32 )
    protected String title;

    @Column( name = "description", columnDefinition = "TEXT" )
    protected String description;


    /// *** Methods     *** ///
    /**
     * Default constructor
     */
    public VideoType() {
        
    }

    /**
     * Constructor
     *
     * @param code Const name of video type
     * @param title Title of video type
     * @param description Description of this video type
     */
    public VideoType(
        String code, 
        String title, 
        String description
    ) {
        //- Initialization -//
        this.code = code;
        this.title = title;
        this.description = description;
    }

    /**
     * Constructor
     *
     * @param code Const name of video type
     * @param title Title of video type
     * @param description Description of this video type
     */
    public VideoType(
        Long id,
        String code,
        String title,
        String description
    ) {
        //- Initialization -//
        this(
            code,
            title,
            description
        );
        this.id = id;
    }


    //- SECTION :: GET -//
    /**
     * Get ID of VideoType
     *
     * @return Long ID of song
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Get code of VideoType
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get title of VideoType
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get description about VideoType
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }


    //- SECTION :: SET -//
    /**
     * Set ID of VideoType
     *
     * @param id ID of VideoType
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set code of VideoType
     * Ex. ADMIN, MANAGER, USER
     *
     * @param code Const name of video type
     */
    public void setCode( String code ) {
        this.code = code;
    }

    /**
     * Set title of VideoType
     *
     * @param title Title of video type
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of VideoType
     *
     * @param description Description of this video type
     */
    public void setDescription( String description ) {
        this.description = description;
    }
}
