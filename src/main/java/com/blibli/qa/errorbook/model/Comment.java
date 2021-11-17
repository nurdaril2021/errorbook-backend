package com.blibli.qa.errorbook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "comment")
public class Comment {

    @Id
    private String id;
    private String errorType;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String username;
    private String comment;

    public Comment(String errorType, LocalDateTime createdDate, String username, String comment) {
        this.errorType = errorType;
        this.createdDate = createdDate;
        this.username = username;
        this.comment = comment;
    }
}