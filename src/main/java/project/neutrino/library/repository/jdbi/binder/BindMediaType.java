package project.neutrino.library.repository.jdbi.binder;

import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;
import project.neutrino.library.model.MediaType;

import java.lang.annotation.*;

@BindingAnnotation(BindMediaType.MediaTypeBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindMediaType {

    public static class MediaTypeBinderFactory implements BinderFactory {
        @Override
        public Binder build(Annotation annotation) {
            return (Binder<BindMediaType, MediaType>) (q, bind, mediaType) ->
                    q.bind("type", mediaType.name());
        }
    }
}
