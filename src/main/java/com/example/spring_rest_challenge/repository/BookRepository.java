package com.example.spring_rest_challenge.repository;

import com.example.spring_rest_challenge.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
