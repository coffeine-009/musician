/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/12/15 10:42 AM
 */

package com.coffeine.virtuoso.music.view.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Form for create/update song.
 *
 * @version 1.0
 */
public class SongForm {

    /**
     * Form for input localized data.
     */
    public static class SongLocaleForm {

        /**
         * Title in selected locale.
         */
        @NotNull
        @NotEmpty
        @Length( max = 64 )
        protected String title;

        /**
         * Selected locale.
         */
        @NotNull
        @NotEmpty
        @Length( max = 5 )
        protected String locale;


        //- SECTION :: GET -//
        /**
         * Get title.
         *
         * @return Title.
         */
        public String getTitle() {
            return title;
        }

        /**
         * Get locale.
         *
         * @return Locale
         */
        public String getLocale() {
            return locale;
        }


        //- SECTION :: SET -//
        /**
         * Set title.
         *
         * @param title    Title of song for selected locale.
         */
        public void setTitle( String title ) {
            this.title = title;
        }

        /**
         * Set locale.
         *
         * @param locale    Code of locale.
         */
        public void setLocale( String locale ) {
            this.locale = locale;
        }
    }

    /**
     * Music notes form.
     */
    public static class MusicNotesForm {

        /**
         * Id of type of music notes.
         * Stave: tab, notation.
         */
        private Long musicNotesTypeId;

        /**
         * Id of style.
         * E.g. Disco, Trance, ... .
         */
        private Long styleId;

        /**
         * File content. MusicXML.
         */
        private String file;


        //- SECTION :: GET -//
        /**
         * Get id of music notes type.
         *
         * @return Id.
         */
        public Long getMusicNotesTypeId() {
            return musicNotesTypeId;
        }

        /**
         * Get id of style.
         *
         * @return Id.
         */
        public Long getStyleId() {
            return styleId;
        }

        /**
         * Get content of file.
         *
         * @return String.xml.MusicXML
         */
        public String getFile() {
            return file;
        }

        //- SECTION :: SET -//
        /**
         * Set id of music notes type.
         *
         * @param musicNotesTypeId    Id of music notes type.
         */
        public void setMusicNotesTypeId( Long musicNotesTypeId ) {
            this.musicNotesTypeId = musicNotesTypeId;
        }

        /**
         * Set id of style.
         *
         * @param styleId    Id of style.
         */
        public void setStyleId( Long styleId ) {
            this.styleId = styleId;
        }

        /**
         * Set content.
         *
         * @param file    MusicXML.
         */
        public void setFile( String file ) {
            this.file = file;
        }
    }

    /**
     * Form for input text(s).
     */
    public static class TextForm {

        /**
         * Selected locale.
         */
        @NotNull
        @NotEmpty
        @Length( max = 5 )
        protected String locale;

        @NotNull
        @NotEmpty
        @Length( min = 60 )
        protected String lyrics;


        //- SECTION :: GET -//
        /**
         * Get locale.
         *
         * @return Locale.
         */
        public String getLocale() {
            return locale;
        }

        /**
         * Get lyrics.
         *
         * @return Lyrics.
         */
        public String getLyrics() {
            return lyrics;
        }

        //- SECTION :: SET -//
        /**
         * Set locale.
         *
         * @param locale    Locale.
         */
        public void setLocale( String locale ) {
            this.locale = locale;
        }

        /**
         * Set lyrics.
         *
         * @param lyrics    Lyrics.
         */
        public void setLyrics( String lyrics ) {
            this.lyrics = lyrics;
        }
    }

    /**
     * Form for input video(s).
     */
    public static class VideoForm {

        @NotNull
        protected Long videoTypeId;

        @NotNull
        @NotEmpty
        @Length( max = 5 )
        protected String locale;

        @NotNull
        @NotEmpty
        @Length( min = 8 )
        protected String title;

        @NotNull
        @NotEmpty
        @Length( min = 8 )
        protected String description;

        @NotNull
        @NotEmpty
        @Length( min = 8 )
        protected String link;

        //- SECTION :: GET -//

        public Long getVideoTypeId() {
            return videoTypeId;
        }

