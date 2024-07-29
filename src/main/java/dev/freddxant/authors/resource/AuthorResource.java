package dev.freddxant.authors.resource;

import dev.freddxant.authors.model.request.AddAuthorRequest;
import dev.freddxant.authors.model.request.UpdateAuthorRequest;
import dev.freddxant.authors.service.AuthorService;
import dev.freddxant.utilities.model.dto.Message;
import dev.freddxant.utilities.model.dto.MessageData;
import dev.freddxant.utilities.model.dto.MessageDataPagination;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    @Inject
    AuthorService authorService;

    @GET
    @Path("/get-all")
    public MessageDataPagination getAll(@QueryParam("search") String search,
                                        @QueryParam("page") @DefaultValue("1") int page,
                                        @QueryParam("size") @DefaultValue("10") int size) {
        return authorService.getAll(search, page, size);
    }

    @GET
    @Path("/get-by-id")
    public MessageData getById(@QueryParam("id") Long id) {
        return authorService.getById(id);
    }

    @POST
    @Path("/add")
    public Message add(AddAuthorRequest request) {
        return authorService.add(request);
    }

    @PUT
    @Path("/update/{id}")
    public Message update(@PathParam("id") Long id, UpdateAuthorRequest request) {
        return authorService.update(id, request);
    }

    @DELETE
    @Path("/delete/{id}")
    public Message delete(@PathParam("id") Long id) {
        return authorService.delete(id);
    }
}
