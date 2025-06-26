package com.example.prj2.member.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.example.prj2.member.entity.Member}
 */
@Data
public class MemberDto implements Serializable {
    String id;
    String password;
    String name;
    String info;
    Instant createdDt;
}