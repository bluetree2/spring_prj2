package com.example.prj2.board.dto;

import com.example.prj2.member.dto.MemberDto;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.prj2.board.entity.Todolist}
 */
@Data
public class TodolistDto implements Serializable {
    Integer id;
    String todoTitle;
    String todoContent;
    LocalDateTime startedDt;
    LocalDateTime finishedDt;
    Boolean completed;
    LocalDateTime createdDt;
    LocalDateTime updatedDt;
    String username;
    MemberDto Member;

}