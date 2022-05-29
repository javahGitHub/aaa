package com.fazliddin.fullyme.mapper;

import com.fazliddin.fullyme.entity.Author;
import com.fazliddin.fullyme.payload.req.AuthorDto;
import com.fazliddin.fullyme.payload.resp.AuthorRespDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorRespDto toAuthorRespDto(Author author);

    AuthorDto toAuthorDto(Author finded);
}
