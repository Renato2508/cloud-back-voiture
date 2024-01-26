create table utilisateur(
    iduser serial primary key,
    nom varchar,
    prenom varchar,
    email varchar,
    mdp varchar,
    role varchar
);

ALTER TABLE utilisateur
ADD COLUMN role VARCHAR;