USE prj2;

Create table member
(
    id         VARCHAR(26)    NOT NULL,
    password   VARCHAR(255)   NOT NULL,
    nickname   VARCHAR(26)    NOT NULL,
    info       VARCHAR(10000) Null,
    created_dt datetime       NOT NULL DEFAULT NOW(),
    updated_dt datetime       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_member PRIMARY KEY (id)
);

CREATE TABLE todolist
(
    id           VARCHAR(26)   NOT NULL,
    todo_title   VARCHAR(255)  NOT NULL,
    todo_content VARCHAR(8000) NULL,
    started_dt   datetime      NOT NULL DEFAULT (NOW() + INTERVAL 1 HOUR),
    finished_dt  datetime      NOT NULL DEFAULT (NOW() + INTERVAL 2 HOUR),
    completed    boolean       NOT NULL DEFAULT false,
    created_dt   datetime      NOT NULL DEFAULT NOW(),
    updated_dt   datetime      NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_todolist PRIMARY KEY (id)

);

Alter TABLE todolist
    ADD FOREIGN KEY (id) REFERENCES member (id);

Alter TABLE todolist
    ADD FOREIGN KEY (username) REFERENCES member (id);
# ALTER TABLE board
#     MODIFY writer VARCHAR(100) NOT NULL;