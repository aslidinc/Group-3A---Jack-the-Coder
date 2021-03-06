package com.jack_the_coder.bilboard_backend.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Hacı Çakın, Aslı Dinç
 * @apiNote This class is base structure of the users table in database
 * @implNote DONE
 * @since 09.11.2021
 */
@Entity( name = "users" )
public class UserEntity implements Serializable {

    public enum UserTypes {
        student,
        academic,
        admin,
        administrativeAssistants,
    }

    private static final long serialVersionUID = 8585888063588545565L;


    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private long id;

    @Column( nullable = false, length = 50, name = "name" )
    private String name;

    @Column( nullable = false, length = 50, name = "surname" )
    private String surname;

    @Enumerated( EnumType.STRING )
    @Column( name = "type" )
    private UserEntity.UserTypes type;

    @Column( length = 80, name = "password", nullable = false )
    private String password;

    @Column( length = 20, name = "bilkent_ID", nullable = false )
    private String bilkentId;

    @Column( nullable = false, length = 120, unique = true, name = "email" )
    private String email;

    @Column( name = "ge_taken" )
    private boolean geTaken;

    @Column( name = "email_confirmation" )
    private boolean emailConfirmation;

    @Column( name = "email_confirmation_token" )
    private String emailConfirmationToken;

    @Column( name = "jwt_expiration" )
    private Date jwtExpirationTime;

    @Column( name = "photo" )
    private String photo;

    @OneToOne( targetEntity = ClubEntity.class )
    private ClubEntity presidentOf;

    @OneToOne( targetEntity = ClubEntity.class )
    private ClubEntity advisorOf;

    @OneToMany( targetEntity = ClubMemberEntity.class, mappedBy = "user" )
    private List<ClubMemberEntity> clubMemberShips;

    @OneToMany( targetEntity = ClubBoardMemberEntity.class, mappedBy = "user" )
    private List<ClubBoardMemberEntity> clubBoardMemberships;

    @ManyToOne( targetEntity = UniversityEntity.class )
    @JoinColumn( name = "university" )
    private UniversityEntity university;

    @OneToOne( targetEntity = PasswordResetTokenEntity.class )
    @JoinColumn( name = "reset_token_id" )
    private PasswordResetTokenEntity resetTokenEntity;

    @OneToMany( targetEntity = EventParticipantEntity.class, mappedBy = "user" )
    private List<EventParticipantEntity> eventParticipants;

    @OneToMany( targetEntity = ClubFeedbackEntity.class, mappedBy = "user" )
    private List<ClubFeedbackEntity> clubFeedbacks;

    @OneToMany( targetEntity = SurveyParticipantEntity.class, mappedBy = "user" )
    private List<SurveyParticipantEntity> surveyParticipants;

    @OneToMany( targetEntity = EventQuestionEntity.class, mappedBy = "user" )
    private List<EventQuestionEntity> eventQuestions;

    @OneToMany( targetEntity = EnrollRequestEntity.class, mappedBy = "user" )
    private List<EnrollRequestEntity> enrollRequests;


    /**
     * get id method
     * @return id is a long
     */
    public long getId () {
        return id;
    }

    /**
     * set id method
     * @param id is a long
     */
    public void setId ( long id ) {
        this.id = id;
    }

    /**
     * Get name method
     * @return String
     */
    public String getName () {
        return name;
    }

    /**
     * Set name method
     * @param name is a String
     */
    public void setName ( String name ) {
        this.name = name;
    }

    /**
     * Get surname method
     * @return String
     */
    public String getSurname () {
        return surname;
    }

    /**
     * Set surname method
     * @param surname is a String
     */
    public void setSurname ( String surname ) {
        this.surname = surname;
    }

    /**
     * Get type method
     * @return UserTypes
     */
    public UserEntity.UserTypes getType () {
        return type;
    }

    /**
     * Set type method
     * @param type is a UserTypes
     */
    public void setType ( UserEntity.UserTypes type ) {
        this.type = type;
    }

    /**
     * Get password method
     * @return String
     */
    public String getPassword () {
        return password;
    }

    /**
     * Set password method
     * @param password is a String
     */
    public void setPassword ( String password ) {
        this.password = password;
    }

    /**
     * Get bilkentId method
     * @return String
     */
    public String getBilkentId () {
        return bilkentId;
    }

    /**
     * Set bilkentId method
     * @param bilkentId is a String
     */
    public void setBilkentId ( String bilkentId ) {
        this.bilkentId = bilkentId;
    }

    /**
     * Get email method
     * @return String
     */
    public String getEmail () {
        return email;
    }

    /**
     * Set email method
     * @param email is a String
     */
    public void setEmail ( String email ) {
        this.email = email;
    }

    /**
     * Get email confirmation method
     * @return Boolean
     */
    public boolean isEmailConfirmation () {
        return emailConfirmation;
    }

    /**
     * Set email confirmation method
     * @param emailConfirmation is a Boolean
     */
    public void setEmailConfirmation ( boolean emailConfirmation ) {
        this.emailConfirmation = emailConfirmation;
    }

    /**
     * Get email confirmation token method
     * @return String
     */
    public String getEmailConfirmationToken () {
        return emailConfirmationToken;
    }

    /**
     * Set email confirmation token method
     * @param emailConfirmationToken is a String
     */
    public void setEmailConfirmationToken ( String emailConfirmationToken ) {
        this.emailConfirmationToken = emailConfirmationToken;
    }

    /**
     * Get jwt expiration time method
     * @return Date
     */
    public Date getJwtExpirationTime () {
        return jwtExpirationTime;
    }

