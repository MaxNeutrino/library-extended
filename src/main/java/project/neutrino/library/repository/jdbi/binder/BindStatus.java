package project.neutrino.library.repository.jdbi.binder;

import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;
import project.neutrino.library.model.Status;

import java.lang.annotation.*;

@BindingAnnotation(BindStatus.StatusBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindStatus {

    public static class StatusBinderFactory implements BinderFactory {
        @Override
        public Binder build(Annotation annotation) {
            return (Binder<BindStatus, Status>) (q, bind, status) ->
                    q.bind("status", status.name());
        }
    }
}
