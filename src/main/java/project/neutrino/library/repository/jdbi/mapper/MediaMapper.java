package project.neutrino.library.repository.jdbi.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import project.neutrino.library.model.Media;
import project.neutrino.library.model.MediaType;
import project.neutrino.library.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaMapper implements ResultSetMapper<Media> {

    @Override
    public Media map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Media(
                r.getInt("id"),
                r.getString("name"),
                r.getString("creator"),
                r.getString("collection"),
                r.getString("genre"),
                Status.valueOf(r.getString("status")),
                MediaType.valueOf(r.getString("type"))
        );
    }
}
