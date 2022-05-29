package com.fazliddin.fullyme.repository;

import com.fazliddin.fullyme.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section , UUID> {
}
