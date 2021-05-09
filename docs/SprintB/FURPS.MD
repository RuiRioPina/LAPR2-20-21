# Supplementary Specification (FURPS+)

## Functionality

____

* Email/SMS

When the test results are completed the customer will be notificated about it.

Registered users shall received an email stating that the registration was completed and it shall contain the password of the user

* Localization

The application is to be used in England, therefore it should be in English.

* Reporting

Many Labs is required by the NHS to report all Covid-19 related information, daily. Therefore the application needs to generate reports on the information required by the NHS.

* Security

Clients must only be able to access their Test Results.

Only the Specialist Doctor is allowed to access all client data.

## Usability 

____

* Prevention of errors entered by the user

  * Being always one step ahead of users helps us predict any mistakes they might make.

  * We are all users, so we must think as one, to prevent the maximum number of possible errors that can occur.

  * The weaknesses of the software should be flagged, in order to correct it as soon as possible. One example of this would be the user being confused with one piece of information he needs to input.

* Aesthetics and design

The client hasn't any clear restriction for this. He stated that he wants to be impressed with it.

* Interface consistency

The interface shall be consistency, in order to reduce the learning curve of the application.

## Reliability
____
* Frequency and severity of failure

Both should be extremely small, since a possible failure might lead to a client's lack of wellbeing.

* Possibility of recovery

In case there is a failure, the program should try to save all the data it possibly can, and recover as soon as possible.

* Possibilty of prediction

"... the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19 tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)." Our application should be able to predict the number of covid-19 cases in the future.

* Accuracy

"To make the predictions, the NHS contract defines that a linear regression algorithm should be used. The NHS required that both simple linear and multiple linear regression algorithms should be evaluated to select the best model. The accuracy of the prediction models should be analysed and documented in the application user manual (in the annexes) that must be delivered with the application.". Our predictions should try to the be the most accurate they can be, therefore our software must use the most correct use of linear regression. Also the accuracy will have to be documented and analysed.	

* Average time between failures

The time between failures should be large, as the program should seek to have the least number of failures possible, especially since it will be used in a health-related context.

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._
___
* Response time

Any interface between a user and the system shall have a maximum response time of 3 seconds.

* Start-up time

The system should start up in less than 10 seconds. 

* Recovery time  

The system should not fail more than 5 days in one year. Whenever the system fails, there should be no data loss.  

* Memory consumption

The application will be deployed to a machine with 8GB of RAM.

* CPU usage 

Needs an intermediate CPU usage to support the use of JavaFX and some minor calculations.

* Application availability

The application should run on all platforms for which there exists a Java Virtual Machine.

## Supportability
_____
* Testability

The application must be subjected to various tests to verify its functionality so that, if an error is found, it is resolved.

Unit tests should be implemented, and a coverage report should be generated. 

Mutation tests should also be developed, in order to check if the tests cover every possibilities.

The development team must implement unit tests for all methods except methods that implement Input/Output operations.

* Configurability / Compatiblity

All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits.

To register a client, the receptionist needs the client's citizen card number and personal information( like NHS, TIF and phone number).

Only the specialist doctor is allowed to access all client data. 

To facilitate the access to the results, the application must allow ordering the clients by TIF and by name. 

The application must support the English language only.

The algorithm to be used by the application must be defined through a configuration file.

The implemented algorithm should be analysed in terms of its worst-case time complexity, and it should be compared to a provided benchmark algorithm. 

It is intended that the choice of the ordering algorithm is based on the algorithm complexity.

At least two sorting algorithms should be evaluated and documented in the application user manual that must be delivered with the application.

* Maintainabilty/ Installability 

The application must be developed in Java language using the IntelliJ IDE or Netbeans, and the  graphical interface is to be developed in JavaFX 11.

All the images/figures produced during the software development process should be recorded in SVG format.

## +

### Design Constraints

____
* Programming language

Java.

* Software process

Iterative Incremental Model.

* Mandatory Standards/patterns

Best Practices for identifying requirements and for OO software analysis and design.

* Use of development tools

We will use an IDE for coding, in this case IntelliJ IDEA. Bitbucket for Version Control. PlantUML for UML diagrams.

* Class library

None.

### Implementation Constraints

____
* Mandatory standards/patterns

Adopt recognized coding standards (e.g. CamelCase, Classes starting with uppercase letter...); Use Javadoc to generate useful documentation for Java code.

* Implementation languages

The application must be developed in Java language using the IntelliJ IDE or Netbeans.

* Database integrity

There are certain restricts on data input that should take place such as: "All those who wish to use the
application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits". 
If it is detected an anomaly on this standard, the application should let the user know before entering the data. If he still does enter it, it should give an error
message stating what happened. Furthermore, it should be guaranteed that only the specialist doctor is allowed to access all client data.
					
* Resource limits

The machine where the application will be deployed will have 8GB of ram, therefore the application should be designed to not need more than those resources.

* Operating system

Every Unix operating system, as well as Windows.

### Interface Constraints
____
Our application uses the NHS API to communicate externally, to provide information required by them.

The NHS API is used to generat barcodes to identify each sample, what's more it is used on sending to the NHS automatically generated reports,on 
a daily basis to the NHS.

### Physical Constraints

____
None.