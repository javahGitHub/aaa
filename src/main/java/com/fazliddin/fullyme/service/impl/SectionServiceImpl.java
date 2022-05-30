package com.fazliddin.fullyme.service.impl;

import com.fazliddin.fullyme.common.MessageService;
import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.entity.Course;
import com.fazliddin.fullyme.entity.Section;
import com.fazliddin.fullyme.exception.RestException;
import com.fazliddin.fullyme.mapper.SectionMapper;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.SectionDto;
import com.fazliddin.fullyme.payload.resp.SectionRespDto;
import com.fazliddin.fullyme.repository.AttachmentRepository;
import com.fazliddin.fullyme.repository.SectionRepository;
import com.fazliddin.fullyme.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final AttachmentRepository attachmentRepository;
    private final SectionMapper sectionMapper;

    @Override
    public ApiResult<SectionRespDto> get(UUID id) {
        Section section = sectionRepository.findById(id).orElseThrow(() -> RestException.notFound("SECTION"));
        return ApiResult.successResponse(sectionMapper.toSectionRespDto(section));
    }

    @Override
    public ApiResult<?> create(SectionDto section) {
        for (Attachment attachment : section.getVideos()) {
            attachmentRepository.findById(attachment.getId()).orElseThrow(() -> RestException.notFound("ATTACHMENT"));
        }
        Section save = sectionRepository.save(new Section(section.getName(), section.getVideos()));
        return ApiResult.successResponse(sectionMapper.toSectionDto(save));
    }

    @Override
    public ApiResult<?> edit(UUID id, SectionDto section) {
        Section finded = sectionRepository.findById(id).orElseThrow(() -> RestException.notFound("SECTION"));
        finded.setName(section.getName());
        finded.setVideos(section.getVideos());
        Section saved = sectionRepository.save(finded);
        return ApiResult.successResponse(sectionMapper.toSectionDto(saved));
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
