package project.neutrino.library;

import project.neutrino.library.model.Media;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.model.Status;

public class MediaTestData {

    public static int ID_ONE = 1;
    public static int ID_TWO = 2;
    public static int ID_THREE = 3;
    public static int ID_FOUR = 4;
    public static int ID_FIVE = 5;
    public static int ID_SIX = 6;

    public static String NAME_ONE = "one";
    public static String NAME_TWO = "two";
    public static String NAME_THREE = "three";
    public static String NAME_FOUR = "threeName";

    public static String GENRE = "genre_two";

    public static String COLLECTION_TWO = "collection_two";
    public static String COLLECTION_THREE = "collection_three";

    public static String CREATOR = "creator_two";

    public static Media mediaOne = new Media(ID_ONE, NAME_ONE, Status.WANT, MediaType.BOOK);

    public static Media mediaTwo = new Media(ID_TWO, NAME_TWO, CREATOR, COLLECTION_TWO,
            GENRE, Status.WANT, MediaType.BOOK);

    public static Media mediaThree = new Media(ID_THREE, NAME_THREE, CREATOR, COLLECTION_THREE,
            GENRE, Status.IN_PROGRESS, MediaType.BOOK);

    public static Media mediaFour = new Media(ID_FOUR, NAME_FOUR, "creator_four", COLLECTION_THREE,
            GENRE, Status.FINISHED, MediaType.BOOK);

    public static Media mediaFive = new Media(ID_FIVE, NAME_FOUR, "creator_four", COLLECTION_THREE,
            GENRE, Status.FINISHED, MediaType.MOVIE);

    public static Media mediaSix = new Media(ID_SIX, NAME_FOUR, "creator_four", COLLECTION_THREE,
            GENRE, Status.FINISHED, MediaType.MOVIE);
}
