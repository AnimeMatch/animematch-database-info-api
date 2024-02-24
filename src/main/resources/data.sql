--insert into usuario(name, email, password, status) values ('AnimeMatch', 'midia@email.com', '$20$1050/TKTGxdREbaYhwf6e9P1PDAMMNqEnZg0095jn$k$fkkIrC', true)
insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values
('Anime Match', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M'),
('Lucas', 'lucas@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-11-25', true, 'M'),
('Leozinn', 'leozin1234@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-11-25', true, 'F');

insert into midia(id_api, nome, nota_media, imagem, likes, tipo) values
(113415, 'JUJUTSU KAISEN', 88.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg', 540, 'ANIME'),
(158927, 'Yu-Gi-Oh', 67.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 32, 'ANIME'),
(158927, 'Dragon ball', 72.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 307, 'ANIME'),
(101922, 'Kimetsu no Yaiba', 72.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx101922-PEn1CTc93blC.jpg', 307, 'ANIME'),
(158927, 'Spy x Family', 80.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 130, 'ANIME');

insert into lista(user_id_id, name) values
(1, 'Favoritos') ,
(1, 'Dropados'),
(1, 'Assistidos'),
(1, 'Em andamento'),
(2, 'Favoritos'),
(2, 'Dropados'),
(2, 'Assistidos'),
(2, 'Em andamento'),
(1, 'Melhores shonen'),
(1, 'Meus favoritos'),
(2, 'Um dia assistirei'),
(3, 'Lista única'),
(2, 'Animes antigos');

insert into midia_lista(midia_id_id, lista_id_id) values
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(2, 3),
(2, 4),
(3, 4),
(4, 4),
(1, 5),
(1, 6),
(2, 6),
(2, 7),
(2, 8),
(3, 8),
(4, 8),
(4, 12);


insert into topico(id,titulo,id_midia,usuario_id) values (9999,'Default','9999',1);
insert into topico(titulo,id_midia,usuario_id) values ('Spy fmaily season 2','158927',2);
insert into comentario(texto,topico_id,data_comentario,email_usuario,id_midia_api,qtd_likes,qtd_deslikes) values ('Anime top de maiss!',1,'2023-11-27','lucas@email.com',158927,10,5);
insert into comentario(texto,topico_id,data_comentario,email_usuario,id_midia_api,qtd_likes,qtd_deslikes) values ('Gostei muitoo!',1,'2023-11-27','lucas@email.com',158927,1,2);
insert into comentario(texto,topico_id,data_comentario,email_usuario,id_midia_api,qtd_likes,qtd_deslikes) values ('Legall',1,'2023-11-27','lucas@email.com',158927,9,12);
insert into comentario(texto,topico_id,data_comentario,email_usuario,id_midia_api,qtd_likes,qtd_deslikes) values ('Top',1,'2023-11-27','lucas@email.com',158927,14,5);
insert into comentario(texto,topico_id,data_comentario,email_usuario,id_midia_api,qtd_likes,qtd_deslikes) values ('Muito ruim',1,'2023-11-27','lucas@email.com',158927,1,20);

--
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Leonardo', 'midia@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/midia/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/midia/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into midia(id_api,nota_media) values (1235,5.5);
--insert into midia(id_api,nota_media) values (158927,5.5);
--insert into midia(id_api,nota_media) values (345,5.5);
