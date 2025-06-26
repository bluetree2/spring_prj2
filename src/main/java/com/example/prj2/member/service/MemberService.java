package com.example.prj2.member.service;

import com.example.prj2.member.dto.MemberDto;
import com.example.prj2.member.dto.MemberForm;
import com.example.prj2.member.entity.Member;
import com.example.prj2.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public void add(MemberForm mf) {
                try {
                    System.out.println("mf = " + mf);
                    if(idcheck(mf)){
                        Member member = new Member();
                        member.setId(mf.getId());
                        member.setName(mf.getName());
                        member.setPassword(mf.getPassword());
                        member.setInfo(mf.getInfo());
                        memberRepository.save(member);
                    }
                }catch (DuplicateKeyException e){
                    throw new DuplicateKeyException(e.getMessage());
                }
    }

    public boolean idcheck(MemberForm mf) {
        Optional<Member> idcheck = memberRepository.findById(mf.getId());
        if (idcheck.isEmpty()) {
            Optional<Member> namecheck = memberRepository.findByName(mf.getName());
            if (namecheck.isEmpty()) {
                return true;
            }else{
                throw new RuntimeException((mf.getName())+"는 이미 있는 이름입니다");
            }
        }else {
            throw new RuntimeException((mf.getId()) + "는 이미 사용중인 아이디입니다");
        }
    }

    public boolean login(String id, String password, HttpSession session) {
        Optional<Member> db = memberRepository.findById(id);
        if (db.isPresent()){
            String pw = db.get().getPassword();
            if (pw.equals(password)) {
                addUserToSession(session, db.get());
                return true;
            }
        }
        return false;
    }

    private static void addUserToSession(HttpSession session, Member member) {
        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setInfo(member.getInfo());
        dto.setCreatedDt(member.getCreatedDt());

        session.setAttribute("loggedInUser", dto);
    }
}
