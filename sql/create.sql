CREATE DATABASE blog;
CREATE USER 'blog_app'@'localhost' IDENTIFIED BY '1234';
GRANT SELECT, INSERT, DELETE, UPDATE ON blog.* TO blog_app@'localhost';