# raju-poc


For Spring Boot CRUD Find the # MongoDB DB details

use pocdb;

db.createCollection("Employee", { capped : true, autoIndexId : true, size : 
   6142800, max : 10000 } );
   
db.Employee.insert({
	
	"empId": "1",
    "empName":"Rajkumar",
    "doj": "12-07-2010",
    "jobTitle": "Technical Lead",
    "department": "APAC",
    "company": "ICS",
    "city": "Bangalore",
    "country": "India",
    "email": "rjkumar@gmail.com",
    "phone": "7349519055",
    "thumbImg": "http://rajkumarj.info/images/profile/profile.jpg"
	
});
