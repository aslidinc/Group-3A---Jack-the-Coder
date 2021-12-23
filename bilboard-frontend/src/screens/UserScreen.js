import { makeStyles } from "@mui/styles";
import Grid from "@mui/material/Grid";
import FollowedClubs from "../components/FollowedClubs";
import BilboardTextField from "../components/BilboardTextField";
import Constants from "../utils/Constants";
import BilboardButton from "../components/BilboardButton";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Colors from "../utils/Colors";
import { Card, CircularProgress, Switch } from "@mui/material";
import { connect } from "react-redux";
import React from "react";

const useStyles = makeStyles( {
    root: {
        height: "calc(100vh-68px)",
    },
    userImage: {
        width: "200px",
        height: "200px",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        borderRadius: "100px"
    },
} );

const UserScreen = ( { image, setScreenNoNavbar, signOut, program } ) => {
    console.log( program )
    const [ geStatus, setGeStatus ] = React.useState( null )
    const [ submitted, setSubmitted ] = React.useState( false )

    if ( geStatus == null ) {
        setGeStatus( program.geTaken )
    }

    const classes = useStyles();
    return (
        <div className={ classes.root }>
            <Grid container>
                <Grid item xs={ 4 }>
                    <Card elevation={ 3 }
                          style={ {
                              borderRadius: Constants.BORDER_RADIUS,
                              padding: "20px",
                              margin: "40px",
                              marginLeft: "100px",
                              marginRight: "80px",

                          } }>
                        <Grid container direction="column">
                            <Grid item xs={ 4 } style={ {
                                paddingTop: "40px",
                                paddingBottom: "30px",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center"
                            } }>
                                <div
                                    className={ classes.userImage }
                                    style={ {
                                        backgroundImage: `url(${ process.env.REACT_APP_IMAGE_URL + program.photo })`
                                    } }
                                />
                            </Grid>
                            <Grid item xs={ 8 }>
                                <Grid container>
                                    <Grid
                                        item
                                        xs={ 12 }
                                        style={ {
                                            fontFamily: Constants.OXYGEN_FONT_FAMILY,
                                            fontSize: "24px",
                                        } }
                                    >
                                        Change Password
                                    </Grid>
                                    <Grid
                                        item
                                        xs={ 12 }
                                        style={ { marginBottom: "10px", marginTop: "10px" } }
                                    >
                                        <BilboardTextField label="Current Password" width={ '280px' }/>
                                    </Grid>
                                    <Grid item xs={ 12 } style={ { marginBottom: "10px" } }>
                                        <BilboardTextField label="New Password" width={ '280px' }/>
                                    </Grid>
                                    <Grid item xs={ 12 } style={ { marginBottom: "10px" } }>
                                        <BilboardTextField label="Retype New Password" width={ '280px' }/>
                                    </Grid>

                                    <Grid item xs={ 12 } style={ { marginBottom: "10px" } }>
                                        <BilboardButton text="Update" width={ '160px' } fontSize={ "12px" }/>
                                    </Grid>
                                    <Grid item xs={ 12 }
                                          style={ { marginBottom: "10px", display: "flex", justifyContent: "center" } }>
                                        <FormGroup>
                                            <FormControlLabel
                                                control={ <Switch checked={ geStatus } onChange={ () => {

                                                } }/> }
                                                label="GE250/1"
                                            />
                                        </FormGroup>
                                    </Grid>
                                    <Grid item xs={ 12 }
                                          style={ { marginBottom: "10px", display: "flex", minHeight:60, justifyContent: "center" } }>
                                        {submitted ? <CircularProgress/> : <div/>}
                                    </Grid>
                                    <Grid item xs={ 12 } style={ { marginTop: "10px" } }>
                                        <BilboardButton color={ Colors.BILBOARD_RED } text="Sign Out" width="160px"
                                                        fontSize="12px" onClick={ () => {
                                            signOut()
                                            setScreenNoNavbar( "login" )
                                        } }/>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Card>
                </Grid>
                <Grid item xs={ 8 }>
                    <Card elevation={ 3 } style={ {
                        borderRadius: Constants.BORDER_RADIUS,
                        padding: "20px",
                        marginRight: "20px",
                        marginTop: "40px"
                    } }>
                        <Grid container style={ { maxHeight: "75vh", overflowX: "scroll" } }>
                            <Grid item xs={ 12 }>
                                <p
                                    style={ {
                                        fontFamily: Constants.OXYGEN_FONT_FAMILY,
                                        fontSize: "32px",
                                        float: "left",
                                    } }
                                >
                                    Followed Clubs
                                </p>
                            </Grid>
                            <Grid item xs={ 12 }>
                                { program.clubMemberShips.map( ( membership ) => {
                                    return <FollowedClubs club={ membership.club }/>
                                } ) }
                            </Grid>
                        </Grid>
                    </Card>
                </Grid>
            </Grid>
        </div>
    );
};

const mapDispatchToProps = ( dispatch ) => {
    return {
        setScreenNoNavbar: ( value ) => dispatch( { type: "SET_SCREEN_NO_NAVBAR", screenNoNavbar: value } )
    }
}

export default connect( null, mapDispatchToProps )( UserScreen );
