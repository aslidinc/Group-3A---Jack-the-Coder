package com.jack_the_coder.bilboard_backend.model.responseModel;

import com.jack_the_coder.bilboard_backend.io.entity.ClubEntity;
import com.jack_the_coder.bilboard_backend.io.entity.UniversityEntity;
import com.jack_the_coder.bilboard_backend.io.entity.UserEntity;

public class UserResponse {

    private long id;
    private String name;
    private String surname;
    private UserEntity.UserTypes type;
    private String bilkentId;
    private String email;
    private boolean geTaken;
    private ClubEntity presidentOf;
    private ClubEntity advisorOf;
    private CreateUniversityResponse university;

    public long getId () {
        return id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname ( String surname ) {
        this.surname = surname;
    }

    public UserEntity.UserTypes getType () {
        return type;
    }

    public void setType ( UserEntity.UserTypes type ) {
        this.type = type;
    }

    public String getBilkentId () {
        return bilkentId;
    }

    public void setBilkentId ( String bilkentId ) {
        this.bilkentId = bilkentId;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public boolean isGeTaken () {
        return geTaken;
    }

    public void setGeTaken ( boolean geTaken ) {
        this.geTaken = geTaken;
    }

    public ClubEntity getPresidentOf () {
        return presidentOf;
    }

    public void setPresidentOf ( ClubEntity presidentOf ) {
        this.presidentOf = presidentOf;
    }

    public ClubEntity getAdvisorOf () {
        return advisorOf;
    }

    public void setAdvisorOf ( ClubEntity advisorOf ) {
        this.advisorOf = advisorOf;
    }

    public CreateUniversityResponse getUniversity () {
        return university;
    }

    public void setUniversity ( CreateUniversityResponse university ) {
        this.university = university;
    }
}
