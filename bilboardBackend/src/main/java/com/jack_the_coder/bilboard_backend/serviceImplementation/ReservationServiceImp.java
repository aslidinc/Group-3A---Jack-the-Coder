package com.jack_the_coder.bilboard_backend.serviceImplementation;

import com.jack_the_coder.bilboard_backend.exception.UserServiceException;
import com.jack_the_coder.bilboard_backend.io.entity.*;
import com.jack_the_coder.bilboard_backend.io.repository.*;
import com.jack_the_coder.bilboard_backend.model.requestModel.CreateBuildingRequest;
import com.jack_the_coder.bilboard_backend.model.requestModel.CreateClassroomDayRequest;
import com.jack_the_coder.bilboard_backend.model.requestModel.CreateClassroomRequest;
import com.jack_the_coder.bilboard_backend.model.requestModel.CreateTimeSlotRequest;
import com.jack_the_coder.bilboard_backend.service.ReservationService;
import com.jack_the_coder.bilboard_backend.shared.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Hacı Çakın
 * @apiNote This class enables communication between controller and repository
 * @since 10.12.2021
 */
@Service
public class ReservationServiceImp implements ReservationService {


    @Autowired
    LocationRequestRepository locationRequestRepository;

    @Autowired
    LocationRequestTimeSlotRepository locationRequestTimeSlotRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    ClassroomDayRepository classroomDayRepository;

    @Autowired
    TimeSlotRepository timeSlotRepository;

    /**
     * Method for creating a location request
     * @param timeSlotIdList is a List<Long>
     * @param eventEntity    is a EventEntity
     * @return LocationRequestDto
     * @apiNote Method for creating a location request
     */
    @Override
    public LocationRequestDto createLocationRequest ( List<Long> timeSlotIdList , EventEntity eventEntity ) {
        ModelMapper modelMapper = new ModelMapper();

        LocationRequestDto locationRequestDto = new LocationRequestDto();
        locationRequestDto.setAnswered( false );
        locationRequestDto.setConfirmed( false );
        locationRequestDto.setNeededNumberOfSpaces( eventEntity.getMaxParticipationCount() );
        locationRequestDto.setEvent( eventEntity );
        LocationRequestEntity locationRequestEntity =
                modelMapper.map( locationRequestDto , LocationRequestEntity.class );
        LocationRequestEntity created = locationRequestRepository.save( locationRequestEntity );

        List<LocationRequestTimeSlotEntity> requestTimeSlots = new ArrayList<>();

        timeSlotIdList.forEach( id -> {
            Optional<TimeSlotEntity> timeSlotEntity = timeSlotRepository.findById( id );

            if ( timeSlotEntity.isPresent() ) {
                LocationRequestTimeSlotDto locationRequestTimeSlotDto = new LocationRequestTimeSlotDto();
                locationRequestTimeSlotDto.setTimeSlot( timeSlotEntity.get() );
                locationRequestTimeSlotDto.setLocationRequest( created );
                requestTimeSlots.add( modelMapper.map( locationRequestDto , LocationRequestTimeSlotEntity.class ) );
            }
        } );

        List<LocationRequestTimeSlotEntity> createdTimeSlots =
                locationRequestTimeSlotRepository.saveAll( requestTimeSlots );
        created.setLocationRequestTimeSlots( createdTimeSlots );
        return modelMapper.map( created , LocationRequestDto.class );
    }

    /**
     * Method for creating a building
     * @param createBuildingRequest is a CreateBuildingRequest
     * @return BuildingDto
     * @apiNote Method for creating a building
     */
    @Override
    public BuildingDto createBuilding ( CreateBuildingRequest createBuildingRequest ) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Optional<UniversityEntity> universityEntity =
                    universityRepository.findById( createBuildingRequest.getUniversity() );

