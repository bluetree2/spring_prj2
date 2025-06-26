package com.example.prj2.board.dto;

import java.time.LocalDateTime;

public interface BoardListInfo {
    public Integer getId();
    public String gettodoTitle();
    public String gettodoContent();
//    todo : memberlistinfo 수정
//    public MemberListInfo getWriter();
    public String getUsername();
    public boolean getcompleted();
    public LocalDateTime getStartedDt();
    public LocalDateTime getfinishedDt();
    public LocalDateTime getCreatedDt();
    public LocalDateTime getUpdatedDt();
}
