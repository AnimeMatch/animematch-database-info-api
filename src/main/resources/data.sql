--insert into usuario(name, email, password, status) values ('AnimeMatch', 'anime@email.com', '$20$1050/TKTGxdREbaYhwf6e9P1PDAMMNqEnZg0095jn$k$fkkIrC', true)
insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero, bio) values
('Anime Match', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M', 'Fan de animes'),
('Lucas', 'lucas@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-11-25', true, 'M', 'Chamaaa'),
('Leozinn', 'leozin1234@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg',
'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-11-25', true, 'F', 'É o LC né vida');

insert into midia(id_api, nome, nota_media, imagem, likes, tipo) values
(113415, 'JUJUTSU KAISEN', 88.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113415-bbBWj4pEFseh.jpg', 540, 'ANIME'),
(158927, 'Yu-Gi-Oh', 67.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 32, 'ANIME'),
(158927, 'Dragon ball', 72.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 307, 'ANIME'),
(101922, 'Kimetsu no Yaiba', 72.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx101922-PEn1CTc93blC.jpg', 307, 'ANIME'),
(158927, 'Spy x Family', 80.0, 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/b158927-lfO85WVguYgc.png', 130, 'ANIME');

insert into lista(user_id_id, name, type) values
(1, 'Favoritos', 1) ,
(1, 'Dropado', 1),
(1, 'No plano', 1),
(1, 'Completo', 1),
(1, 'Em espera', 1),
(1, 'Em progresso', 1),
(1, 'Favoritos', 2) ,
(1, 'Dropado', 2),
(1, 'No plano', 2),
(1, 'Completo', 2),
(1, 'Em espera', 2),
(1, 'Em progresso', 2),
(2, 'Favoritos', 1),
(2, 'Dropados', 1),
(2, 'Assistidos', 1),
(2, 'Em andamento', 1),
(1, 'Melhores shonen', 1),
(1, 'Meus favoritos', 1),
(2, 'Um dia assistirei', 1),
(3, 'Lista única', 1),
(2, 'Animes antigos', 1);

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
--
--insert into usuario(name, email, password, profile_image, cover_image, criacao, status, genero) values ('Leonardo', 'anime@email.com', '$2a$10$vRFsyEX99Q97nrzsg9GSNuDnTYNJ7VCHYG8E9AQAtH050xarNiPKC', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', 'https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx113415-bbBWj4pEFseh.jpg', '2023-10-28', true, 'M');
--insert into anime(id_api,nota_media) values (1235,5.5);
--insert into anime(id_api,nota_media) values (158927,5.5);
--insert into anime(id_api,nota_media) values (345,5.5);
