import { CircularProgress, Grid, IconButton, Snackbar } from "@mui/material";
import React from 'react';
import Constants from "../../utils/Constants";
import BilboardButton from "../../components/BilboardButton";
import BilboardTextField from "../../components/BilboardTextField";
import SponsorCard from "./clubManagementComponents/SponsorCard";
import Colors from "../../utils/Colors";
import { AddOutlined, Image } from "@mui/icons-material";
import Env from "../../utils/Env";
import axios from "axios";
import Button from "@mui/material/Button";
import { Edit, ImageAspectRatio, ImageOutlined } from "@mui/icons-material";
import { makeStyles } from "@mui/styles";
import Alert from "@mui/material/Alert";

const useStyles = makeStyles( {
    textGrid: {
        display: "flex",
        justifyContent: "end",
        paddingRight: "20px",
        fontFamily: Constants.OXYGEN_FONT_FAMILY,
    },
    inputGrid: {
        display: "flex",
        justifyContent: "start",
    },
    rowGrid: {
        paddingTop: "10px",
    },
    input: {
        display: "none",
    },
} );

const ClubManagementSponsorScreen = ( { club, functionList } ) => {
    const classes = useStyles();

    const [ name, setName ] = React.useState( "" );
    const [ amount, setAmount ] = React.useState( "" );
    const [ type, setType ] = React.useState( "" );
    const [ photo, setPhoto ] = React.useState( null );
    const [ error, setError ] = React.useState( "" );
    const [ submitted, setSubmitted ] = React.useState( false );
    const [ success, setSuccess ] = React.useState( "" );


    function handleSponsorshipAddition() {
        const formData = new FormData()

        if ( name.trim().length === 0 || amount.trim().length === 0 || type.trim().length === 0 || photo === null ) {
            setError( "Please fill all the fields!" );
        } else {
            setSubmitted( true );

            Array.from( photo )
                 .forEach( ( file ) => {
                     formData.append( "photo", file, file.name )
                 } )

            formData.append( "clubId", club.id )
            formData.append( "name", name )
            formData.append( "amount", amount )
            formData.append( "type", type )

            const headers = {
                'Authorization': 'Bearer ' + Env.TOKEN
            }

            axios.post( process.env.REACT_APP_URL + "club/sponsorship", formData, { headers: headers } )
                 .then( function ( response ) {
                     setSuccess( "Sponsorship is successfully added" )
                     setSubmitted( false )
                     functionList.handleNewSponsorAddition( response.data )
                     setName( "" )
                     setAmount( "" )
                     setType( "" )
                 } )
                 .catch( function ( error ) {
                     setError( "Sponsorship could not be added!" )
                     setSubmitted( false )
                 } )
        }
    }


    return <Grid container
                 style={ { margin: 0, padding: 10, borderRadius: Constants.BORDER_RADIUS } }>
        <Grid style={ {
            fontFamily: Constants.OXYGEN_FONT_FAMILY,
            fontSize: 48,
            paddingBottom: 20,
        } } item xs={ 12 }>
            Sponsors
        </Grid>
        <Grid container style={ { maxHeight: "60vh", overflowY: "scroll" } }>
            { club.clubSponsorships.map( sponsor => {
                return <SponsorCard setError={ ( val ) => setError( val ) }
                                    setSuccess={ ( val ) => setSuccess( val ) }
                                    functionList={ functionList }
                                    sponsor={ sponsor }/>
            } ) }
        </Grid>
        <Grid container style={ {
            marginTop: 20,
            height: "10vh",
            padding: 12,
            borderRadius: Constants.BORDER_RADIUS,
            alignItems: "center",
            background: Colors.BILBOARD_MAIN_ALTERNATIVE
        } }>
            <Grid item xs={ 1 }>
                <input
                    className={ classes.input }
                    accept="image/*"
                    id="contained-button-file"
                    type="file"
                    onChange={ ( event ) => {
                        setPhoto( event.target.files )
                    } }
                />
                <Grid item xs={ 5 } className={ classes.textGrid } style={ { marginLeft: 15 } }>
                    Image
                </Grid>
                <Grid item xs={ 7 } className={ classes.inputGrid } style={ { marginLeft: 10 } }>
                    <label htmlFor="contained-button-file">
                        <Button component="span" style={ { marginLeft: -20 } }> <ImageOutlined
                            style={ { color: Colors.BILBOARD_MAIN } }/></Button>
                    </label>
                    <label htmlFor="contained-button-file">
                        <Button disabled={ true } component="span"
                                style={ { marginLeft: -20 } }>     { photo !== null && "Selected!" }</Button>
                    </label>
                </Grid>
            </Grid>
            <Grid item xs={ 3 }>
                <BilboardTextField value={ name } onChange={ ( e ) => setName( e.target.value ) } width={ "14vw" }
                                   label={ "Name" }/>
            </Grid>
            <Grid item xs={ 3 }>
                <BilboardTextField value={ amount } onChange={ ( e ) => setAmount( e.target.value ) } width={ "14vw" }
                                   label={ "Amount" }/>
            </Grid>
            <Grid item xs={ 3 }>
                <BilboardTextField value={ type } onChange={ ( e ) => setType( e.target.value ) } width={ "14vw" }
                                   label={ "Type" }/>
            </Grid>
            <Grid item xs={ 2 }>
                { submitted ? <CircularProgress/> :
                    <BilboardButton onClick={ () => handleSponsorshipAddition() } text={ "Add" }
                                    width={ "8vw" }/> }
            </Grid>
        </Grid>
        <Snackbar
            anchorOrigin={ { vertical: "bottom", horizontal: "center", } }
            open={ error !== '' }
            autoHideDuration={ 2000 }
            onClose={ () => setError( '' ) }
        >
            <Alert onClose={ () => setError( '' ) }
                   severity={ "warning" }
            >
                { error }
            </Alert>
        </Snackbar>
        <Snackbar
            anchorOrigin={ { vertical: "bottom", horizontal: "center", } }
            open={ success !== '' }
            autoHideDuration={ 2000 }
            onClose={ () => setSuccess( '' ) }
        >
            <Alert onClose={ () => setSuccess( '' ) }
                   severity={ "success" }
            >
                { success }
            </Alert>
        </Snackbar>
    </Grid>
};

export default ClubManagementSponsorScreen;