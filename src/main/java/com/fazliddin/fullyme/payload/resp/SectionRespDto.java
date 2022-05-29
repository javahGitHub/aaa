package com.fazliddin.fullyme.payload.resp;

import com.fazliddin.fullyme.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SectionRespDto {

    private String name;
    private List<Attachment> videos;

}
