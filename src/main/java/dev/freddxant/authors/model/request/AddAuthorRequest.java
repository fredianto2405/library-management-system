package dev.freddxant.authors.model.request;

public class AddAuthorRequest {
    private String name;

    private String bio;

    private String birthdate;

    public AddAuthorRequest() {
    }

    public AddAuthorRequest(String name, String bio, String birthdate) {
        this.name = name;
        this.bio = bio;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "AddAuthorRequest{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
