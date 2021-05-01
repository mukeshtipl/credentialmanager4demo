create table user (
id integer not null,
username varchar(255) not null,
email varchar(255) ,
password varchar(255),
primary key(id)
);

create table container (
id integer not null,
name varchar(255) not null,
owner integer  not null,
primary key(id),
foreign key (owner) references User(id)
);

create table credential (
id integer not null,
username varchar(255) not null,
container integer  not null,
password varchar(255),
primary key(id),
FOREIGN KEY (container) REFERENCES  container(id)
);