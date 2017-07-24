package project.neutrino.library.model;

public enum MediaType {
    BOOK("author", "book series", "books"),
    MUSIC("artist", "album", "music"),
    MOVIE("director", "movie series", "movies");

    public final String creator;

    public final String collection;

    public final String pluralName;

    MediaType(String creator, String collection, String pluralName) {
        this.creator = creator;
        this.collection = collection;
        this.pluralName = pluralName;
    }
}
