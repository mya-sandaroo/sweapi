package com.swe.sweapi.repository;

import com.swe.sweapi.entity.Attendee;
import com.swe.sweapi.entity.Meeting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendeeRepository extends CrudRepository<Attendee, Long> {
    @Query( "SELECT a FROM SWEUser u join u.attendees a WHERE a.meeting = :meeting_id")
    List<Attendee> findByMeeting(@Param("meeting_id") Meeting meeting);
}
