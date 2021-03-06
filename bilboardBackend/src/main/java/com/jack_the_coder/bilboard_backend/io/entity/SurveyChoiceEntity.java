package com.jack_the_coder.bilboard_backend.io.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Hacı Çakın
 * @apiNote This class is base structure of the survey_choices table in database
 * @implNote DONE
 * @since 09.11.2021
 */
@Entity( name = "survey_choices" )
public class SurveyChoiceEntity implements Serializable {

    private static final long serialVersionUID = -2340163411490305735L;

    @GeneratedValue
    @Id
    @Column( name = "id" )
    private long id;

    @Column( name = "content", length = 100, nullable = false )
    private String content;

    @Column( name = "vote_count" )
    private int voteCount;

    @ManyToOne( targetEntity = SurveyQuestionEntity.class )
    @JoinColumn( name = "question" )
    private SurveyQuestionEntity question;

    /**
     * Get id method
     * @return long
     */
    public long getId () {
        return id;
    }

    /**
     * Set id method
     * @param id is a long
     */
    public void setId ( long id ) {
        this.id = id;
    }

    /**
     * Get content method
     * @return String
     */
    public String getContent () {
        return content;
    }

    /**
     * Set content method
     * @param content is a String
     */
    public void setContent ( String content ) {
        this.content = content;
    }

    /**
     * Get vote count method
     * @return int
     */
    public int getVoteCount () {
        return voteCount;
    }

    /**
     * Set vote count method
     * @param voteCount is an int
     */
    public void setVoteCount ( int voteCount ) {
        this.voteCount = voteCount;
    }

    /**
     * Get question method
     * @return SurveyQuestionEntity
     */
    public SurveyQuestionEntity getQuestion () {
        return question;
    }

    /**
     * Set question method
     * @param question is a SurveyQuestionEntity
     */
    public void setQuestion ( SurveyQuestionEntity question ) {
        this.question = question;
    }
}
