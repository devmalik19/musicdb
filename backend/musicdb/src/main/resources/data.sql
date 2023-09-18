INSERT INTO track(title, album, genre, length, release, language) VALUES('Track 1', 'Album 1', 'Genre 1', 300, '2023-01-01', 'English');
INSERT INTO track(title, album, genre, length, release, language) VALUES('Track 2', 'Album 1', 'Genre 2', 400, '2023-01-01', 'English');
INSERT INTO track(title, album, genre, length, release, language) VALUES('Track 3', 'Album 2', 'Genre 3', 500, '2023-02-01', 'English');
INSERT INTO track(title, album, genre, length, release, language) VALUES('Track 4', 'Album 2', 'Genre 4', 200, '2023-02-01', 'English');
INSERT INTO track(title, album, genre, length, release, language) VALUES('Track 5', 'Album 3', 'Genre 5', 100, '2023-01-03', 'English');
INSERT INTO track(title, album, genre, length, release, language) VALUES('Track 6', 'Album 4', 'Genre 5', 100, '2023-01-03', 'English');

INSERT INTO collaborator(track_id, artist_id, role) VALUES(1, 1, 'SINGER');
INSERT INTO collaborator(track_id, artist_id, role) VALUES(2, 1, 'SINGER');
INSERT INTO collaborator(track_id, artist_id, role) VALUES(3, 2, 'SINGER');
INSERT INTO collaborator(track_id, artist_id, role) VALUES(4, 2, 'SINGER');
INSERT INTO collaborator(track_id, artist_id, role) VALUES(5, 3, 'SINGER');
INSERT INTO collaborator(track_id, artist_id, role) VALUES(6, 1, 'SINGER');
INSERT INTO collaborator(track_id, artist_id, role) VALUES(6, 2, 'FEATURING');

INSERT INTO artist(name, biography, dob) VALUES('Pink!', 'Pink! is a USA pop singer', '1989-01-01');
INSERT INTO artist(name, biography, dob) VALUES('Katty Perry', 'Katty Perry is a USA pop singer', '1991-01-01');
INSERT INTO artist(name, biography, dob) VALUES('Damien', 'Damien is an member of USA boy band Back Street Boys', '1991-01-01');

