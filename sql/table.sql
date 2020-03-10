CREATE TABLE user
(
    id            tinyint unsigned auto_increment not null primary key,
    login      varchar(255)  unique            not null,
    password      varchar(60)                    not null,
    email         varchar(255)                    not null,
    creation_stamp DATETIME DEFAULT CURRENT_TIMESTAMP  not null,
    last_changes TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    img mediumblob,
    role TINYINT NOT NULL default 1,
    about_me text
);

CREATE TABLE post
(
    id        tinyint unsigned auto_increment not null primary key,
    title     varchar(60)                     not null,
    author    tinyint unsigned                not null,
    post_text text                            not null,
    creation_stamp DATETIME DEFAULT CURRENT_TIMESTAMP  not null,
    last_changes TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (author) references user (id) ON DELETE CASCADE
);
CREATE TABLE comment
(
    id           tinyint unsigned auto_increment not null primary key,
    author       tinyint unsigned                not null,
    post_id         tinyint unsigned                not null,
    comment_text varchar(255)                    not null,
    foreign key (author) references user (id) ON DELETE CASCADE,
    foreign key (post_id) references post (id) ON DELETE CASCADE
);
CREATE TABLE category
(
    id   tinyint unsigned auto_increment not null primary key,
    name varchar(20)                     not null
);
CREATE TABLE post_category
(
    post_id     tinyint unsigned not null,
    category_id tinyint unsigned not null,
    foreign key (post_id) references post (id) ON DELETE CASCADE,
    foreign key (category_id) references category (id) ON DELETE CASCADE
);

CREATE TABLE user_subscription
(
    user_id       tinyint  unsigned not null,
    subscriber_id tinyint unsigned not null,
    foreign key (user_id) references user (id) ON DELETE CASCADE,
    foreign key (subscriber_id) references user (id) ON DELETE CASCADE
);

CREATE TABLE image
(
    post_id tinyint unsigned not null,
    image   MEDIUMBLOB,
    foreign key (post_id) references post (id) ON DELETE CASCADE
);

CREATE TABLE visit_log
  (
      id tinyint unsigned auto_increment not null primary key,
      session_id varchar(40) not null,
      login varchar(60) not null,
      enter_time varchar(25) not null ,
      out_time varchar(25)
  );

