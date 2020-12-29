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

### Install generator jar

### Generate

Run maven jooq-codegen:generate

