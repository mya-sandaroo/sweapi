package com.swe.sweapi.service;

import com.google.common.collect.Lists;
import com.swe.sweapi.entity.Attendee;
import com.swe.sweapi.entity.Meeting;
import com.swe.sweapi.entity.SWEUser;
import com.swe.sweapi.entity.Suggestion;
import com.swe.sweapi.repository.AttendeeRepository;
import com.swe.sweapi.repository.MeetingInfoRepository;
import com.swe.sweapi.repository.SWEUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SWEService {
    private final SWEUserRepository userRepository;
    private final MeetingInfoRepository meetingInfoRepository;
    public final AttendeeRepository attendeeRepository;

    public List<SWEUser> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public List<Meeting> findAllMeetings(String userId) {
        return meetingInfoRepository.findAllByCreatedBy(userId);
    }

    public SWEUser login(@RequestBody SWEUser user) {
        return userRepository.findByLoginIdAndLoginPassword(user.getLoginId(), user.getLoginPassword());
    }
    @Transactional
    public Meeting save(Meeting meeting) {
        if(!meeting.getStatus().equalsIgnoreCase("Closed")) {
            List<Attendee> attendeeList = new ArrayList<>();
            for(String userId: meeting.getAttendeeUserList()) {
                SWEUser user = userRepository.findByLoginId(userId);
                attendeeList.add(Attendee.builder().sweUser(user).meeting(meeting).build());
            }

            if(Objects.nonNull(meeting.getSuggestedLocations())) {
                for(Suggestion suggestion: meeting.getSuggestedLocations()) {
                    suggestion.setMeeting(meeting);
                }
            }

            meeting.setAttendees(attendeeList);
            meeting.setSuggestedLocations(meeting.getSuggestedLocations());
            meetingInfoRepository.save(meeting);
        } else {
            //Session has already closed, can not update or join.
        }

        return meeting;
    }

    @Transactional
    public void delete(Meeting meeting) {
        this.meetingInfoRepository.delete(meeting);
    }

    @Transactional
    public void close(Meeting meeting) {
        Random rndm = new Random();
        Optional<Meeting> meetingOptional = this.meetingInfoRepository.findById(meeting.getId());
        if(Objects.nonNull(meetingOptional) && Objects.nonNull(meeting.getSuggestedLocations())) {
            Suggestion rndmElem = meeting.getSuggestedLocations().get(rndm.nextInt(meeting.getSuggestedLocations().size()));
            Meeting meetingDB = meetingOptional.get();
            meetingDB.setSelectedLocation(rndmElem.getLocation());
            meetingDB.setStatus("Closed");
            this.meetingInfoRepository.save(meetingDB);
        }
    }
}
