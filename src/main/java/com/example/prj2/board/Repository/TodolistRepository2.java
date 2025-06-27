package com.example.prj2.board.Repository;

import com.example.prj2.board.entity.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodolistRepository2 extends JpaRepository<Todolist, Integer> {
}