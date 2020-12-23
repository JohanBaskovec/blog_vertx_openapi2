# Starter

## Application configuration
### Set the path to the config file
For example
```
export BLOG_CONF=/home/johan/IdeaProjects/blog-vertx-openapi2/conf.example.json
```

## Postgres
### Install postgresql
```
sudo apt-get install postgresql
```

### Run
```
sudo systemctl start postgresql@12-main
```

### Create a role
```
sudo su - postgres
psql
create role blog_admin superuser login password 'password
```

### Create the database
```
createdb blog
```

### Create the schema
Run src/main/resources/db/00001.sql

## Building

To launch your tests:
```
./mvnw clean test
```

To package your application:
```
./mvnw clean package
```

To run your application:
```
./mvnw clean compile exec:java
```

## Help

* https://vertx.io/docs/[Vert.x Documentation]
* https://stackoverflow.com/questions/tagged/vert.x?sort=newest&pageSize=15[Vert.x Stack Overflow]
* https://groups.google.com/forum/?fromgroups#!forum/vertx[Vert.x User Group]
* https://gitter.im/eclipse-vertx/vertx-users[Vert.x Gitter]


