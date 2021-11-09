import { useState } from "react";
import Grid from "@mui/material/Grid";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import PersonIcon from "@mui/icons-material/Person";
import { makeStyles } from "@mui/styles";

const useStyles = makeStyles({
  root: {
    width: "260px",
    height: "260px",
    filter: "brightness(80%)",
    cursor: "pointer",
    borderRadius: "16px",
    backgroundSize: "cover",
    backgroundRepeat: "no-repeat",
    transition: "transform .4s",
    "&:hover": {
      transform: "scale(1.2)",
      filter: "brightness(100%)",
    },
  },
});

const EventCard = (props) => {
  const classes = useStyles();
  const [visible, setVisible] = useState(true);
  return (
    <div
      className={classes.root}
      style={{
        backgroundImage: `url(${props.image})`,
      }}
      onMouseOver={() => setVisible(false)}
      onMouseLeave={() => setVisible(true)}
      onClick={()=> console.log("sss")}
    >
      <Grid
        container
        style={{
          minHeight: "16%",
          backgroundColor: "rgba(0,0,0,0.1)",
          borderRadius: "16px 16px 4px 4px",
        }}
      >
        <Grid
          item
          xs={4}
          style={{
            color: "white",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <LocationOnIcon />
          <div style={{ fontSize: "120%" }}>{props.location}</div>
        </Grid>
        <Grid item xs={4} />
        <Grid
          item
          xs={4}
          style={{
            color: "white",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <PersonIcon />
          <div style={{ fontSize: "120%" }}>{props.participantCount}</div>
        </Grid>
      </Grid>
      {visible && (
        <Grid
          container
          style={{
            minHeight: "24%",
            position: "fixed",
            bottom: "0%",
            color: "white",
            backgroundColor: "rgba(0,0,0,0.1)",
            borderRadius: "4px 4px 16px 16px",
          }}
        >
          <Grid
            item
            xs={12}
            style={{
              display: "flex",
              justifyContent: "start",
              marginLeft: "4%",
              fontSize: "120%",
              fontWeight: "bolder",
            }}
          >
            {props.title}
          </Grid>
          <Grid
            item
            xs={12}
            style={{
              display: "flex",
              justifyContent: "start",
              marginLeft: "4%",
            }}
          >
            {props.club}
          </Grid>
        </Grid>
      )}
    </div>
  );
};

export default EventCard;
