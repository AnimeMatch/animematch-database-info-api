--insert into usuario(name, email, password, status) values ('AnimeMatch', 'anime@email.com', '$20$1050/TKTGxdREbaYhwf6e9P1PDAMMNqEnZg0095jn$k$fkkIrC', true)

insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Leonardo', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
insert into anime(id_api,nota_media) values (1,5.5);
insert into anime(id_api,nota_media) values (1,5.5);
insert into anime(id_api,nota_media) values (1,5.5);
insert into anime(id_api,nota_media) values (1,5.5);
insert into anime(id_api,nota_media) values (1,5.5);

insert into topico(titulo,id_anime) values ('Spy fmaily season 2','158927');
insert into comentario(texto,topico_id,data_comentario) values ('Anime top de maiss!',1,'2023-11-27');
insert into comentario(texto,topico_id,data_comentario) values ('Gostei muitoo!',1,'2023-11-27');
insert into comentario(texto,topico_id,data_comentario) values ('Comentario3',1,'2023-11-27');
insert into comentario(texto,topico_id,data_comentario) values ('Comentario4',1,'2023-11-27');
insert into comentario(texto,topico_id,data_comentario) values ('Comentario5',1,'2023-11-27');