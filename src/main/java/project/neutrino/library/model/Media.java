package project.neutrino.library.model;

import java.util.Objects;
import java.util.Optional;

public class Media {

    private Integer id;

    private String name;

    private String creator;

    private String collection;

    private String genre;

    private Status status;

    private MediaType type;

    public Media(String name) {
        this.name = name;
    }

    public Media(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Media(int id, String name,Status status, MediaType type) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public Media(String name, String creator, String collection, String genre, Status status, MediaType type) {
        this.name = name;
        this.creator = creator;
        this.collection = collection;
        this.genre = genre;
        this.status = status;
        this.type = type;
    }

    public Media(Integer id, String name, String creator, String collection,
                 String genre, Status status, MediaType type) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.collection = collection;
        this.genre = genre;
        this.status = status;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getCreator() {
        return Optional.ofNullable(creator);
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Optional<String> getCollection() {
        return Optional.ofNullable(collection);
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Optional<String> getGenre() {
        return Optional.ofNullable(genre);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id) &&
                Objects.equals(name, media.name) &&
                Objects.equals(creator, media.creator) &&
                Objects.equals(collection, media.collection) &&
                Objects.equals(genre, media.genre) &&
                status == media.status &&
                type == media.type;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", collection='" + collection + '\'' +
                ", genre='" + genre + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

