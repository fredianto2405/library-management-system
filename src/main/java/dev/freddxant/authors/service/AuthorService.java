package dev.freddxant.authors.service;

import dev.freddxant.authors.model.request.AddAuthorRequest;
import dev.freddxant.authors.model.request.UpdateAuthorRequest;
import dev.freddxant.utilities.model.dto.Message;
import dev.freddxant.utilities.model.dto.MessageData;
import dev.freddxant.utilities.model.dto.MessageDataPagination;

public interface AuthorService {
    MessageDataPagination getAll(String search, int page, int size);

    MessageData getById(Long id);

    Message add(AddAuthorRequest request);

    Message update(Long id, UpdateAuthorRequest request);

    Message delete(Long id);
}
