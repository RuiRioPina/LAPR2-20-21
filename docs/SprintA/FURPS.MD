# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._



(fill in here)



## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


(fill in here )

## Reliability

	Frequency and severity of failure: Both should be extremely small, since a possible failure might lead to a client's lack of wellbeing.
	Possibility of recovery: In case there is a failure, the program should try to save all the data it possibly can, and recover as soon as possible.
	Possibilty of prediction: "... the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19 tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)." Our application should be able to predict the number of covid-19 cases in the future.
	Accuracy:"To make the predictions, the NHS contract defines that a linear regression algorithm should be used. The NHS required that both simple linear and multiple linear regression algorithms should be evaluated to select the best model. The accuracy of the prediction models should be analysed and documented in the application user manual (in the annexes) that must be delivered with the application.". Our predictions should try to the be the most accurate they can be, therefore our software must use the most correct use of linear regression. Also the accuracy will have to be documented and analysed.	
	Average time between failures: The time between failures should be large, as the program should seek to have the least number of failures possible, especially since it will be used in a health-related context.


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

Response time: 
	Should be almost immediate so the wait time for tests decreases, improving the application functionality.
	"Therefore, the company wants to decrease the number of tests waiting for its result. It proceeds as following: for difference between the number of new tests and the number of results available to the client during each half an hour period is computed."
Memory consumption:
	Can be pushed to run two at the same time, then consuming a good amount of memory.
	"The application should use object serialization to ensure data persistence between two runs of the 
	application"
CPU usage:
	Need a high CPU usage to support the use of JavaFX.
	"The application graphical interface is to be developed in JavaFX 11"
Load capacity:
	Could be available to load files in various format.
	"The algorithm to be used by the application must be defined through a configuration file."
Application availability:
	Should be available any time because it needs to calculate the test and another functionalities.
	"To evaluate this, it proceeds as following: for any interval of time, for example one week (6 working days with 12 working hours per day)"


## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 



(fill in here )


#+

### Design Constraints
  
-Programming language: JAVA

-Software process: Iterative Incremental Model

-Mandatory Standards/patterns: Best Practices for identifying requirements and for OO software analysis and design.

-Use of development tools: We will use an IDE for coding, in this case IntelliJ IDEA. Bitbucket for Version Control. PlantUML for UML diagrams.

-Class library: none


### Implementation Constraints

Mandatory standards/patterns: Adopt recognized coding standards (e.g. CamelCase, Classes starting with uppercase letter...)
Use Javadoc to generate useful documentation for Java code

Implementation languages : The application must be developed in Java language using the IntelliJ IDE or Netbeans

Database integrity: There are certain restricts on data input that should take place such as: "All those who wish to use the
application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits". If it is detected
an anomaly on this standard, the application should let the user know before entering the data. If he still does enter it, it should give an error
message stating what happened.
		    Furthermore, it should be guaranteed that only the specialist doctor is allowed to access all client data.
					
Resource limits: none

Operating system: Every Unix operating system, as well as Windows.


### Interface Constraints

Our application uses the NHS API to communicate externally, to provide information required by them.

The NHS API is used to generated barcodes to identify each sample, what's more it is used on sending to the NHS automatically generated reports,on 
a daily basis to the NHS.


### Physical Constraints

None.