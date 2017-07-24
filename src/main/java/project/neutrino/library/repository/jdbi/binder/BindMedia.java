package project.neutrino.library.repository.jdbi.binder;

import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;
import project.neutrino.library.model.Media;

import java.lang.annotation.*;

@BindingAnnotation(BindMedia.MediaBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindMedia {

    public static class MediaBinderFactory implements BinderFactory {
        @Override
        public Binder build(Annotation annotation) {
            return (Binder<BindMedia, Media>) (q, bind, media) -> {
                q.bind("id", media.getId());
                q.bind("name", media.getName());
                q.bind("creator", media.getCreator().orElse(null));
                q.bind("collection", media.getCollection().orElse(null));
                q.bind("genre", media.getGenre().orElse(null));
                q.bind("status", media.getStatus().name());
                q.bind("type", media.getType().name());
            };
        }
    }
}
