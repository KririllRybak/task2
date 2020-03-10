create table users(
    id BIGSERIAL PRIMARY KEY ,
    login VARCHAR(70) unique NOT NULL,
	password varchar(60) not null,
	email varchar(255) not null,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	img  BYTEA ,
    role integer NOT NULL default 1,
    about_me text
);
CREATE TABLE post
(
       id BIGSERIAL PRIMARY KEY ,
    title     varchar(60)                     not null,
    author    integer                not null,
    post_text text                            not null,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    foreign key (author) references users (id) ON DELETE CASCADE
);
CREATE TABLE comment
(
        id BIGSERIAL PRIMARY KEY ,
    author       integer                 not null,
    post_id         integer                 not null,
    comment_text varchar(255)                    not null,
    foreign key (author) references users (id) ON DELETE CASCADE,
    foreign key (post_id) references post (id) ON DELETE CASCADE
);
CREATE TABLE category
(
        id BIGSERIAL PRIMARY KEY ,
    name varchar(20)                     not null
);
CREATE TABLE post_category
(
    post_id     integer  not null,
    category_id integer  not null,
    foreign key (post_id) references post (id) ON DELETE CASCADE,
    foreign key (category_id) references category (id) ON DELETE CASCADE
);

CREATE TABLE user_subscription
(
    user_id       integer   not null,
    subscriber_id integer  not null,
    foreign key (user_id) references users (id) ON DELETE CASCADE,
    foreign key (subscriber_id) references users (id) ON DELETE CASCADE
);

CREATE TABLE image
(
    post_id integer  not null,
    image   BYTEA,
    foreign key (post_id) references post (id) ON DELETE CASCADE
);

CREATE TABLE visit_log
  (
          id BIGSERIAL PRIMARY KEY ,
      session_id varchar(40) not null,
      login varchar(60) not null,
      enter_time varchar(25) not null ,
      out_time varchar(25)
  );

