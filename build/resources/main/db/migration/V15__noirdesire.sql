-- Table "Chansons"
CREATE TABLE Chansons (
                          ID_chanson INT PRIMARY KEY,
                          titre VARCHAR(100),
                          artiste VARCHAR(100),
                          album VARCHAR(100),
                          duree TIME,
                          date_ajout DATE
);

-- Table "Playlists"
CREATE TABLE Playlists (
                           ID_playlist INT PRIMARY KEY,
                           ID_utilisateur INT,
                           nom VARCHAR(100),
                           date_creation DATE,
                           FOREIGN KEY (ID_utilisateur) REFERENCES users (id)
);

-- Table "Playlist_Chansons"
CREATE TABLE Playlist_Chansons (
                                   ID_playlist INT,
                                   ID_chanson INT,
                                   FOREIGN KEY (ID_playlist) REFERENCES Playlists (ID_playlist),
                                   FOREIGN KEY (ID_chanson) REFERENCES Chansons (ID_chanson)
);

-- Table "Albums"
CREATE TABLE Albums (
                        ID_album INT PRIMARY KEY,
                        titre VARCHAR(100),
                        artiste VARCHAR(100),
                        date_sortie DATE
);

-- Table "Album_Chansons"
CREATE TABLE Album_Chansons (
                                ID_album INT,
                                ID_chanson INT,
                                FOREIGN KEY (ID_album) REFERENCES Albums (ID_album),
                                FOREIGN KEY (ID_chanson) REFERENCES Chansons (ID_chanson)
);

-- Table "Followers"
CREATE TABLE Followers (
                           ID_follower INT PRIMARY KEY,
                           ID_utilisateur_suivant INT,
                           ID_utilisateur_suiveur INT,
                           FOREIGN KEY (ID_utilisateur_suivant) REFERENCES users (id),
                           FOREIGN KEY (ID_utilisateur_suiveur) REFERENCES users (id)
);

-- Table "Likes"
CREATE TABLE Likes (
                       ID_like INT PRIMARY KEY,
                       ID_utilisateur INT,
                       ID_chanson INT,
                       FOREIGN KEY (ID_utilisateur) REFERENCES users (id),
                       FOREIGN KEY (ID_chanson) REFERENCES Chansons (ID_chanson)
);

-- Table "Commentaires"
CREATE TABLE Commentaires (
                              ID_commentaire INT PRIMARY KEY,
                              ID_utilisateur INT,
                              ID_chanson INT,
                              contenu TEXT,
                              date_creation DATE,
                              FOREIGN KEY (ID_utilisateur) REFERENCES users (id),
                              FOREIGN KEY (ID_chanson) REFERENCES Chansons (ID_chanson)
);