package com.jack_the_coder.bilboard_backend.model.requestModel;

import java.util.Date;
import java.util.List;

public class CreateTimeSlotRequest {

    private List<Date> startTimeList;
    private List<Date> endTimeList;
    private long classroomDay;

    public List<Date> getStartTimeList () {
        return startTimeList;
    }

    public void setStartTimeList ( List<Date> startTimeList ) {
        this.startTimeList = startTimeList;
    }

    public List<Date> getEndTimeList () {
        return endTimeList;
    }

    public void setEndTimeList ( List<Date> endTimeList ) {
        this.endTimeList = endTimeList;
    }

    public long getClassroomDay () {
        return classroomDay;
    }

    public void setClassroomDay ( long classroomDay ) {
        this.classroomDay = classroomDay;
    }
}
