INSERT INTO public.users(id,login,email,password)VALUES (1,'KIRILL','krybak99@gmail.com','$2a$12$tJKv/bKL.XWSmdxhsLt9u.hg55uhnaZdHq0PNZRaKQHpSMr.0cLOS');
INSERT INTO public.users(id,login,email,password)VALUES (2,'DMITRY','dzabolotski99@gmail.com','$2a$12$tJKv/bKL.XWSmdxhsLt9u.hg55uhnaZdHq0PNZRaKQHpSMr.0cLOS');
INSERT INTO public.users(id,login,email,password)VALUES (3,'qwerty','kirillribak99@gmail.com','$2a$12$tJKv/bKL.XWSmdxhsLt9u.hg55uhnaZdHq0PNZRaKQHpSMr.0cLOS');
INSERT INTO public.users(id,login,email,password)VALUES (4,'dsfsd','pythonjava99@gmail.com','$2a$12$tJKv/bKL.XWSmdxhsLt9u.hg55uhnaZdHq0PNZRaKQHpSMr.0cLOS');
INSERT INTO public.users(id,login,email,password,role)VALUES (5,'admin','pythonjava99@gmail.com','$2a$12$4gk/NKGm/Ps5N/lY03oTwOLxjaflVBE0yW9H.jDNngyJKLP69Uty2',2);
INSERT INTO public.post(id,author,title,post_text)VALUES (1,1,'Новости от CD Project Red','Польский портал Bankier опубликовал ряд высказываний руководителя CD ');
INSERT INTO public.post(id,author,title,post_text)VALUES (2,1,'Человек-паук от Sony  получит новые костюмы','Японское подразделение PlayStation (возможно, чуть раньше ');
INSERT INTO public.post(id,author,title,post_text)VALUES (3,2,'В Cyberpunk 2077 будут не только «стволы»','Как ожидается, Cyberpunk 2077 предложит довольно много свободы  ');
INSERT INTO public.post(id,author,title,post_text)VALUES (4,2,'Всем советую глянуть',' Каждая галактическая планета обладает мощнейшим потоком, который вырабатывает энергию ');
INSERT INTO public.comment(id,author,post_id,comment_text)VALUES(1,3,1,'Скорей бы поиграть.');
INSERT INTO public.comment(id,author,post_id,comment_text)VALUES(2,3,3,'Жду не дождусь.');
INSERT INTO public.comment(id,author,post_id,comment_text)VALUES(3,1,2,'Плойка ацтой');
INSERT INTO public.comment(id,author,post_id,comment_text)VALUES(4,1,3,'Смотрел,ну такое...');
INSERT INTO public.category(id,name)VALUES(1,'спорт');
INSERT INTO public.category(id,name)VALUES(2,'игры');
INSERT INTO public.category(id,name)VALUES(3,'фильмы');
INSERT INTO public.category(id,name)VALUES(4,'аниме');
INSERT INTO public.category(id,name)VALUES(5,'бизнесс');
INSERT INTO public.category(id,name)VALUES(6,'фантастика');
INSERT INTO public.category(id,name)VALUES(7,'it');
INSERT INTO public.category(id,name)VALUES(8,'политика');
INSERT INTO public.category(id,name)VALUES(9,'машины');
INSERT INTO public.category(id,name)VALUES(10,'ЗОЖ');
INSERT INTO public.category(id,name)VALUES(11,'здоровое питание');
INSERT INTO public.category(id,name)VALUES(12,'кино');
INSERT INTO public.category(id,name)VALUES(13,'знаменитости');
INSERT INTO public.category(id,name)VALUES(14,'мультфильмы');
INSERT INTO public.post_category(post_id,category_id)VALUES(1,2);
INSERT INTO public.post_category(post_id,category_id)VALUES(2,2);
INSERT INTO public.post_category(post_id,category_id)VALUES(3,2);
INSERT INTO public.post_category(post_id,category_id)VALUES(4,1);
INSERT INTO public.post_category(post_id,category_id)VALUES(4,14);
INSERT INTO public.user_subscription(user_id,subscriber_id)VALUES(1,2);
INSERT INTO public.user_subscription(user_id,subscriber_id)VALUES(1,3);
INSERT INTO public.user_subscription(user_id,subscriber_id)VALUES(3,2);
INSERT INTO public.user_subscription(user_id,subscriber_id)VALUES(3,1);
INSERT INTO public.user_subscription(user_id,subscriber_id)VALUES(2,1);
INSERT INTO public.user_subscription(user_id,subscriber_id)VALUES(2,3);