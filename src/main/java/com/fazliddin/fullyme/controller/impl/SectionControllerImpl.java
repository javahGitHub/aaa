package com.fazliddin.fullyme.controller.impl;

import com.fazliddin.fullyme.controller.SectionController;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.SectionDto;
import com.fazliddin.fullyme.payload.resp.SectionRespDto;
import com.fazliddin.fullyme.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SectionControllerImpl implements SectionController {

    private final SectionService sectionService;

    @Override
    public ApiResult<SectionRespDto> get(UUID id) {
        return sectionService.get(id);
    }

    @Override
    public ApiResult<?> create(SectionDto section) {
        return sectionService.create(section);
    }

    @Override
    public ApiResult<?> edit(UUID id, SectionDto section) {
        return sectionService.edit(id,section);
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        return sectionService.delete(id);
    }
}
