package com.fazliddin.fullyme.repository;

import com.fazliddin.fullyme.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
