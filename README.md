Build your bank
===============

The application supports as the below json structure
```
[
  {
    "name": "user1",
    "instruction": "DEPOSIT",
    "amount": "20.20"
  }
  ...
]
```

None of these values are mandatory
It will ignore all empty lines ,spaces and any additional fields appended to the file.


###### Code flow
##### Input -> Orchestrator -> Action -> Account


The Orchestrator chains the flow of
- process the file
- kick off the action execution.

The Actions are broken down into `DEPOSIT, WITHDRAW & BALANCE.`
These concrete classes manage action specific behaviour including validation.


The design pattern that could be used to implement the Bank Actions is the `Command` pattern.
There is the standard way i.e. using an interface and the related Action classes to implement
this interface. I ,however, chose to do it differently.The reason being, the actions i.e Deposit & withdraw 
have very specific behaviour on the balance of an account.
For the current scope, an enum extension function is used to acheive the desired behaviour. 
If need be, the action classes can be customised to hold action specific implementation.

 
The Bank balance should change when the account holder balance changes.Used the `Observer` Pattern to implement that behaviour.

# PreRequistes
- Gradle 5.2.1
- Jvm 1.8
- Kotlin 1.3.20

## Instructions
To Build & run Unit tests  => `./gradlew build`

To run the application => `./gradlew run`

To run the Integration tests  => `./gradlew integrationTest`

## Scenarios considered
The program will simply print an error message 
and continue processing the file for the below scenarios
- an account is overdrawn 
- invalid deposit amount e.g -3. 
- invalid withdrawal amount e.g -3.
- invalid instructions 


## Assumptions
- The Bank has a predefined mapping of customer to Account.
- Only existing users can perform transactions. This could be extended to create a new account and assign a account number.
- One to one mapping of account and users for now, with the customerId (String type)being the key.
This is so that in case of a single user having multiple  accounts, the Bank.customerToAccountMapping can
 be refactored to hold a `List<Account>`
 With the user as the primary key , two users can share the same account i.e Joint accounts.


## Areas to improve
- Exception could be wrapped in a customised `ProcessingError` class. This would help with
userexceptions and systemsexception separation.
- A user should not see the banks balance. The current code displays 
the users account as well as the banks balance. This can be done as an extension with a separate command.
This was done because the cited example mentioned it.

 
