package dev.freddxant.authors.service.impl;

import dev.freddxant.authors.entity.Author;
import dev.freddxant.authors.model.dto.AuthorDto;
import dev.freddxant.authors.model.request.AddAuthorRequest;
import dev.freddxant.authors.model.request.UpdateAuthorRequest;
import dev.freddxant.authors.repository.AuthorRepository;
import dev.freddxant.authors.service.AuthorService;
import dev.freddxant.utilities.DateConverter;
import dev.freddxant.utilities.model.dto.Message;
import dev.freddxant.utilities.model.dto.MessageData;
import dev.freddxant.utilities.model.dto.MessageDataPagination;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {
    @Inject
    AuthorRepository authorRepository;

    @Override
    public MessageDataPagination getAll(String search, int page, int size) {
        List<AuthorDto> authorsList = authorRepository.findAllByCriteria(search, (page - 1), size)
                .stream()
                .map(this::toAuthorDto)
                .collect(Collectors.toList());
        long totalElements = authorRepository.countAllByCriteria(search);
        int totalPages = (int) Math.ceil((double) totalElements / size);
        String message = (totalElements == 0) ? "No data found" : "Data found";
        return new MessageDataPagination(message, authorsList, totalElements, totalPages, page);
    }

    @Override
    public MessageData getById(Long id) {
        Author author = authorRepository.findById(id);
        String message = (author == null) ? "No data found" : "Data found";

        if (author != null) {
            AuthorDto authorDto = toAuthorDto(author);
            return new MessageData(message, authorDto);
        } else {
            return new MessageData(message, null);
        }
    }

    @Override
    @Transactional
    public Message add(AddAuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBio(request.getBio());
        author.setBirthdate(DateConverter.dateStringToDate(request.getBirthdate()));
        authorRepository.persist(author);
        return new Message("Author added successfully");
    }

    @Override
    @Transactional
    public Message update(Long id, UpdateAuthorRequest request) {
        Author author = authorRepository.findById(id);

        if (author != null) {
            author.setName(request.getName());
            author.setBio(request.getBio());
            author.setBirthdate(DateConverter.dateStringToDate(request.getBirthdate()));
            authorRepository.persist(author);
            return new Message("Author updated successfully");
        }

        return new Message("Author not found");
    }

    @Override
    @Transactional
    public Message delete(Long id) {
        Author author = authorRepository.findById(id);

        if (author != null) {
            authorRepository.delete(author);
            return new Message("Author deleted successfully");
        }

        return new Message("Author not found");
    }

    private AuthorDto toAuthorDto(Author author) {
        return new AuthorDto(author.getId(),
                author.getName(),
                author.getBio(),
                DateConverter.dateToDateString(author.getBirthdate()));
    }
}
