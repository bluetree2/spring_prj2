package com.example.prj2.board.service;

import com.example.prj2.board.dto.BoardListInfo;
import com.example.prj2.board.Repository.BoardRepository;
import com.example.prj2.board.dto.TodoFrom;
import com.example.prj2.board.dto.TodolistDto;
import com.example.prj2.board.entity.Todolist;
import com.example.prj2.member.dto.MemberDto;
import com.example.prj2.member.entity.Member;
import com.example.prj2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Map<String,Object> list(Integer page, String keyword) {
//        List<Board> list = boardRepository.findAll();

        Page<BoardListInfo> boardPage = null;

        if (keyword == null || keyword.isBlank()) {
            boardPage = boardRepository
                    .findAllBy(PageRequest.of(page -1, 10,
                            Sort.by("id").descending()));
        }else{
            boardPage = boardRepository
                    .searchByKeyword("%"+keyword+"%",
                            PageRequest.of(page - 1,10,
                                    Sort.by("id").descending()));
        }
        List<BoardListInfo> boardList = boardPage.getContent();

        Integer rightPageNumber = ((page - 1) / 10 + 1) * 10;
        Integer leftPageNumber = rightPageNumber - 9;
        rightPageNumber = Math.min(rightPageNumber, boardPage.getTotalPages());

        var result = Map.of("todoList", boardList,
                "totalElements", boardPage.getTotalElements(),
                "totalPages",boardPage.getTotalPages(),
                "rightPageNumber", rightPageNumber,
                "leftPageNumber", leftPageNumber,
                "currentPage",page
        );
        return result ;
    }

    public void add(TodoFrom data, MemberDto user) {
        Todolist todolist = new Todolist();
        todolist.setTodoTitle(data.getTodoTitle());
        todolist.setTodoContent(data.getTodoContent());
        todolist.setStartedDt(data.getStartedDt());
        todolist.setFinishedDt(data.getFinishedDt());
        if (data.getCompleted() == null) todolist.setCompleted(false);
        else todolist.setCompleted(data.getCompleted());

        Member member = memberRepository.findById(user.getId()).get();
        todolist.setMember(member);
        todolist.setUsername(member.getName());

        boardRepository.save(todolist);
    }

    public TodolistDto get(Integer id) {
        Todolist todo = boardRepository.findById(id).get();
        TodolistDto dto = new TodolistDto();
        dto.setId(todo.getId());
        dto.setTodoTitle(todo.getTodoTitle());
        dto.setTodoContent(todo.getTodoContent());
        dto.setStartedDt(todo.getStartedDt());
        dto.setFinishedDt(todo.getFinishedDt());
        dto.setCompleted(todo.getCompleted());

        MemberDto memberDto = new MemberDto();
//        memberDto.setId( .getWriter().getId());
//        memberDto.setName( .getWriter().getNickName());
        memberDto.setId(todo.getMember().getId());
        memberDto.setName(todo.getMember().getName());



        dto.setMember(memberDto);
//        dto.setMember(.getMember());
        dto.setCreatedDt(todo.getCreatedDt());
        System.out.println("todo = " + todo);
        System.out.println("todo.getCreatedDt() = " + todo.getCreatedDt());
        System.out.println("dto = " + dto);
        return dto;
    }
}
