package com.fazliddin.fullyme.entity.templete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractLong extends AbsUserAuditing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
