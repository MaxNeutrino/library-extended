DROP TABLE IF EXISTS media;

CREATE TABLE IF NOT EXISTS media
(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  creator VARCHAR(255),
  collection VARCHAR(255),
  genre VARCHAR(255),
  status VARCHAR(255),
  type VARCHAR(255)
);

ALTER TABLE media ALTER COLUMN status SET DEFAULT 'WANT';

INSERT INTO media (id, name, type) VALUES
  (1, 'one', 'BOOK');

INSERT INTO media (id, name, creator, collection, genre, status, type) VALUES
  (2, 'two', 'creator_two', 'collection_two', 'genre_two', 'WANT', 'BOOK'),
  (3, 'three', 'creator_two', 'collection_three', 'genre_two', 'IN_PROGRESS', 'BOOK'),
  (4, 'threeName', 'creator_four', 'collection_three', 'genre_two', 'FINISHED', 'BOOK'),
  (5, 'threeName', 'creator_four', 'collection_three', 'genre_two', 'FINISHED', 'MOVIE'),
  (6, 'threeName', 'creator_four', 'collection_three', 'genre_two', 'FINISHED', 'MOVIE');