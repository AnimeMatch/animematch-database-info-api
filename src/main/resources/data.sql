--insert into usuario(name, email, password, status) values ('AnimeMatch', 'anime@email.com', '$20$1050/TKTGxdREbaYhwf6e9P1PDAMMNqEnZg0095jn$k$fkkIrC', true)

insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Leozinn', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('O andres', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Limão', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Coda fofo', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Usuario Anonimo123', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');

insert into anime(id_api,nota_media) values (158927,5.5);
insert into anime(id_api,nota_media) values (123,5.5);
insert into anime(id_api,nota_media) values (198,5.5);
insert into anime(id_api,nota_media) values (909,5.5);

insert into topico(titulo,id_anime,usuario_id) values ('Spy fmaily season 2','158927',1);
insert into comentario(texto,topico_id,data_comentario,id_usuario,id_anime_api) values ('Anime top de maiss!',1,'2023-11-27',1,158927);
insert into comentario(texto,topico_id,data_comentario,id_usuario,id_anime_api) values ('Anime top de maiss!',1,'2023-11-27',1,909);
insert into comentario(texto,topico_id,data_comentario,id_usuario,id_anime_api) values ('Gostei muitoo!',1,'2023-11-27',1,909);
insert into comentario(texto,topico_id,data_comentario,id_usuario,id_anime_api) values ('Não curto muito',1,'2023-11-27',1,909);
--insert into comentario(texto,topico_id,data_comentario,id_usuario) values ('Prefiro naruto!!!',1,'2023-11-27',1);
--insert into comentario(texto,topico_id,data_comentario,id_usuario) values ('Top de maissss',1,'2023-11-27',1);