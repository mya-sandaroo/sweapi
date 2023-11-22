package com.swe.sweapi.repository;

import com.swe.sweapi.entity.Attendee;
import com.swe.sweapi.entity.Meeting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingInfoRepository extends CrudRepository<Meeting, Long> {
    @Query("SELECT distinct m FROM Meeting m join Attendee a on m.id = a.meeting.id join SWEUser u on u.id = a.sweUser.id where u.loginId = :createdBy or m.createdBy = :createdBy")
    List<Meeting> findAllByCreatedBy(@Param("createdBy") String createdBy);
}
