CREATE TABLE track
(
    id INT auto_increment PRIMARY KEY,
    title TEXT,
    album TEXT,
    genre TEXT,
    length INT,
    release DATE,
    language TEXT,
    created_on DATE,
    modified_on  DATE,
    created_by DATE,
    modified_by DATE
);

CREATE TABLE artist
(
    id INT auto_increment PRIMARY KEY,
    name TEXT,
    biography TEXT,
    dob DATE,
    type TEXT,
    created_on DATE,
    modified_on  DATE,
    created_by DATE,
    modified_by DATE
);

CREATE TABLE alias
(
    id INT auto_increment PRIMARY KEY,
    name TEXT,
    artist_id INT,
    created_on DATE,
    modified_on  DATE,
    created_by DATE,
    modified_by DATE
);

CREATE TABLE collaborator
(
    id INT auto_increment PRIMARY KEY,
    track_id INT,
    artist_id INT,
    role TEXT,
    created_on DATE,
    modified_on  DATE,
    created_by DATE,
    modified_by DATE
);

CREATE TABLE state
(
    id INT auto_increment PRIMARY KEY,
    name TEXT,
    data TEXT,
    created_on DATE,
    modified_on  DATE,
    created_by DATE,
    modified_by DATE
);

CREATE TABLE artist_queue
(
    id INT auto_increment PRIMARY KEY,
    artist_id INT,
    next_artist INT
);