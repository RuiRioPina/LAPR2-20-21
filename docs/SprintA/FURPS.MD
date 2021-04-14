# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._
____
* Security 

All operators responsible for entering and editing data in the system must ensure the validity of the information entered. external customers should only have access to their data.    

* Email/SMS

When the test results are completed the customer will be notificated about it.    

* Audit

The data recorded should always be sign by the operator.  

* Licensing

The owner Many Labs controls the application.  

## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._
____
* The user interface

Usability can be defined with five points:

Efficiency - How fast a user can perform after learning how to use a system.
	
Learnability - The user must be able to learn about the system, but faster and more easier than before.

Memorability - After the user learns about the system, how well can he remember the functions of that same system.
	
Errors -The user should make the least mistakes possible.
	
Satisfaction - How advantageous it was to use the system.

* Error prevention

To prevent some errors:

Adopt best practices for identifying requirements and for OO software analysis and design.

Adopt recognized coding standards.

* Interface aesthetics and design

The application graphical interface is to be developed in JavaFX 11.

* Help and documentation

Glossary.

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._
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

Should be almost immediate, so that the wait time for user actions decreases, improving the application functionality.

* Start-up time

Should be quick but can be affected by serialization. 

* Memory consumption

It can be pushed to store a big amount of tests, therefore it might consume a good amount of memory.

* CPU usage 

Needs an intermediate CPU usage to support the use of JavaFX and some minor calculations.

* Application availability

Should be available at any time, since it needs to calculate the tests and other functionalities.

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 
_____
* Testability

The application must be subjected to various tests to verify its functionality so that, if an error is found, it is resolved.

Unit tests should be implemented, and a coverage report should be generated. 

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

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
____
* Programming language

JAVA.

* Software process

Iterative Incremental Model.

* Mandatory Standards/patterns

Best Practices for identifying requirements and for OO software analysis and design.

* Use of development tools

We will use an IDE for coding, in this case IntelliJ IDEA. Bitbucket for Version Control. PlantUML for UML diagrams.

* Class library

None.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._
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

None.

* Operating system

Every Unix operating system, as well as Windows.

### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._
____
Our application uses the NHS API to communicate externally, to provide information required by them.

The NHS API is used to generated barcodes to identify each sample, what's more it is used on sending to the NHS automatically generated reports,on 
a daily basis to the NHS.

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._
____
None.