Hibernate: create table Doctor (id bigint not null, name varchar(255) not null, patronymic varchar(255), surname varchar(255) not null, specialization varchar(255) not null, primary
key (id))
Hibernate: create table Patient (id bigint not null, name varchar(255) not null, patronymic varchar(255), surname varchar(255) not null, phone_number varchar(255), primary key (id))
Hibernate: create table Recipe (id bigint not null, date date, description varchar(255), priority varchar(255), validity bigint not null, doctor_id bigint not null, patient_id bigint
 not null, primary key (id))
Hibernate: alter table Recipe add constraint FKk9fqn9dj3s3h9slvynsnn0t0g foreign key (doctor_id) references Doctor
Hibernate: alter table Recipe add constraint FKajyr5pfsxrt9ngh4nn3b9j4f6 foreign key (patient_id) references Patient
