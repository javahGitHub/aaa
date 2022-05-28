package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.ResponseFileDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface AttachmentService {
    ApiResult<?> upload(MultipartHttpServletRequest request) throws IOException;

    void download(UUID id, HttpServletResponse response);

    ApiResult<List<ResponseFileDto>> getListFiles();

    ApiResult<Attachment> get(UUID id);
}
