package com.fazliddin.fullyme.controller;

import com.fazliddin.fullyme.constants.AppConstants;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.SectionDto;
import com.fazliddin.fullyme.payload.resp.SectionRespDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
@RequestMapping(SectionController.SECTION_CONTROLLER)
public interface SectionController {

    String SECTION_CONTROLLER = AppConstants.BASE_URL + "/section";

    @GetMapping("/{id}")
    ApiResult<SectionRespDto> get(@PathVariable UUID id);

    @PostMapping("/create")
    ApiResult<?> create (@RequestBody @Valid SectionDto section);

    @PutMapping("/edit/{id}")
    ApiResult<?> edit(@PathVariable UUID id, @RequestBody @Valid SectionDto section);

    @DeleteMapping("/del/{id}")
    ApiResult<?> delete(@PathVariable UUID id);
}
