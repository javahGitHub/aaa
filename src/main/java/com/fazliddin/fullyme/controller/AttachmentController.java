package com.fazliddin.fullyme.controller;

import com.fazliddin.fullyme.constants.AppConstants;
import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.ResponseFileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping(AttachmentController.ATTACHMENT_CONTROLLER)
public interface AttachmentController {
    String ATTACHMENT_CONTROLLER = AppConstants.BASE_URL + "/attachment";

    @PostMapping("/upload")
    ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException;

    @GetMapping("/download/{id}")
    void download(@PathVariable(value = "id") UUID id, HttpServletResponse response);

    @GetMapping()
    ApiResult<List<ResponseFileDto>> getListFiles();

    @GetMapping("{id}")
    ApiResult<Attachment> get(@PathVariable UUID id);

}
