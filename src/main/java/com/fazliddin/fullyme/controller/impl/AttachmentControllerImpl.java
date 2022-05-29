package com.fazliddin.fullyme.controller.impl;

import com.fazliddin.fullyme.controller.AttachmentController;
import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.ResponseFileDto;
import com.fazliddin.fullyme.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {

    private final AttachmentService attachmentService;

    @Override
    public ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.upload(request);
    }

    @Override
    public void download(UUID id, HttpServletResponse response) {
        attachmentService.download(id,response);
    }

    @Override
    public ApiResult<List<ResponseFileDto>> getListFiles() {
        return attachmentService.getListFiles();
    }

    @Override
    public ApiResult<Attachment> get(UUID id) {
        return attachmentService.get(id);
    }
}
