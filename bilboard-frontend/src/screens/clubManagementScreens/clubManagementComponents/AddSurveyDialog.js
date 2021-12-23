import * as React from "react";
import Grid from "@mui/material/Grid";
import BilboardMultilineTextField from "../../../components/BilboardMultilineTextField";
import BilboardButton from "../../../components/BilboardButton";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import Dialog from "@mui/material/Dialog";
import Constants from "../../../utils/Constants";
import Button from "@mui/material/Button";
import BilboardTextField from "../../../components/BilboardTextField";
import Colors from "../../../utils/Colors";
import { Alert, Card, Snackbar, TextField } from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {
  AddAlert,
  AddBox,
  DeleteForever,
  RemoveOutlined,
} from "@mui/icons-material";
import AddSurveyCard from "./AddSurveyCard";

/**
 * Add Survey Dialog
 *
 * Metehan Saçakçı
 * 17.11.2021
 */

const AddSurveyDialog = (props) => {
    const [surveyCards, setSurveyCards] = React.useState([<AddSurveyCard/>]);
    const [isAddSurveyDisabled, setIsAddSurveyDisabled] = React.useState(true);

    const handleAddSurvey = () => {

    }

    React.useEffect(() => {
        if(surveyCards.length === 0){
            setIsAddSurveyDisabled(true);
        }
        else{
            setIsAddSurveyDisabled(false);
        }
    },[surveyCards]);
  
  return (
    <Dialog
      open={props.open}
      fullWidth
      maxWidth={"md"}
      onClose={() => props.setOpen(false)}
    >
      <p
        style={{
          color: Colors.BILBOARD_LIGHT_GREY,
          fontSize: "44px",
          marginBottom: "30px",
          fontFamily: Constants.OXYGEN_FONT_FAMILY,
          letterSpacing: "4px",
          display: "flex",
          justifyContent: "center",
        }}
      >
        Add Survey
      </p>
      
      {surveyCards}

      <Grid item xs={12} style={{display: "flex", justifyContent: "center", marginBottom: "40px"}}>
        <BilboardButton width="200px" fontSize="14px" text="Add Question" onClick={() => {
            let temp = [...surveyCards];
            temp.push(<AddSurveyCard/>);
            setSurveyCards(temp);
        }} />
        <BilboardButton width="200px" color="red" fontSize="14px" text="Delete Last Question" onClick={() => {
            let temp = [...surveyCards];
            temp.pop();
            setSurveyCards(temp);
        }} />
      </Grid>

      <Grid item xs={12}>
        <p
          style={{
            color: Colors.BILBOARD_LIGHT_GREY,
            fontSize: "18px",
            marginTop: "10px",
            marginBottom: "1px",
            fontFamily: Constants.OXYGEN_FONT_FAMILY,
            align: "center",
            letterSpacing: "1px",
            display: "flex",
            justifyContent: "center",
          }}
        >
          End Date
        </p>
      </Grid>

      <Grid
        item
        xs={12}
        style={{
          marginTop: "10px",
          marginBottom: "10px",
          align: "center",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <BilboardTextField width={"300px"} type={"datetime-local"} />
      </Grid>
      <Grid
        item
        xs={12}
        style={{
          marginTop: "40px",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <BilboardButton width="200px" fontSize="14px" text="Add Survey" disabled={isAddSurveyDisabled} onClick={() => handleAddSurvey()}/>
      </Grid>
      <DialogActions>
        <Button
          onClick={() => props.setOpen(false)}
          style={{
            display: "flex",
            justifyContent: "center",
          }}
        >
          Cancel
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default AddSurveyDialog;
