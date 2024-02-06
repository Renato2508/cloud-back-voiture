create table utilisateur(
    iduser serial primary key,
    nom varchar,
    prenom varchar,
    email varchar unique,
    mdp varchar,
    role varchar,
    notif_token text
);

create table pourcentage(
    idpourcentage serial primary key,
    pourcentage double
);