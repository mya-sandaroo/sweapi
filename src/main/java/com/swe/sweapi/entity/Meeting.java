package com.swe.sweapi.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Meeting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Attendee> attendees;

    @Column(name = "date_of_meeting")
    private String dateOfMeeting;

    @Column(name = "details")
    private String details;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "selected_location")
    private String selectedLocation;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Suggestion> suggestedLocations;

    @Transient
    List<String> attendeeList;

    public Set<String> getAttendeeList() {
        return this.attendees.stream().map(Attendee::getSweUser).map(SWEUser::getLoginId).collect(Collectors.toSet());
    }

    public List<String> getAttendeeUserList() {
        return this.attendeeList;
    }
}
