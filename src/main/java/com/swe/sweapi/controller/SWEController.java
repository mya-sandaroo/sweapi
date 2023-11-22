package com.swe.sweapi.controller;

import com.swe.sweapi.entity.Meeting;
import com.swe.sweapi.entity.SWEUser;
import com.swe.sweapi.service.SWEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sweapi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SWEController {
    private final SWEService service;

    @GetMapping("/findAllUsers")
    public List<String> findAll() {
        return service.findAll().stream().map(SWEUser::getLoginId).collect(Collectors.toList());
    }

    @PostMapping("/login")
    public SWEUser welcome(@RequestBody SWEUser user) {
        return service.login(user);
    }

    @PostMapping ("/findAllMeetings")
    public ResponseEntity<List<Meeting>> findAllMeetings(@RequestBody SWEUser user) {
        if(Objects.nonNull(service.login(user))) {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllMeetings(user.getLoginId()));
        } else {
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(Collections.emptyList());
        }
    }

    @PostMapping("/saveMeeting/{loginId}")
    public ResponseEntity<List<Meeting>> saveMeeting(@RequestBody Meeting meeting, @PathVariable("loginId") String loginId) {
        service.save(meeting);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllMeetings(loginId));
    }

    @PostMapping("/deleteMeeting/{loginId}")
    public ResponseEntity<List<Meeting>> deleteMeeting(@RequestBody Meeting meeting, @PathVariable("loginId") String loginId) {
        service.delete(meeting);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllMeetings(loginId));
    }

    @PostMapping("/closeMeeting/{loginId}")
    public ResponseEntity<List<Meeting>> closeMeeting(@RequestBody Meeting meeting, @PathVariable("loginId") String loginId) {
        service.close(meeting);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllMeetings(loginId));
    }
}
