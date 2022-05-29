package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.SectionDto;
import com.fazliddin.fullyme.payload.resp.SectionRespDto;

import java.util.UUID;

public interface SectionService {
    ApiResult<SectionRespDto> get(UUID id);

    ApiResult<?> create(SectionDto section);

    ApiResult<?> edit(UUID id, SectionDto section);

    ApiResult<?> delete(UUID id);
}
