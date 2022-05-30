package com.fazliddin.fullyme.mapper;

import com.fazliddin.fullyme.entity.Course;
import com.fazliddin.fullyme.payload.resp.CourseRespDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mappings({
            @Mapping(target = "authorId",source = "entity.author.id"),
            @Mapping(target = "sectionId" , source = "entity.section.id")
    })
    CourseRespDto toCourseRespDto(Course course);

}
