package com.example.prj2.board.dto;

import com.example.prj2.member.dto.MemberDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoFrom {
    String id;
    MemberDto member;
    String todoTitle;
    String todoContent;
    LocalDateTime startedDt;
    LocalDateTime finishedDt;
    Boolean completed;
    LocalDateTime createdDt;
    LocalDateTime updatedDt;
}
