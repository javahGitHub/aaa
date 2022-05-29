package com.fazliddin.fullyme.controller;

import com.fazliddin.fullyme.constants.AppConstants;
import com.fazliddin.fullyme.entity.Author;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.CustomPage;
import com.fazliddin.fullyme.payload.req.AuthorDto;
import com.fazliddin.fullyme.payload.resp.AuthorRespDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.fazliddin.fullyme.constants.AppConstants.DEFAULT_PAGE_NUMBER;
import static com.fazliddin.fullyme.constants.AppConstants.DEFAULT_PAGE_SIZE;
@RequestMapping(AuthorController.AUTHOR_CONTROLLER)
public interface AuthorController {
    String AUTHOR_CONTROLLER = AppConstants.BASE_URL + "/author";

    @GetMapping()
    ApiResult<CustomPage<AuthorRespDto>> getAll(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer page,
                                                @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size);

    @GetMapping("/{id}")
    ApiResult<AuthorRespDto> get(@PathVariable UUID id);

    @PostMapping("/create")
    ApiResult<?> create(@RequestBody @Valid AuthorDto author);

    @PutMapping("/edit/{id}")
    ApiResult<?> edit(@PathVariable UUID id, @RequestBody @Valid AuthorDto author);
}
