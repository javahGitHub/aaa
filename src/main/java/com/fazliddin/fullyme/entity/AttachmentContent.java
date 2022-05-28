package com.fazliddin.fullyme.entity;

import com.fazliddin.fullyme.entity.templete.AbstractUUID;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "attachment_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted=false")
@SQLDelete(sql = "update attachment_content set deleted = false where id = ?")
public class AttachmentContent extends AbstractUUID {

    @Column(name = "bytes", nullable = false)
    private byte[] bytes;

    @OneToOne(optional = false)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;
}
