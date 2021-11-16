import Grid  from "@mui/material/Grid";
import BilboardTextField from "../components/BilboardTextField";
import BilboardButton from "../components/BilboardButton";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import Dialog from "@mui/material/Dialog";
import Constants from "../utils/Constants";
import Button from "@mui/material/Button";
import React, {useState} from "react";

/**
 * Attend Event Dialog
 * 
 * Date: 13.11.2021
 * Metehan Saçakçı
 */

const AttendEventDialog  = () => {
    const [isDialogOpen, setIsDialogOpen] = useState(true);
    
    return (
        <Dialog open={isDialogOpen} fullWidth maxWidth={"sm"}
                onClose={() => setIsDialogOpen(false)}>
            <DialogContent>            
                <Grid container>
                    <Grid item xs={12}>
                        <p
                            style={{
                                color: "#616161",
                                fontSize: "44px",
                                marginBottom: "30px",
                                fontFamily: Constants.OXYGEN_FONT_FAMILY,
                                letterSpacing: "4px",
                                display: "flex",
                                justifyContent: "center",
                            }}
                        >
                            Attend An Event
                        </p>                       
                        <p
                        style={{
                            color: "#616161",
                            fontSize: "18px",
                            marginBottom: "1px",
                            fontFamily: Constants.OXYGEN_FONT_FAMILY,
                            align: "center",
                            letterSpacing: "1px",
                            display: "flex",
                            justifyContent: "center",
                        }}      
                    >Please enter 8-digit event code</p>
                    </Grid>
                    <Grid item xs={12} style={{marginTop: "20px",
                        display: "flex",
                        justifyContent: "center",
                        }}>
                        <BilboardTextField
                            label="Code"
                            type="attendanceCode"
                            width="300px"
                            style={{marginTop: "30px"}}
                        />
                    </Grid>
                    <Grid item xs={12} style={{marginTop: "40px",                  
                        display: "flex",
                        justifyContent: "center"}}>                         
                        <BilboardButton width="100px" fontSize="14px" text="Submit"/>
                    </Grid>
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button 
                    onClick={() => setIsDialogOpen(false)}
                    style= {{
                        display: "flex",
                        justifyContent: "center"
                    }}
                    >Cancel</Button>
            </DialogActions>
        </Dialog>
    )
}

export default AttendEventDialog;