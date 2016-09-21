connect 'jdbc:derby://localhost:1527/springclass;create=true;user=sa;password=password'; 

INSERT INTO loan (EXPECTED_RETURN_LOCATION,MEMBER_ID,DVDCODE,FROM_LOCATION,LOAN_DATE,EXPECTED_RETURN_DATE) VALUES 
 ('LAS-IDA','93947','29108','AMS-LC0','2006-05-11','2006-05-11');

select * from loan;

disconnect;
exit;

