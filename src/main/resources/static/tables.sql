create table marque (
    idmarque serial primary key, 
    nomMarque varchar
);
create table vehicule (
    idvehicule serial primary key, 
    matricule varchar,
    idmarque integer references marque(idmarque)
);
create table kilometrage(
    idkilometrage serial primary key,
    idvehicule integer references vehicule(idvehicule),
    date date, 
    debutkm numeric,
    finkm numeric
);
create table utilisateur(
    idutilisateur serial primary key, 
    login varchar, 
    motdepasse varchar, 
    role varchar
);