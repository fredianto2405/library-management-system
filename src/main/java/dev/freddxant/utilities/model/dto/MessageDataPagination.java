package dev.freddxant.utilities.model.dto;

public class MessageDataPagination {
    private final int status = 200;

    private String message;

    private Object data;

    private Object totalElements;

    private Object totalPages;

    private Object number;

    public MessageDataPagination() {
    }

    public MessageDataPagination(String message, Object data, Object totalElements, Object totalPages, Object number) {
        this.message = message;
        this.data = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Object totalElements) {
        this.totalElements = totalElements;
    }

    public Object getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Object totalPages) {
        this.totalPages = totalPages;
    }

    public Object getNumber() {
        return number;
    }

    public void setNumber(Object number) {
        this.number = number;
    }
}
