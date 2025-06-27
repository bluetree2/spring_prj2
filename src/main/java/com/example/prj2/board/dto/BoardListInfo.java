package com.example.prj2.board.dto;

import java.time.LocalDateTime;

public interface BoardListInfo {
    public Integer getId();
    public String getTodoTitle();
    public String getTodoContent();
//    public MemberListInfo getWriter();
    public String getUsername();
    public boolean getCompleted();
    public LocalDateTime getStartedDt();
    public LocalDateTime getFinishedDt();
    public LocalDateTime getCreatedDt();
    public LocalDateTime getUpdatedDt();
}
