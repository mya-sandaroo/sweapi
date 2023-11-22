CREATE TABLE IF NOT EXISTS SWEUSER(
    ID BIGINT PRIMARY KEY,
    Login_Id VARCHAR(255),
    Login_Name VARCHAR(255),
    Login_Password VARCHAR(255),
    primary key (ID)
    );
CREATE TABLE IF NOT EXISTS Meeting(
    ID BIGINT generated by default as identity,
    title VARCHAR(255),
    date_of_meeting VARCHAR(255),
    details VARCHAR(255),
    selected_location VARCHAR(255),
    created_by VARCHAR(255),
    status VARCHAR(255),
    primary key (ID)
    );

CREATE TABLE IF NOT EXISTS Attendee(
                                       ID BIGINT generated by default as identity,
                                       user_id BIGINT,
                                       meeting_id BIGINT,
                                       primary key (ID),
    CONSTRAINT `fk_meeting` FOREIGN KEY (`meeting_id`) REFERENCES `meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `SWEUser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS Suggestion(
                                         ID BIGINT generated by default as identity,
                                         location VARCHAR(255),
    suggested_by VARCHAR(255),
    meeting_id BIGINT,
    primary key (ID),
    CONSTRAINT `fk_suggestion_meeting` FOREIGN KEY (`meeting_id`) REFERENCES `meeting` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
    );