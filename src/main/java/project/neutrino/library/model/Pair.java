package project.neutrino.library.model;

import java.util.Objects;

public class Pair {

    private String key;

    private String message;

    public Pair(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(key, pair.key) &&
                Objects.equals(message, pair.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, message);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
