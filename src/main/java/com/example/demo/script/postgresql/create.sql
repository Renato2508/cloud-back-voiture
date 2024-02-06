create database voiture;
\c voiture
create table utilisateur(
    iduser serial primary key,
    nom varchar,
    prenom varchar,
    email varchar unique,
    mdp varchar,
    role varchar,
    notif_token text
);

CREATE TABLE annonce(
    idAnnonce serial primary key,
    etat int,
    idvoiture VARCHAR(70),
    iduser int references utilisateur(iduser)
);

CREATE TABLE favoris(
    idfavoris serial primary key,
    iduser int references utilisateur(iduser),
    idAnnonce int references annonce(idannonce),
    dateabo date
);

CREATE TABLE pourcentage(
    idpourcentage serial primary key,
    pourcentage decimal
);