package com.jack_the_coder.bilboard_backend.controller;

import com.jack_the_coder.bilboard_backend.io.entity.UniversityEntity;
import com.jack_the_coder.bilboard_backend.model.OperationName;
import com.jack_the_coder.bilboard_backend.model.OperationStatus;
import com.jack_the_coder.bilboard_backend.model.StatusResponse;
import com.jack_the_coder.bilboard_backend.model.requestModel.ResetPasswordRequest;
import com.jack_the_coder.bilboard_backend.model.requestModel.SignUpRequest;
import com.jack_the_coder.bilboard_backend.model.responseModel.SignUpResponse;
import com.jack_the_coder.bilboard_backend.service.AdminService;
import com.jack_the_coder.bilboard_backend.service.UserService;
import com.jack_the_coder.bilboard_backend.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hacı Çakın
 * @apiNote This is auth controller that consists of signUp/signIn, reset password, account confirmation. Client
 * interacts with that router.
 * It's path is /bilboard-app/v1/auth
 * @implNote NOT Completed
 * @since 11.12.2021
 */
@CrossOrigin
@RestController
@RequestMapping( "/auth" )
public class AuthController {  // http://localhost:8080/bilboard-app/v1/auth


    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @PostMapping( path = "/signUp" )  // http://localhost:8080/bilboard-app/v1/auth/signUp
    public SignUpResponse signUp ( @RequestBody SignUpRequest requestModel ) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map( requestModel , UserDto.class );   // modelMapper.map(source, target.class);
        userDto.setUniversity( modelMapper.map( adminService.getUniversity( requestModel.getUniversity() ) ,
                UniversityEntity.class ) );

        UserDto createdDto = userService.createUser( userDto );
        return modelMapper.map( createdDto , SignUpResponse.class );
    }


    // email verification   http://localhost:8080/auth/emailVerification
    @PostMapping( path = "/emailVerification" )
    public StatusResponse signUpConfirmation ( @RequestParam( value = "token" ) String token ) {
        StatusResponse returnValue = new StatusResponse();
        returnValue.setOperationName( OperationName.VERIFY_EMAIL.name() );

        boolean isVerified = userService.verifyEmailToken( token );

        if ( isVerified ) {
            returnValue.setOperationResult( OperationStatus.SUCCESS.name() );
        } else {
            returnValue.setOperationResult( OperationStatus.ERROR.name() );
        }

        return returnValue;
    }

    // reset passwordRequest    http://localhost:8080/auth/resetPasswordRequest
    @PostMapping( path = "/resetPasswordRequest" )
    public StatusResponse forgetPassword ( @RequestParam( value = "email" ) String email ) {
        StatusResponse returnValue = new StatusResponse();

        boolean operationResult = userService.requestPasswordReset( email );

        returnValue.setOperationName( OperationName.REQUEST_PASSWORD_RESET.name() );
        returnValue.setOperationResult( OperationStatus.ERROR.name() );

        if ( operationResult ) {
            returnValue.setOperationResult( OperationStatus.SUCCESS.name() );
        }

        return returnValue;
    }

    // reset password   http://localhost:8080/auth/resetPassword
    @PostMapping( path = "/resetPassword" )
    public StatusResponse changePassword ( @RequestBody ResetPasswordRequest requestModel ) {
        StatusResponse returnValue = new StatusResponse();

        boolean operationResult = userService.resetPassword(
                requestModel.getToken() ,
                requestModel.getNewPassword() );

        returnValue.setOperationName( OperationName.PASSWORD_RESET.name() );
        returnValue.setOperationResult( OperationStatus.ERROR.name() );

        if ( operationResult ) {
            returnValue.setOperationResult( OperationStatus.SUCCESS.name() );
        }

        return returnValue;
    }

}
