package com.jack_the_coder.bilboard_backend.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity( name = "password_reset_tokens" )
public class PasswordResetTokenEntity implements Serializable {

    private static final long serialVersionUID = -7106434375768222773L;

    @Id
    @GeneratedValue
    private long id;

    @Column( name = "token" )
    private String token;

    @OneToOne( targetEntity = UserEntity.class )
    @JoinColumn( name = "user_id" )
    private UserEntity userDetails;

    public long getId () {
        return id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    public String getToken () {
        return token;
    }

    public void setToken ( String token ) {
        this.token = token;
    }

    public UserEntity getUserDetails () {
        return userDetails;
    }

    public void setUserDetails ( UserEntity userDetails ) {
        this.userDetails = userDetails;
    }
}

