create table utilisateur(
    iduser serial primary key,
    nom varchar,
    prenom varchar,
    email varchar,
    mdp varchar,
    role varchar
);

insert into utilisateur(nom, prenom,email, mdp) values ('Rakoto', 'Jean', 'jean@jean.jean', 'jean');
ALTER TABLE utilisateur
ADD COLUMN role VARCHAR;

UPDATE utilisateur
SET role = 'ROLE_ADMIN'
WHERE iduser = 1;
