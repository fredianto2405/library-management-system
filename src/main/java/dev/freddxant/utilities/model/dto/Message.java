package dev.freddxant.utilities.model.dto;

public class Message {
    private final int status = 200;

    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
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

    @Override
    public String toString() {
        return "Message{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
