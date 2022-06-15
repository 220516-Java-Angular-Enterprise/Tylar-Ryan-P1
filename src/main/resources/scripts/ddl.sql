drop table if exists users;
drop table if exists ers_user;
drop table if exists user_role;


create table user_role(
	role_id varchar primary key not null,
	role varchar
)

create table ers_user(
	user_id varchar primary key,
	username varchar not null,
	email varchar not null,
	password varchar not null,
	given_name varchar,
	surname varchar not null,
	is_active boolean not null,
	role_id varchar not null,
	constraint fk_role_id foreign key (role_id) references user_role(role_id)
)

create table reimbursement_type(
	type_id varchar primary key not null,
	type varchar
)

create table reimbursement_status(
	status_id varchar primary key not null,
	status varchar
)

create table reimbursement(
	reimb_id varchar primary key not null,
	amount varchar not null,
	submitted timestamp,
	resolved timestamp,
	description varchar,
	receipt bytea,
	payment_id varchar,
	author_id varchar,
	resolver_id varchar,
	status_id varchar,
	type_id varchar
)

alter table reimbursement
add constraint fk_status_id
foreign key (status_id)
references reimbursement_status(status_id);

alter table reimbursement
add constraint fk_type_id
foreign key (type_id)
references reimbursement_type(type_id);

