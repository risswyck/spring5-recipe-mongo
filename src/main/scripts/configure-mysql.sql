# docker container run -d --name recipedb -p 3306:3306 -v C:\Users\Stefan\OneDrive\Arbeit\udemy\spring\docker\mysql:/var/lib/mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:5.7.24

# databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

# users
CREATE USER 'sfg_dev'@'localhost' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod'@'localhost' IDENTIFIED BY 'guru';
CREATE USER 'sfg_dev'@'%' IDENTIFIED BY 'guru';
CREATE USER 'sfg_prod'@'%' IDENTIFIED BY 'guru';

# permissions
GRANT SELECT ON sfg_dev.* TO 'sfg_dev'@'localhost';
GRANT INSERT ON sfg_dev.* TO 'sfg_dev'@'localhost';
GRANT UPDATE ON sfg_dev.* TO 'sfg_dev'@'localhost';
GRANT DELETE ON sfg_dev.* TO 'sfg_dev'@'localhost';

GRANT SELECT ON sfg_prod.* TO 'sfg_prod'@'localhost';
GRANT INSERT ON sfg_prod.* TO 'sfg_prod'@'localhost';
GRANT UPDATE ON sfg_prod.* TO 'sfg_prod'@'localhost';
GRANT DELETE ON sfg_prod.* TO 'sfg_prod'@'localhost';

GRANT SELECT ON sfg_dev.* TO 'sfg_dev'@'%';
GRANT INSERT ON sfg_dev.* TO 'sfg_dev'@'%';
GRANT UPDATE ON sfg_dev.* TO 'sfg_dev'@'%';
GRANT DELETE ON sfg_dev.* TO 'sfg_dev'@'%';

GRANT SELECT ON sfg_prod.* TO 'sfg_prod'@'%';
GRANT INSERT ON sfg_prod.* TO 'sfg_prod'@'%';
GRANT UPDATE ON sfg_prod.* TO 'sfg_prod'@'%';
GRANT DELETE ON sfg_prod.* TO 'sfg_prod'@'%';