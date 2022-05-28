package com.fazliddin.fullyme.repository;

import com.fazliddin.fullyme.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment , UUID> {

}
