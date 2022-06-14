create table users(
	id varchar primary key not null,
	username varchar not null,
	password varchar not null,
	role varchar not null
)

create table ers_user_role(
	role_id varchar primary key not null,
	role varchar
)

create table ers_user(
	user_id varchar primary key not null,
	username varchar not null,
	email varchar,
	password varchar,
	given_name varchar,
	surname varchar,
	is_active boolean,
	role_id varchar
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