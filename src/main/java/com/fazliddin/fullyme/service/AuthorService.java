package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.entity.Author;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.CustomPage;
import com.fazliddin.fullyme.payload.req.AuthorDto;
import com.fazliddin.fullyme.payload.resp.AuthorRespDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AuthorService {

    ApiResult<CustomPage<AuthorRespDto>> getAll(Integer page, Integer size);

    ApiResult<AuthorRespDto> get(UUID id);

    ApiResult<?> create(AuthorDto author);

    ApiResult<?> edit(UUID id, AuthorDto author);

    CustomPage<AuthorRespDto> makeCustomPage(Page<Author> products);
}
