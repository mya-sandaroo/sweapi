package com.swe.sweapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class SWEUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login_Id")
    private String loginId;
    @Column(name = "login_Name")
    private String loginName;
    @Column(name = "login_Password")
    private String loginPassword;
    @OneToMany(mappedBy = "sweUser", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    List<Attendee> attendees;
}