        public String getLocale() {
            return locale;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getLink() {
            return link;
        }

        //- SECTION :: SET -//

        public void setVideoTypeId( Long videoTypeId ) {
            this.videoTypeId = videoTypeId;
        }

        public void setLocale( String locale ) {
            this.locale = locale;
        }

        public void setTitle( String title ) {
            this.title = title;
        }

        public void setDescription( String description ) {
            this.description = description;
        }

        public void setLink( String link ) {
            this.link = link;
        }
    }

    /// *** Properties  *** ///
    /**
     * Ids list of composers.
     */
    @NotNull
    @NotEmpty
    private List<Long> composerIds;

    /**
     * Ids list of poets.
     */
    @NotNull
    @NotEmpty
    private List<Long> poetIds;

    /**
     * Localized data about song.
     */
    @NotNull
    @NotEmpty
    private List<SongForm.SongLocaleForm> data = new ArrayList<>();

    /**
     * List of music notes.
     */
    @NotNull
    @NotEmpty
    protected List<SongForm.MusicNotesForm> staffs = new ArrayList<>();

    /**
     * List of texts.
     */
    @NotNull
    @NotEmpty
    private List<SongForm.TextForm> texts = new ArrayList<>();

    /**
     * List of videos(Covers, clips, ... ,).
     */
    @NotNull
    private List<SongForm.VideoForm> videos = new ArrayList<>();

    /**
     * Original locale of song.
     */
    @NotNull
    @NotEmpty
    @Length( min = 2, max = 5 )
    private String locale;

    /**
     * Date of write this song.
     */
    @JsonDeserialize( using = LocalDateDeserializer.class )
    private LocalDate writeDate;


    //- SECTION :: GET -//
    /**
     * Get list of composer's ids.
     *
     * @return List of ids.
     */
    public List<Long> getComposerIds() {
        return composerIds;
    }

    /**
     * Get list of poet's ids.
     *
     * @return List of ids.
     */
    public List<Long> getPoetIds() {
        return poetIds;
    }

    /**
     * Get list of localized forms.
     *
     * @return List of localized forms.
     */
    public List<SongLocaleForm> getData() {
        return data;
    }

    /**
     * Get music notes.
     *
     * @return Get list of music notes.
     */
    public List<MusicNotesForm> getStaffs() {
        return staffs;
    }

    /**
     * Get texts.
     *
     * @return List of texts.
     */
    public List<TextForm> getTexts() {
        return texts;
    }

    /**
     * Get list of videos.
     *
     * @return List of videos.
     */
    public List<VideoForm> getVideos() {
        return videos;
    }

    /**
     * Get origin locale of song.
     *
     * @return Code of locale.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Date of write.
     *
     * @return Date.
     */
    public LocalDate getWriteDate() {
        return writeDate;
    }


    //- SECTION :: SET -//
    /**
     * Set list of composer's ids.
     *
     * @param composerIds    Composer's ids.
     */
    public void setComposerIds( List<Long> composerIds ) {
        this.composerIds = composerIds;
    }

    /**
     * Set list of poet's ids.
     *
     * @param poetIds    Poet's ids.
     */
    public void setPoetIds( List<Long> poetIds ) {
        this.poetIds = poetIds;
    }

    /**
     * Set localized data about song.
     *
     * @param data    List of SongLocalForm
     */
    public void setData( List<SongLocaleForm> data ) {
        this.data = data;
    }

    /**
     * Set music notes.
     *
     * @param staffs    List of music notes.
     */
    public void setStaffs( List<MusicNotesForm> staffs ) {
        this.staffs = staffs;
    }

    /**
     * Set list of texts.
     *
     * @param texts    List of texts.
     */
    public void setTexts( List<TextForm> texts ) {
        this.texts = texts;
    }

    /**
     * Set videos.
     *
     * @param videos    List of videos.
     */
    public void setVideos( List<VideoForm> videos ) {
        this.videos = videos;
    }

    /**
     * Set origin locale for this song.
     *
     * @param locale    Code of locale.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Set date of write this song.
     *
     * @param writeDate    Date.
     */
    public void setWriteDate( LocalDate writeDate ) {
        this.writeDate = writeDate;
    }
}
