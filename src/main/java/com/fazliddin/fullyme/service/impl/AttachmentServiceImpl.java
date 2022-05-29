package com.fazliddin.fullyme.service.impl;

import com.fazliddin.fullyme.common.MessageService;
import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.entity.AttachmentContent;
import com.fazliddin.fullyme.exception.RestException;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.ResponseFileDto;
import com.fazliddin.fullyme.repository.AttachmentContentRepository;
import com.fazliddin.fullyme.repository.AttachmentRepository;
import com.fazliddin.fullyme.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;

    @Override
    public ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        assert file != null;
        Attachment attachment = Attachment.builder()
                .originalName(file.getOriginalFilename())
                .name(file.getName())
                .size(file.getSize())
                .contentType(file.getContentType()).build();
        Attachment save = attachmentRepository.save(attachment);

        AttachmentContent content = AttachmentContent.builder()
                .attachment(save)
                .bytes(file.getBytes()).build();
        contentRepository.save(content);

        ResponseFileDto responseFileDto = new ResponseFileDto(
                attachment.getName(),
                attachment.getOriginalName(),
                path(attachment),
                attachment.getContentType(),
                attachment.getSize()
        );
        return ApiResult.successResponse(responseFileDto);
    }

    @Override
    public void download(UUID id, HttpServletResponse response) {
        Optional<Attachment> file = attachmentRepository.findById(id);
        if (file.isPresent()) {
            Attachment attachment = file.get();
            Optional<AttachmentContent> attachmentContent = contentRepository.findByAttachmentId(id);
            if (attachmentContent.isPresent()) {
                AttachmentContent content = attachmentContent.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                try {
                    FileCopyUtils.copy(content.getBytes(), response.getOutputStream());
                } catch (Exception e) {
                    throw RestException.badRequest();
                }
            }
        }
    }

    @Override
    public ApiResult<List<ResponseFileDto>> getListFiles() {
        List<ResponseFileDto> files = getAllFiles().map(attachment -> new ResponseFileDto(
                attachment.getName(),
                attachment.getOriginalName(),
                path(attachment),
                attachment.getContentType(),
                attachment.getSize())).collect(Collectors.toList());

        return ApiResult.successResponse(files);
    }

    private Stream<Attachment> getAllFiles() {
        return attachmentRepository.findAll().stream();
    }

    @Override
    public ApiResult<Attachment> get(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(
                () -> RestException.notFound(MessageService.notFound("ATTACHMENT")));
        return ApiResult.successResponse(attachment);
    }

    private String path(Attachment attachment) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath().host("localhost").port(8080)
                .path("/api/v1/attachment/download/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();
    }
}
