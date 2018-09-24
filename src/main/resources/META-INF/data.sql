insert into Doctor (name, lastName, patronymic, specialization) values ('Maria', 'Kostnereva', 'Igorevna', 'Dentist');
insert into Doctor (name, lastName, patronymic, specialization) values ('Kirill', 'Ambrosov', 'Makarovich', 'Therapist');
insert into Doctor (name, lastName, patronymic, specialization) values ('Natalia', 'Kukushkina', 'Olegovna', 'Surgeon');
insert into Patient (name, lastName, patronymic, phone_number) values ('Anastasia', 'Hadaeva', 'Maksimovna', '9154865532');
insert into Patient (name, lastName, patronymic, phone_number) values ('Ilya', 'Alekseev', 'Alekseevich', '9658547812');
insert into Patient (name, lastName, patronymic, phone_number) values ('Anastasia', 'Ulyanova', 'Mihailovna', '9234576198');
insert into Recipe (date, description, priority, validity, doctor_id, patient_id) values (current_timestamp, 'drugs','CITO', 'MONTH_1', 1, 2);
insert into Recipe (date, description, priority, validity, doctor_id, patient_id) values (current_timestamp, 'kastorka' ,'NORMAL', 'MONTH_1', 2, 1);