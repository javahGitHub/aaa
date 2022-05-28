package com.fazliddin.fullyme.entity;

import com.fazliddin.fullyme.entity.templete.AbstractUUID;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted=false")
@SQLDelete(sql = "update authors set deleted = false where id = ?")
public class Author extends AbstractUUID {

    private String firsName;
    private String lastName;

    @Column(name = "about" , columnDefinition = "text")
    private String about;

    @Column(name = "website_link")
    private String websiteLink;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "linkedIn_Link")
    private String linkedInLink;

}
