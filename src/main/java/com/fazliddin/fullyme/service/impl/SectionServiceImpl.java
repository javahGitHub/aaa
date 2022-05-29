package com.fazliddin.fullyme.service.impl;

import com.fazliddin.fullyme.common.MessageService;
import com.fazliddin.fullyme.entity.Section;
import com.fazliddin.fullyme.exception.RestException;
import com.fazliddin.fullyme.mapper.SectionMapper;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.SectionDto;
import com.fazliddin.fullyme.payload.resp.SectionRespDto;
import com.fazliddin.fullyme.repository.SectionRepository;
import com.fazliddin.fullyme.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    @Override
    public ApiResult<SectionRespDto> get(UUID id) {
        Section section = sectionRepository.findById(id).orElseThrow(() -> RestException.notFound("SECTION"));
        return ApiResult.successResponse(sectionMapper.toSectionRespDto(section));
    }

    @Override
    public ApiResult<?> create( SectionDto section) {
        return null;
    }

    @Override
    public ApiResult<?> edit(UUID id, SectionDto section) {
        return null;
    }

    @Override
    public ApiResult<?> delete(UUID id) {
        try {
            sectionRepository.deleteById(id);
            return ApiResult.successResponse(MessageService.successDelete("SECTION"));
        } catch (Exception e) {
            throw RestException.notFound("SECTION");
        }
    }
}
