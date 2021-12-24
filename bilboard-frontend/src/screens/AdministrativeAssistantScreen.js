import React from "react";
import { Grid } from "@mui/material";
import Colors from "../utils/Colors";
import Constants from "../utils/Constants";
import { Card } from "@mui/material";
import BilboardButton from "../components/BilboardButton";
import { makeStyles } from "@mui/styles";
import IconButton from "@mui/material/IconButton";
import Fab from "@mui/material/Fab";
import AddIcon from "@mui/icons-material/Add";
import clsx from "clsx";
import LogoutIcon from "@mui/icons-material/Logout";
import CreateBuildingScreen from "./AdministrativeAssistantComponents/CreateBuildingScreen";
import CreateClassroomScreen from "./AdministrativeAssistantComponents/CreateClassroomScreen";
import CreateClassroomDayScreen from "./AdministrativeAssistantComponents/CreateClassroomDayScreen";
import ManageRequestsScreen from "./AdministrativeAssistantComponents/ManageRequestsScreen";

const useStyles = makeStyles({
  root: {
    width: "100%",
  },
  navbar: {
    height: "68px",
    background: Colors.BILBOARD_MAIN,
  },
  navbarText: {
    fontSize: "40px",
    fontFamily: Constants.OXYGEN_FONT_FAMILY,
    color: "white",
  },
  anIcon: {
    color: "white",
    minWidth: "40px",
    minHeight: "40px",
    borderRadius: "50px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
});

const AdministrativeAssistantScreen = () => {
  const classes = useStyles();
  const pages = ["Create Building", "Create Classroom", "Create Classroom Day", "Manage Requests"];
  const [openedScreen, setOpenedScreen] = React.useState("Create Building");
  const handleLogOut = () => {};

  return (
    <div className={classes.root}>
      <div className={classes.navbar}>
        <Grid container>
          <Grid item xs={4}></Grid>
          <Grid
            item
            xs={4}
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <div className={classes.navbarText}>Administrative Assistants</div>
          </Grid>
          <Grid item xs={2}></Grid>
          <Grid
            item
            xs={2}
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <IconButton size="large" onClick={() => handleLogOut()}>
              <LogoutIcon className={classes.anIcon} />
            </IconButton>
          </Grid>
        </Grid>
      </div>
      <div>
        <Grid container style={{ padding: 20, height: "88vh" }}>
          <Grid
            style={{
              height: "85vh",
              borderRadius: Constants.BORDER_RADIUS,
              padding: 4,
            }}
            item
            xs={3}
          >
            <Card
              elevation={4}
              style={{ borderRadius: Constants.BORDER_RADIUS }}
            >
              {pages.map((page) => (
                <Grid
                  item
                  xs={12}
                  style={{
                    paddingTop: 5,
                    paddingBottom: 5,
                    paddingLeft: "2%"
                  }}
                >
                  {openedScreen === page ? (
                    <BilboardButton
                      text={page}
                      fontSize={16}
                      width="23vw"
                      height={"4vh"}
                      color={Colors.BILBOARD_MAIN}
                      textColor={Colors.WHITE}
                    />
                  ) : (
                    <BilboardButton
                      text={page}
                      fontSize={16}
                      width="23vw"
                      height={"4vh"}
                      color={Colors.WHITE}
                      textColor={Colors.BILBOARD_MAIN}
                      onClick={() => setOpenedScreen(page)}
                    />
                  )}
                </Grid>
              ))}
            </Card>
          </Grid>
          <Grid item xs={9} style={{ padding: 4 }}>
          <Card
            elevation={4}
            style={{ borderRadius: Constants.BORDER_RADIUS, height: "85vh" }}
          >
            {openedScreen === "Create Building" ? (
              <CreateBuildingScreen />
            ) : openedScreen === "Create Classroom" ? (
              <CreateClassroomScreen />
            ) : openedScreen === "Create Classroom Day" ? (
              <CreateClassroomDayScreen />
            ) : openedScreen === "Manage Requests" ? (
                <ManageRequestsScreen />) : <div/>}
          </Card>
          </Grid>
        </Grid>
      </div>
    </div>
  );
};

export default AdministrativeAssistantScreen;
