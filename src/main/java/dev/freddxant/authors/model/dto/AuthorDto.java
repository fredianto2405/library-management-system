package dev.freddxant.authors.model.dto;

import java.util.Date;

public class AuthorDto {
    private Long id;

    private String name;

    private String bio;

    private String birthdate;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name, String bio, String birthdate) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "AuthorsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
