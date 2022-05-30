package com.fazliddin.fullyme.service.impl;

import com.fazliddin.fullyme.entity.Author;
import com.fazliddin.fullyme.exception.RestException;
import com.fazliddin.fullyme.mapper.AuthorMapper;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.CustomPage;
import com.fazliddin.fullyme.payload.req.AuthorDto;
import com.fazliddin.fullyme.payload.resp.AuthorRespDto;
import com.fazliddin.fullyme.repository.AuthorRepository;
import com.fazliddin.fullyme.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public ApiResult<CustomPage<AuthorRespDto>> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));
        Page<Author> productPage = authorRepository.findAll(pageRequest);
        return ApiResult.successResponse(makeCustomPage(productPage));
    }

    @Override
    public ApiResult<AuthorRespDto> get(UUID id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> RestException.notFound("AUTHOR"));
        return ApiResult.successResponse(authorMapper.toAuthorRespDto(author));
    }
    @Override
    public ApiResult<?> create(AuthorDto author) {
        Author savedAuthor = authorRepository.save(new Author(author.getFirstName(), author.getLastName(), author.getAbout(), author.getWebsiteLink(), author.getLinkedInLink(), author.getGithubLink()));
        return ApiResult.successResponse(authorMapper.toAuthorRespDto(savedAuthor));
    }
    @Override
    public ApiResult<?> edit(UUID id, AuthorDto author) {
        Author finded = authorRepository.findById(id).orElseThrow(() -> RestException.notFound("AUTHOR"));
        finded.setFirstName(author.getFirstName());
        finded.setLastName(author.getLastName());
        finded.setAbout(author.getAbout());
        finded.setWebsiteLink(author.getWebsiteLink());
        finded.setGithubLink(author.getGithubLink());
        finded.setLinkedInLink(author.getLinkedInLink());
        finded = authorRepository.save(finded);
        return ApiResult.successResponse(authorMapper.toAuthorDto(finded));
    }
    @Override
    public CustomPage<AuthorRespDto> makeCustomPage(Page<Author> authors) {
        return new CustomPage<>(
                authors.getContent().stream().map(authorMapper::toAuthorRespDto).collect(Collectors.toList()),
                authors.getNumberOfElements(),
                authors.getNumber(),
                authors.getTotalElements(),
                authors.getTotalPages(),
                authors.getSize()
        );
    }
}
