package project.neutrino.library.service;

import org.junit.Assert;
import org.junit.Test;
import project.neutrino.library.ChosenMediaType;
import project.neutrino.library.MediaTestData;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.model.Status;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MediaServiceTest extends AbstractServiceTest {

    @Test
    public void testFind() {
        Media first = mediaService.find(MediaTestData.ID_ONE);
        Assert.assertEquals(first, MediaTestData.mediaOne);

        Media second = mediaService.find(MediaTestData.NAME_TWO).get(0);
        Assert.assertEquals(MediaTestData.mediaTwo, second);
    }

    @Test
    public void testFindByNameContaining() {
        String chars = MediaTestData.NAME_THREE.substring(0, 3);
        List<Media> media = mediaService.findByNameContaining(chars);
        Assert.assertEquals(Arrays.asList(MediaTestData.mediaThree, MediaTestData.mediaFour), media);
    }

    @Test
    public void testFindAll() {
        List<Media> books = mediaService.findAll();
        List<Media> booksTestData = Arrays.asList(
                MediaTestData.mediaOne,
                MediaTestData.mediaTwo,
                MediaTestData.mediaThree,
                MediaTestData.mediaFour
        );

        booksTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(booksTestData, books);

        ChosenMediaType.TYPE = MediaType.MOVIE;

        List<Media> movies = mediaService.findAll();
        List<Media> movieTestData = Arrays.asList(
                MediaTestData.mediaFive,
                MediaTestData.mediaSix
        );

        movieTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(movieTestData, movies);
    }

    @Test
    public void findByGenre() {
        List<Media> books = mediaService.findByGenre(MediaTestData.GENRE);
        List<Media> booksTestData = Arrays.asList(
                MediaTestData.mediaTwo,
                MediaTestData.mediaThree,
                MediaTestData.mediaFour
        );

        booksTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(booksTestData, books);

        ChosenMediaType.TYPE = MediaType.MOVIE;

        List<Media> movies = mediaService.findByGenre(MediaTestData.GENRE);
        List<Media> movieTestData = Arrays.asList(
                MediaTestData.mediaFive,
                MediaTestData.mediaSix
        );

        movieTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(movieTestData, movies);
    }

    @Test
    public void findByCollection() {
        List<Media> books = mediaService.findByCollection(MediaTestData.COLLECTION_TWO);
        Assert.assertEquals(books, Collections.singletonList(MediaTestData.mediaTwo));
    }

    @Test
    public void findByCreator() {
        List<Media> media = mediaService.findByCreator(MediaTestData.CREATOR);
        List<Media> mediaTestData = Arrays.asList(
                MediaTestData.mediaTwo,
                MediaTestData.mediaThree
        );

        mediaTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(mediaTestData, media);
    }

    @Test
    public void findByStatus() {
        List<Media> media = mediaService.findByStatus(Status.IN_PROGRESS);
        Assert.assertEquals(Collections.singletonList(MediaTestData.mediaThree), media);
    }

    @Test
    public void testCreate() {
        Media toSave = new Media("newBook", null,null,null, Status.FINISHED, MediaType.BOOK);

        int id = mediaService.save(toSave);
        toSave.setId(id);

        List<Media> media = mediaService.findAll();
        List<Media> booksTestData = Arrays.asList(
                MediaTestData.mediaOne,
                MediaTestData.mediaTwo,
                MediaTestData.mediaThree,
                MediaTestData.mediaFour,
                toSave
        );

        booksTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(booksTestData, media);
    }

    @Test
    public void testUpdate() {
        Media toUpdate = MediaTestData.mediaOne;
        toUpdate.setName("updated");
        mediaService.save(toUpdate);

        Media updated = mediaService.find(MediaTestData.ID_ONE);
        Assert.assertEquals(toUpdate, updated);
    }

    @Test
    public void testDelete() {
        mediaService.delete(MediaTestData.ID_FOUR);

        List<Media> media = mediaService.findAll();
        List<Media> booksTestData = Arrays.asList(
                MediaTestData.mediaOne,
                MediaTestData.mediaTwo,
                MediaTestData.mediaThree
        );

        booksTestData.sort(Comparator.comparing(Media::getName));
        Assert.assertEquals(booksTestData, media);
    }
}
