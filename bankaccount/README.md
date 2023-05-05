Bank-account-kata

to respect the hexagonal architecture , I've created a package "BusinessLogic" that contains two interfaces

"BankAccountLogic" has two business methods which are : checkAccount and listAllOperations 

"OperationsLogic" has also two business methods : DepositMoney and WithdrawalMoney . 

and of course we have models and reposistory packages for database connection 

I've used H2 as a persistence adapter (in memory database) for this app ( configuration for database in /ressources
/application.properties)
data.sql script populates the account table in our schema with some sample data .
Then, after starting the application, we can navigate to http://localhost:8080/h2-console, which will present us with 
a login page.

and we have controllers and services(implementation of businessLogic Interfaces) which are adapters to our HTTP requests
( REST API ).


Finally , after running the application you can test it via postman .

1-Deposit of a given amount of money on an account:

PUT    url : http://localhost:8080/api/accounts/{accountId}/deposit  

Payload is as:
{
"amount":4000
}

Response as :
{
"balance": 303000,
"latestOperations": [
{
"id": 1,
"date": "2023-03-31T12:58:31.939000200Z",
"type": "DEPOSIT",
"amount": 3000
}
]
}

2- Withdrawal of a given amount of money from an account:
PUT      url : http://localhost:8080/api/accounts/{accountId}/withdrawal 
payload is as :

{
"amount":20000
}
Response preview :

{
"balance": 283000,
"latestOperations": [
{
"id": 2,
"date": "2023-03-31T12:58:36.196326900Z",
"type": "WITHDRAWAL",
"amount": -20000
},
{
"id": 1,
"date": "2023-03-31T12:58:31.939Z",
"type": "DEPOSIT",
"amount": 3000
}
]
}

3 - history of  All operations :
GET      http://localhost:8080/api/accounts/{accountId}/history
Response preview:

{
"id": 1,
"date": "2023-03-31T12:58:31.939Z",
"type": "DEPOSIT",
"amount": 3000
},
{
"id": 2,
"date": "2023-03-31T12:58:36.196327Z",
"type": "WITHDRAWAL",
"amount": -20000
}
]

4 - Check account balance  : 

GET    url : http://localhost:8080/api/accounts/{accountId}


response as : 

{
"balance": 283000,
"latestOperations": [
{
"id": 2,
"date": "2023-03-31T12:58:36.196327Z",
"type": "WITHDRAWAL",
"amount": -20000
},
{
"id": 1,
"date": "2023-03-31T12:58:31.939Z",
"type": "DEPOSIT",
"amount": 3000
}
]
}

Finally , I did not handle exceptions errors (to do), I can implement it after confirming with you if I have respected
the hexagonal architecture constraints . 

and I tested the application via postman , I can also add unit testing(unit testing junit) if the application respect 
the constraints that 
you mentioned .