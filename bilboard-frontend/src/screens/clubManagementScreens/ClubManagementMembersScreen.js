import Constants from "../../utils/Constants";
import MemberComponent from "./clubManagementComponents/MemberComponent";
import Grid from "@mui/material/Grid";

const ClubManagementMembersScreen = ( { club } ) => {


    return (
        <div>
            <div style={ { fontSize: "40px" } }>Members</div>
            <Grid container style={ { marginLeft: "20px" } }>
                <Grid item xs={ 3 } style={ { marginTop: "30px", marginBottom: "20px" } }>
                    <div style={ { fontSize: "24px", fontFamily: "Oxygen, sans-serif" } }>
                        Student Name
                    </div>
                </Grid>
                <Grid item xs={ 2 } style={ { marginTop: "30px", marginBottom: "20px" } }>
                    <div style={ { fontSize: "24px", fontFamily: "Oxygen, sans-serif" } }>
                        Attended Event
                    </div>
                </Grid>
                <Grid item xs={ 1 } style={ { marginTop: "30px", marginBottom: "20px" } }>
                    <div style={ { fontSize: "24px", fontFamily: "Oxygen, sans-serif" } }>
                        Ge Taken
                    </div>
                </Grid>
                <Grid item xs={ 1 } style={ { marginTop: "30px", marginBottom: "20px" } }>
                    <div style={ { fontSize: "24px", fontFamily: "Oxygen, sans-serif" } }>
                        Ge Point
                    </div>
                </Grid>
                <Grid item xs={ 2 } style={ { marginTop: "30px", marginBottom: "20px" } }>
                    <div style={ { fontSize: "24px", fontFamily: "Oxygen, sans-serif" } }>
                        Student ID
                    </div>
                </Grid>
                <Grid item xs={ 3 } style={ { marginTop: "30px", marginBottom: "20px" } }>
                    <div style={ { fontSize: "24px", fontFamily: "Oxygen, sans-serif" } }>
                        Operations
                    </div>
                </Grid>
            </Grid>
            { club.clubMembers.sort( ( a, b ) => ( a.user.name > b.user.name ) ? 1 : -1 )
                  .map( ( member, index ) => (
                      <div key={ index } style={ { marginLeft: "24px" } }>
                          <MemberComponent name={ member.user.name } surname={ member.user.surname }
                                           ID={ member.user.bilkentId } eventCount={ member.attendedEventCount }
                                           geTaken={ member.user.geTaken } gePoint={ member.gePoint }/>
                      </div>
                  ) ) }
        </div>
    );
};

export default ClubManagementMembersScreen;
