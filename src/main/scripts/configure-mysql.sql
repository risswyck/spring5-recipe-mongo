# docker container run -d --name recipedb -p 3306:3306 -v C:\Users\Stefan\OneDrive\Arbeit\udemy\spring\docker\mysql:/var/lib/mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:5.7.24

# databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

# users
CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'guru';
CREATE USER 'sfg_dev_prod'@'localhost' IDENTIFIED BY 'guru';
CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
CREATE USER 'sfg_dev_prod'@'%' IDENTIFIED BY 'guru';

# permissions
GRANT SELECT ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT INSERT ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT UPDATE ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT DELETE ON sfg_dev.* TO 'sfg_dev_user'@'localhost';

GRANT SELECT ON sfg_prod.* TO 'sfg_dev_prod'@'localhost';
GRANT INSERT ON sfg_prod.* TO 'sfg_dev_prod'@'localhost';
GRANT UPDATE ON sfg_prod.* TO 'sfg_dev_prod'@'localhost';
GRANT DELETE ON sfg_prod.* TO 'sfg_dev_prod'@'localhost';

GRANT SELECT ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* TO 'sfg_dev_user'@'%';

GRANT SELECT ON sfg_prod.* TO 'sfg_dev_prod'@'%';
GRANT INSERT ON sfg_prod.* TO 'sfg_dev_prod'@'%';
GRANT UPDATE ON sfg_prod.* TO 'sfg_dev_prod'@'%';
GRANT DELETE ON sfg_prod.* TO 'sfg_dev_prod'@'%';