create database voiture;
\c voiture
CREATE TABLE annonce(
    idAnnonce serial primary key,
    datepublication date,
    etat int,
    commission int,
    idvoiture VARCHAR(70),
    sommepayee numeric,
    datepayement date,
    iduser VARCHAR(70)
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
    email varchar,
    mdp varchar,
    role varchar
);