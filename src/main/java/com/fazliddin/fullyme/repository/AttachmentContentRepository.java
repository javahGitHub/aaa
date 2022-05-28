package com.fazliddin.fullyme.repository;

import com.fazliddin.fullyme.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent , UUID> {

    Optional<AttachmentContent> findByAttachmentId(UUID id);
}
