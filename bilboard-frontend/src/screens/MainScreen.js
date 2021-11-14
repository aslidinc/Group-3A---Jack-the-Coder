import { makeStyles } from "@mui/styles";
import Grid from "@mui/material/Grid";
import BilboardNavbar from "../components/BilboardNavbar";
import EventCard from "../components/EventCard";
import BilboardButton from "../components/BilboardButton";

const useStyles = makeStyles({
  root: {
    height: "100vh",
  },
  eventsContainer: {
    display: "flex",
    alignItems: "center",
    minHeight: "340px",
    maxHeight: "340px",
    maxWidth: "100vw",
    //border: "1px solid black",
    overflowY: "hidden",
    flexWrap: "nowrap",
    justifyContent: "start",
    marginRight: "20px",
  },
  eventCard: {
    width: "20%",
    margin: "20px",
  },
});

const MainScreen = () => {
  const classes = useStyles();
  return (
    <>
      <BilboardNavbar />
      <div>
        <Grid container direction="column">
          <Grid item xs={6}>
            <Grid container>
              <Grid item xs={12} style={{ marginBottom: "-40px" }}>
                <p
                  style={{
                    float: "left",
                    marginLeft: "20px",
                    fontSize: "28px",
                  }}
                >
                  Events of Following Clubs
                </p>
              </Grid>
              <Grid item xs={12}>
                <div className={classes.eventsContainer}>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                </div>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={6}>
            <Grid container>
              <Grid item xs={12} style={{ marginBottom: "-40px" }}>
                <p
                  style={{
                    float: "left",
                    marginLeft: "20px",
                    fontSize: "28px",
                  }}
                >
                  Discover
                </p>
              </Grid>
              <Grid item xs={12}>
                <div className={classes.eventsContainer}>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  <div className={classes.eventCard}>
                    <EventCard />
                  </div>
                  
                </div>
              </Grid>
            </Grid>
          </Grid>
        </Grid>
      </div>
    </>
  );
};

export default MainScreen;
