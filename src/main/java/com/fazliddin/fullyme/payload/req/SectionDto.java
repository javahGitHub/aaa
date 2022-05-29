package com.fazliddin.fullyme.payload.req;

import com.fazliddin.fullyme.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SectionDto {

    private String name;
    private List<Attachment> videos;

}
