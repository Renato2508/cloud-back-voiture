create database voiture;
\c voiture
CREATE TABLE annonce(
    idAnnonce serial primary key,
    etat int,
    idvoiture VARCHAR(70),
    iduser int
);

CREATE TABLE favoris(
    idfavoris serial primary key,
    iduser int,
    idAnnonce int,
    dateabo date
);

create table utilisateur(
    iduser serial primary key,
    nom varchar,
    prenom varchar,
    email varchar unique,
    mdp varchar,
    role varchar,
    notif_token text
);

CREATE TABLE pourcentage(
    idpourcentage serial primary key,
    pourcentage decimal
);