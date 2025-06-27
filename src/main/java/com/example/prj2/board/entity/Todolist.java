package com.example.prj2.board.entity;

import com.example.prj2.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@Table(name = "todolist", schema = "prj2")
public class Todolist {
    @Id
    @Column(name = "id", nullable = false, length = 26)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Member member;

    @Column(name = "todo_title", nullable = false)
    private String todoTitle;

    @Column(name = "todo_content", length = 8000)
    private String todoContent;

    @ColumnDefault("((now() + interval 1 hour))")
    @Column(name = "started_dt", nullable = false)
    private LocalDateTime startedDt;

    @ColumnDefault("((now() + interval 2 hour))")
    @Column(name = "finished_dt", nullable = false)
    private LocalDateTime finishedDt;

    @ColumnDefault("0")
    @Column(name = "completed")
    private Boolean completed = false;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_dt", insertable = false, updatable = false)
    private LocalDateTime createdDt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_dt", insertable = false)
    private LocalDateTime updatedDt;

    @Column(name = "username", nullable = false, length = 120)
    private String username;

}