    /**
     * Set jwt expiration time method
     * @param jwtExpirationTime is a Date
     */
    public void setJwtExpirationTime ( Date jwtExpirationTime ) {
        this.jwtExpirationTime = jwtExpirationTime;
    }

    /**
     * Get get president of method
     * @return ClubEntity
     */
    public ClubEntity getPresidentOf () {
        return presidentOf;
    }

    /**
     * Set president of method
     * @param presidentOf is a ClubEntity instance
     */
    public void setPresidentOf ( ClubEntity presidentOf ) {
        this.presidentOf = presidentOf;
    }

    /**
     * Get get advisor of method
     * @return ClubEntity
     */
    public ClubEntity getAdvisorOf () {
        return advisorOf;
    }

    /**
     * Set advisor of method
     * @param advisorOf is ClubEntity instance
     */
    public void setAdvisorOf ( ClubEntity advisorOf ) {
        this.advisorOf = advisorOf;
    }


    /**
     * get club memberships method
     * @return clubMemberShips is a ClubMemberEntity list
     */
    public List<ClubMemberEntity> getClubMemberShips () {
        return clubMemberShips;
    }

    /**
     * set club memberships method
     * @param clubMemberShips is a ClubMemberEntity list
     */
    public void setClubMemberShips (
            List<ClubMemberEntity> clubMemberShips ) {
        this.clubMemberShips = clubMemberShips;
    }

    /**
     * get club board member method
     * @return clubBoardMemberships is a ClubBoardMemberEntity list
     */
    public List<ClubBoardMemberEntity> getClubBoardMemberships () {
        return clubBoardMemberships;
    }

    /**
     * set club board member method
     * @param clubBoardMemberships is a ClubBoardMemberEntity list
     */
    public void setClubBoardMemberships (
            List<ClubBoardMemberEntity> clubBoardMemberships ) {
        this.clubBoardMemberships = clubBoardMemberships;
    }

    /**
     * get university method
     * @return university is UniversityEntity instance
     */
    public UniversityEntity getUniversity () {
        return university;
    }

    /**
     * set university method
     * @param university is UniversityEntity
     */
    public void setUniversity ( UniversityEntity university ) {
        this.university = university;
    }

    /**
     * get event participants method
     * @return eventParticipants is EventParticipantEntity list
     */
    public List<EventParticipantEntity> getEventParticipants () {
        return eventParticipants;
    }

    /**
     * set event participants method
     * @param eventParticipants is an EventParticipantEntity list
     */
    public void setEventParticipants (
            List<EventParticipantEntity> eventParticipants ) {
        this.eventParticipants = eventParticipants;
    }

    /**
     * get club feedbacks method
     * @return clubFeedbacks is a ClubFeedbackEntity list
     */
    public List<ClubFeedbackEntity> getClubFeedbacks () {
        return clubFeedbacks;
    }

    /**
     * set club feedbacks method
     * @param clubFeedbacks is a ClubFeedbackEntity list
     */
    public void setClubFeedbacks (
            List<ClubFeedbackEntity> clubFeedbacks ) {
        this.clubFeedbacks = clubFeedbacks;
    }

    /**
     * get survey participants method
     * @return surveyParticipants is a SurveyParticipantEntity list
     */
    public List<SurveyParticipantEntity> getSurveyParticipants () {
        return surveyParticipants;
    }

    /**
     * set survey participants method
     * @param surveyParticipants is a SurveyParticipantEntity list
     */
    public void setSurveyParticipants (
            List<SurveyParticipantEntity> surveyParticipants ) {
        this.surveyParticipants = surveyParticipants;
    }

    /**
     * get event questions method
     * @return eventQuestions is an EventQuestionEntity list
     */
    public List<EventQuestionEntity> getEventQuestions () {
        return eventQuestions;
    }

    /**
     * set event questions method
     * @param eventQuestions is an EventQuestionEntity list
     */
    public void setEventQuestions (
            List<EventQuestionEntity> eventQuestions ) {
        this.eventQuestions = eventQuestions;
    }

    /**
     * get enroll requests method
     * @return enrollRequests is an EnrollRequestEntity list
     */
    public List<EnrollRequestEntity> getEnrollRequests () {
        return enrollRequests;
    }

    /**
     * set enroll requests method
     * @param enrollRequests is an EnrollRequestEntity list
     */
    public void setEnrollRequests (
            List<EnrollRequestEntity> enrollRequests ) {
        this.enrollRequests = enrollRequests;
    }

    /**
     * is ge taken method
     * @return geTaken is a boolean
     */
    public boolean isGeTaken () {
        return geTaken;
    }

    /**
     * set ge taken method
     * @param geTaken is a boolean
     */
    public void setGeTaken ( boolean geTaken ) {
        this.geTaken = geTaken;
    }

    /**
     * get reset token entity method
     * @return resetTokenEntity is a PasswordResetTokenEntity instance
     */
    public PasswordResetTokenEntity getResetTokenEntity () {
        return resetTokenEntity;
    }

    /**
     * set reset token entity method
     * @param resetTokenEntity is a PasswordResetTokenEntity instance
     */
    public void setResetTokenEntity ( PasswordResetTokenEntity resetTokenEntity ) {
        this.resetTokenEntity = resetTokenEntity;
    }

    /**
     * get photo method
     * @return photo is a String
     */
    public String getPhoto () {
        return photo;
    }

    /**
     * set photo method
     * @param photo is a String
     */
    public void setPhoto ( String photo ) {
        this.photo = photo;
    }
}
