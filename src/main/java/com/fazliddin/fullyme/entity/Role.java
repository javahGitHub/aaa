package com.fazliddin.fullyme.entity;


import com.fazliddin.fullyme.entity.templete.AbstractLong;
import com.fazliddin.fullyme.enums.PermissionEnum;
import com.fazliddin.fullyme.enums.RoleTypeEnum;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted=false")
@SQLDelete(sql = "update roles set deleted = false where id = ?")
public class Role extends AbstractLong {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum roleType;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<PermissionEnum> permissionEnums;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
