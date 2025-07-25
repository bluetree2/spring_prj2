package com.example.prj2.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@ToString
@Table(name = "member", schema = "prj2")
public class Member {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, length = 100)
    private String name;

    @Column(name = "info", length = 10000)
    private String info;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_dt", insertable = false, updatable = false)
    private Instant createdDt;

}