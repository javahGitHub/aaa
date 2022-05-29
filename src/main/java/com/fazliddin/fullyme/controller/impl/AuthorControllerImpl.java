package com.fazliddin.fullyme.controller.impl;

import com.fazliddin.fullyme.controller.AuthorController;
import com.fazliddin.fullyme.entity.Author;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.CustomPage;
import com.fazliddin.fullyme.payload.req.AuthorDto;
import com.fazliddin.fullyme.payload.resp.AuthorRespDto;
import com.fazliddin.fullyme.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    public ApiResult<CustomPage<AuthorRespDto>> getAll(Integer page, Integer size) {
       return authorService.getAll(page, size);
    }

    @Override
    public ApiResult<AuthorRespDto> get(UUID id) {
        return authorService.get(id);
    }

    @Override
    public ApiResult<?> create(AuthorDto author) {
        return authorService.create(author);
    }

    @Override
    public ApiResult<?> edit(UUID id, AuthorDto author) {
        return authorService.edit(id, author);
    }
}
