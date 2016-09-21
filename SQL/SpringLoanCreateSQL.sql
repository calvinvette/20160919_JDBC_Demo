connect 'jdbc:derby://localhost:1527/springclass;create=true;user=sa;password=password'; 

drop table Loan;
create table LOAN (
  LOAN_ID integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  EXPECTED_RETURN_LOCATION varchar(10),
  MEMBER_ID varchar(10),
  DVDCODE varchar(10),
  FROM_LOCATION varchar(10),
  LOAN_DATE date,
  EXPECTED_RETURN_DATE date,
  primary key (LOAN_ID));
disconnect;
exit;