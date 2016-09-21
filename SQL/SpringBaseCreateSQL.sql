connect 'jdbc:derby://localhost:1527/springclass;create=true;user=sa;password=password'; 

drop view airport_locations;
drop table Loan;
drop table dvd;
drop table dvdimage;
drop table location;
drop table airport;
drop table dvdtitle;
drop table ff_program_partners;
drop table ff_programs;


create table LOAN (
  LOAN_ID integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  EXPECTED_RETURN_LOCATION varchar(10),
  MEMBER_ID varchar(10),
  DVDCODE varchar(10),
  FROM_LOCATION varchar(10),
  LOAN_DATE date,
  EXPECTED_RETURN_DATE date,
  primary key (LOAN_ID));


CREATE TABLE airport (
  AIRPORT_CODE varchar(3) NOT NULL,
  AIRPORT_NAME varchar(40) default NULL,
  CITY varchar(40) default NULL,
  COUNTRY varchar(40) default NULL,
  PRIMARY KEY  (AIRPORT_CODE)
);


CREATE TABLE location (
  LOCATION_ID varchar(10) NOT NULL,
  AIRPORT_CODE varchar(3) default NULL,
  TERMINAL varchar(20) default NULL,
  LOCATION_INFORMATION varchar(60) default NULL,
  PRIMARY KEY  (LOCATION_ID),
  CONSTRAINT AIRPORT_CODE FOREIGN KEY (AIRPORT_CODE) REFERENCES airport (AIRPORT_CODE)
);

CREATE TABLE dvdtitle (
  DVD_TITLE_ID varchar(10) NOT NULL,
  DIRECTOR varchar(255) default NULL,
  ENCODING varchar(255) default NULL,
  FORMAT varchar(255) default NULL,
  RATED varchar(255) default NULL,
  STUDIO varchar(255) default NULL,
  YEARMONTH_RELEASE varchar(6) default NULL,
  TITLE varchar(255) default NULL,
  STARRING varchar(255) default NULL,
  PRIMARY KEY  (DVD_TITLE_ID)
);

CREATE TABLE dvd (
  DVDCODE varchar(10) NOT NULL,
  DVD_TITLE_ID varchar(10) NOT NULL,
  LOCATION_ID varchar(10) default NULL,
  ON_HOLD char(1) default NULL,
  PRIMARY KEY  (DVDCODE),
  CONSTRAINT DVD_TITLE_ID FOREIGN KEY (DVD_TITLE_ID) REFERENCES dvdtitle (DVD_TITLE_ID),
  CONSTRAINT LOCATION_ID FOREIGN KEY (LOCATION_ID) REFERENCES location (LOCATION_ID)
);

CREATE TABLE dvdimage (
  DVDTITLE_ID varchar(10) NOT NULL,
  DVDIMAGE blob,
  PRIMARY KEY  (DVDTITLE_ID),
  CONSTRAINT DVDTITLE_ID FOREIGN KEY (DVDTITLE_ID) REFERENCES dvdtitle (DVD_TITLE_ID)
);


CREATE TABLE ff_programs (
  PROGRAM_ID varchar(4) NOT NULL,
  PROGRAM_NAME varchar(40) default NULL,
  PRIMARY KEY  (PROGRAM_ID)
);

CREATE TABLE ff_program_partners (
  PROGRAM_PARTNER_ID varchar(10) NOT NULL,
  PROGRAM_ID varchar(4) default NULL,
  AIRLINE_NAME varchar(40) default NULL,
  AIRLINE_CODE varchar(3) default NULL,
  PRIMARY KEY  (PROGRAM_PARTNER_ID),
  CONSTRAINT Program_ID FOREIGN KEY (PROGRAM_ID) REFERENCES ff_programs (PROGRAM_ID)
);

CREATE VIEW airport_locations AS select distinct location.LOCATION_ID AS LOCATION_ID,location.AIRPORT_CODE AS AIRPORT_CODE,airport.AIRPORT_NAME AS AIRPORT_NAME,airport.CITY AS CITY,airport.COUNTRY AS COUNTRY,location.TERMINAL AS TERMINAL,location.LOCATION_INFORMATION AS LOCATION_INFORMATION from (location join airport on((location.AIRPORT_CODE = airport.AIRPORT_CODE)));

disconnect;
exit;