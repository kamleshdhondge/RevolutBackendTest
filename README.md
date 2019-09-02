# RevolutBackendTest
Solution for Backend Engineer test of Revolt

## API documentation
Application exposes following REST endpoints

### GET /bankaccount/
Retrives all the bank accounts
Response:
```json
[
    {
        "id": 12,
        "accountName": "Kamlesh",
        "currentBalance": 100
    },
    {
        "id": 13,
        "accountName": "Mark",
        "currentBalance": 100
    }
]
```
Response 200 if OK


### POST /bankaccount/
Creates a BankAccount
Sample request:
```json
{
     id": 12,
    "accountName": "Kamlesh",
    "currentBalance": 100
}
```
Response will be 200OK if created

### GET /transaction
Gets all The Transactions
Response
```json
[
    {
        "transactionId": 1234566,
        "fromAccNo": 12,
        "toAccNo": 13,
        "amount": 1,
        "creationDate": 1567463241262,
        "updateDate": 1567463241262,
        "status": "SUCCEED",
        "failMessage": ""
    }
]
```

200 if OK

### POST /transaction
Transafers amount from "FromAccountNumber" of Bank to "toAccountNumber" of Bank
Sample Request:
```json
{
	"transactionId" : 1234566,
	"fromAccNo" : 12,
	"toAccNo" : 13,
	"amount" : 1

}
 ````
 
 Response will have status as "SUCCEED" if successful else if failed, the message in "failMessage" field
```json
{
    "transactionId": 1234566,
    "fromAccNo": 12,
    "toAccNo": 13,
    "amount": 1,
    "creationDate": 1567463241262,
    "updateDate": 1567463241262,
    "status": "SUCCEED",
    "failMessage": ""
}
```
## Tests
Application code is covered with unit tests.<br>
BankAccountTest is for BankAccount API's.<br>
TransactionTest is for Transaction related testcases.

## Build and run
Application can be built with command,
```
mvn clean install
```
To Skip Tests
```
mvn clean install -DskipTests
```
To run the application
```
java -jar target/backend-test-0.0.1-jar-with-dependencies.jar 
```
