package com.fazliddin.fullyme.entity;

import com.fazliddin.fullyme.entity.templete.AbstractUUID;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted=false")
@SQLDelete(sql = "update courses set deleted = false where id = ?")
public class Course extends AbstractUUID {

    private String name;

    @Column(name = "about" , columnDefinition = "text")
    private String about;

    private Double price;

    @Column(name = "requirements" , columnDefinition = "text")
    private String requirements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;
}
