import { makeStyles } from "@mui/styles";
import Grid from "@mui/material/Grid";
import BilboardTextField from "../../components/BilboardTextField";
import BilboardButton from "../../components/BilboardButton";
import Constants from "../../utils/Constants";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import React from "react";

const CreateClassroomScreen = () => {
  const [building, setBuilding] = React.useState("");
  const [classroom, setClassroom] = React.useState("");

  const handleBuildingSelect = (e) => {
    setBuilding(e.target.value);
  };

  const handleClassroomName = (e) => {
    setClassroom(e.target.value);
  }

  const handleAddButton = () => {
    console.log(building, classroom)
  }
  return (
    <div style={{ height: "70vh", display: "flex", alignItems: "center" }}>
      <Grid container>
        <Grid
          item
          xs={12}
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: "20px",
          }}
        >
          <div
            style={{
              fontFamily: Constants.OXYGEN_FONT_FAMILY,
              fontSize: "28px",
            }}
          >
            Please Enter Classroom:
          </div>
        </Grid>

        <Grid
          item
          xs={12}
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: "20px",
          }}
        >
          <FormControl sx={{ width: 300 }}>
            <InputLabel id="building-select-label">
              Please Select Building First
            </InputLabel>
            <Select
              value={building}
              labelId="building-select-label"
              id="building-select"
              label="Please Select Building First"
              onChange={handleBuildingSelect}
            >
              <MenuItem value={"Building1"}>Building1</MenuItem>
              <MenuItem value={"Building2"}>Building2</MenuItem>
              <MenuItem value={"Building3"}>Building3</MenuItem>
            </Select>
          </FormControl>
        </Grid>

        {building !== "" && (
          <Grid container>
            <Grid
              item
              xs={12}
              style={{
                display: "flex",
                justifyContent: "center",
                marginBottom: "20px",
              }}
            >
              <BilboardTextField onChange={handleClassroomName} label="Classroom Name" width="300px" />
            </Grid>

            <Grid
              item
              xs={12}
              style={{
                display: "flex",
                justifyContent: "center",
                marginBottom: "20px",
              }}
            >
              <BilboardButton onClick={handleAddButton} text="Add" fontSize="16px" width="100px" />
            </Grid>
          </Grid>
        )}
      </Grid>
    </div>
  );
};

export default CreateClassroomScreen;
