package com.example.prj2.board.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.prj2.board.entity.Todolist}
 */
@Value
public class TodolistDto implements Serializable {
    String id;
    String todoTitle;
    String todoContent;
    LocalDateTime startedDt;
    LocalDateTime finishedDt;
    Boolean completed;
    LocalDateTime createdDt;
    LocalDateTime updatedDt;
}