insert into pet(id_pet, nome) values(1, 'Loro');
insert into pet(id_pet, nome) values(2, 'Lili');
insert into pet(id_pet, nome) values(3, 'Chica');
insert into pet(id_pet, nome) values(4, 'Dety');
insert into pet(id_pet, nome) values(5, 'Mel');
insert into pet(id_pet, nome) values(6, 'Lucy');
insert into pet(id_pet, nome) values(7, 'Rick');

insert into pessoa(id_pessoa, nome) values(1, 'Kércia Oliveira Alves');
insert into pessoa(id_pessoa, nome) values(2, 'César Antônio de Magalhães');
insert into pessoa(id_pessoa, nome) values(3, 'Luiz Henrique de Magalhães');
insert into pessoa(id_pessoa, nome) values(4, 'Leandro Luzia de Magalhães');
insert into pessoa(id_pessoa, nome) values(5, 'Daniel Júlio de Magalhães');

insert into projeto(id_projeto, nome, id_pet) values(1 ,'Cuida da Lili', 2);
insert into projeto(id_projeto, nome, id_pet) values(2 ,'Cuida da Mel', 5);
insert into projeto(id_projeto, nome, id_pet) values(3 ,'Cuida do Loro', 1);
insert into projeto(id_projeto, nome, id_pet) values(4 ,'Cuida da Dety', 4);
insert into projeto(id_projeto, nome, id_pet) values(5 ,'Cuida do Rick', 7);
insert into projeto(id_projeto, nome, id_pet) values(6 ,'Cuida da Chica', 3);
insert into projeto(id_projeto, nome, id_pet) values(7 ,'Cuida da Lucy', 6);

insert into pessoa_projeto(id_pessoa, id_projeto) values(1, 1);
insert into pessoa_projeto(id_pessoa, id_projeto) values(1, 2);
insert into pessoa_projeto(id_pessoa, id_projeto) values(4, 3);
insert into pessoa_projeto(id_pessoa, id_projeto) values(4, 4);
insert into pessoa_projeto(id_pessoa, id_projeto) values(4, 5);
insert into pessoa_projeto(id_pessoa, id_projeto) values(5, 6);
insert into pessoa_projeto(id_pessoa, id_projeto) values(5, 7);