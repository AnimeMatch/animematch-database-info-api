--insert into usuario(name, email, password, status) values ('AnimeMatch', 'anime@email.com', '$20$1050/TKTGxdREbaYhwf6e9P1PDAMMNqEnZg0095jn$k$fkkIrC', true)
insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values
('Anime Match', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M'),
('Anime Match', 'lucas@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-11-25', true, 'M'),
('Anime Match', 'userNoAnime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-11-25', true, 'F');

insert into anime(id_api, nome, nota_media, imagem, likes) values
(113415, 'JUJUTSU KAISEN', 88.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg', 540),
(158927, 'Yu-Gi-Oh', 67.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 32),
(158927, 'Dragon ball', 72.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 307),
(158927, 'Spy x Family', 80.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 130);

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

insert into anime_lista(anime_id_id, lista_id_id) values
(1, 1),
(1, 2),
(2, 2),
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


--
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Leonardo', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into anime(id_api,nota_media) values (1,5.5);
--insert into anime(id_api,nota_media) values (1,5.5);
--insert into anime(id_api,nota_media) values (1,5.5);
--insert into anime(id_api,nota_media) values (1,5.5);
--insert into anime(id_api,nota_media) values (1,5.5);
--
--insert into topico(titulo,id_anime) values ('Spy fmaily season 2','158927');
--insert into comentario(texto,topico_id,data_comentario) values ('Anime top de maiss!',1,'2023-11-27');
--insert into comentario(texto,topico_id,data_comentario) values ('Gostei muitoo!',1,'2023-11-27');
--insert into comentario(texto,topico_id,data_comentario) values ('Comentario3',1,'2023-11-27');
--insert into comentario(texto,topico_id,data_comentario) values ('Comentario4',1,'2023-11-27');
--insert into comentario(texto,topico_id,data_comentario) values ('Comentario5',1,'2023-11-27');