            if ( universityEntity.isPresent() ) {
                BuildingDto buildingDto = new BuildingDto();
                buildingDto.setName( createBuildingRequest.getName() );
                buildingDto.setUniversity( universityEntity.get() );
                BuildingEntity created =
                        buildingRepository.save( modelMapper.map( buildingDto , BuildingEntity.class ) );

                return modelMapper.map( created , BuildingDto.class );
            } else {
                throw new UserServiceException( "University is NOT found!" );
            }
        } catch ( Exception e ) {
            throw new UserServiceException( e.getMessage() );
        }
    }

    /**
     * Method for creating a classroom
     * @param createClassroomRequest is a CreateClassroomRequest
     * @return ClassroomDto
     * @apiNote Method for creating a classroom
     */
    @Override
    public ClassroomDto createClassroom ( CreateClassroomRequest createClassroomRequest ) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Optional<BuildingEntity> buildingEntity =
                    buildingRepository.findById( createClassroomRequest.getBuilding() );

            if ( buildingEntity.isPresent() ) {
                ClassroomDto classroomDto = new ClassroomDto();
                classroomDto.setName( createClassroomRequest.getName() );
                classroomDto.setBuilding( buildingEntity.get() );
                classroomDto.setCapacity( createClassroomRequest.getCapacity() );
                ClassroomEntity created = classroomRepository.save( modelMapper.map( classroomDto ,
                        ClassroomEntity.class ) );

                return modelMapper.map( created , ClassroomDto.class );
            } else {
                throw new UserServiceException( "Building is NOT found!" );
            }
        } catch ( Exception e ) {
            throw new UserServiceException( e.getMessage() );
        }
    }

    /**
     * Method for creating a classroom days
     * @param createClassroomDayRequest is a CreateClassroomDayRequest
     * @return List<ClassroomDayDto>
     * @apiNote Method for creating a classroom days
     */
    @Override
    public List<ClassroomDayDto> createClassroomDays ( CreateClassroomDayRequest createClassroomDayRequest ) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Optional<ClassroomEntity> classroomEntity =
                    classroomRepository.findById( createClassroomDayRequest.getClassroom() );

            if ( classroomEntity.isPresent() ) {
                List<ClassroomDayEntity> classroomDays = new ArrayList<>();

                createClassroomDayRequest.getDayDates().forEach( date -> {
                    ClassroomDayDto classroomDayDto = new ClassroomDayDto();
                    classroomDayDto.setDayDate( date );
                    classroomDayDto.setClassroom( classroomEntity.get() );
                    classroomDays.add( modelMapper.map( classroomDayDto , ClassroomDayEntity.class ) );
                } );
                List<ClassroomDayEntity> createdClassroomDays
                        = classroomDayRepository.saveAll( classroomDays );

                List<ClassroomDayDto> createdDtoList = new ArrayList<>();
                createdClassroomDays.forEach( classroomDayEntity -> {
                    createdDtoList.add( modelMapper.map( classroomDayEntity , ClassroomDayDto.class ) );
                } );

                return createdDtoList;
            } else {
                throw new UserServiceException( "Classroom is NOT found!" );
            }
        } catch ( Exception e ) {
            throw new UserServiceException( e.getMessage() );
        }
    }

    /**
     * Method for creating time slots
     * @param createTimeSlotRequest is a CreateTimeSlotRequest
     * @return List<TimeSlotDto>
     * @apiNote Method for creating time slots
     */
    @Override
    public List<TimeSlotDto> createTimeSlots ( CreateTimeSlotRequest createTimeSlotRequest ) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Optional<ClassroomDayEntity> classroomDayEntity =
                    classroomDayRepository.findById( createTimeSlotRequest.getClassroomDay() );

            if ( classroomDayEntity.isPresent() ) {
                List<TimeSlotEntity> timeSlots = new ArrayList<>();

                for ( int a = 0 ; a < createTimeSlotRequest.getStartTimeList().size() ; a++ ) {
                    TimeSlotDto timeSlotDto = new TimeSlotDto();
                    timeSlotDto.setStartTime( createTimeSlotRequest.getStartTimeList().get( a ) );
                    timeSlotDto.setEndTime( createTimeSlotRequest.getEndTimeList().get( a ) );
                    timeSlotDto.setClassroomDay( classroomDayEntity.get() );
                    timeSlots.add( modelMapper.map( timeSlotDto , TimeSlotEntity.class ) );
                }
                List<TimeSlotEntity> createdTimeSlots
                        = timeSlotRepository.saveAll( timeSlots );

                List<TimeSlotDto> createdDtoList = new ArrayList<>();
                createdTimeSlots.forEach( timeSlotEntity -> {
                    createdDtoList.add( modelMapper.map( timeSlotEntity , TimeSlotDto.class ) );
                } );

                return createdDtoList;
            } else {
                throw new UserServiceException( "Classroom is NOT found!" );
            }
        } catch ( Exception e ) {
            throw new UserServiceException( e.getMessage() );
        }
    }

    /**
     * Method for deleting location requests
     * @param locationRequestEntityList is a List<LocationRequestEntity>
     * @return Boolean
     * @apiNote Method for deleting location requests
     */
    @Override
    public Boolean deleteLocationRequests ( List<LocationRequestEntity> locationRequestEntityList ) {
        try {
            locationRequestEntityList.forEach( locationRequestEntity -> {
                locationRequestTimeSlotRepository.deleteAll( locationRequestEntity.getLocationRequestTimeSlots() );
            } );

            locationRequestRepository.deleteAll( locationRequestEntityList );

            return true;
        } catch ( Exception e ) {
            return false;
        }
    }

    /**
     * Method for responding to a location request
     * @param requestId is a long
     * @param status    is a boolean
     * @return Boolean
     * @apiNote Method for responding to a location request
     */
    @Override
    public Boolean respondLocationRequest ( long requestId , boolean status ) {
        try {
            Optional<LocationRequestEntity> optional = locationRequestRepository.findById( requestId );

            if ( optional.isPresent() ) {
                optional.get().setAnswered( true );
                optional.get().setConfirmed( status );
                locationRequestRepository.save( optional.get() );
                return true;
            } else {
                return false;
            }
        } catch ( Exception e ) {
            return false;
        }
    }

    /**
     * Method for getting all the buildings
     * @return List<BuildingDto> is a list of building dto instances
     * @apiNote Method for getting all the buildings in the university
     */
    @Override
    public List<BuildingDto> getAllBuildings () {
        try {

            ModelMapper modelMapper = new ModelMapper();
            List<BuildingDto> buildingDtoList = new ArrayList<>();
            buildingRepository.findAll().forEach( buildingEntity -> {
                buildingDtoList.add( modelMapper.map( buildingEntity , BuildingDto.class ) );
            } );
            return buildingDtoList;
        } catch ( Exception e ) {
            return new ArrayList<>();
        }
    }

    /**
     * Method for getting all the location requests
     * @return List<LocationRequestDto> is a list of location request dto instances
     * @apiNote Method for getting all the location requests in the university
     */
    @Override
    public List<LocationRequestDto> getAllLocationRequests () {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<LocationRequestDto> locationRequestDtoList = new ArrayList<>();
            locationRequestRepository.findAll().forEach( locationRequestEntity -> {
                locationRequestDtoList.add( modelMapper.map( locationRequestEntity , LocationRequestDto.class ) );
            } );
            return locationRequestDtoList;
        } catch ( Exception e ) {
            return new ArrayList<>();
        }
    }
}
