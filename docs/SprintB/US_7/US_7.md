US 003 - Register Client
1. Requirements Engineering
In this section, it is suggested to capture the requirement description and specifications as provided by the client as well as any further clarification on it. It is also suggested to capture the requirements acceptance criteria and existing dependencies to other requirements. At last, identfy the involved input and output data and depicted an Actor-System interaction in order to fulfill the requirement.

1.1. User Story Description
US7:As an administrator, I want to register a new employee.

1.2. Customer Specifications and Clarifications
Insert here any related specification and/or clarification provided by the client together with your interpretation. When possible, provide a link to such specifications/clarifications.

From the specifications document:


From the client clarifications:

Question: What parameter (asked by the receptionist) should the system use to create the password of the new client?

Answer: The password should be randomly generated. It should have ten alphanumeric characters.
-

1.3. Acceptance Criteria
AC 1 : Each user must have a single role defined in the system. The"auth" component available on the repository must be reused (without modifications).

1.4. Found out Dependencies
No dependicies were found.

1.5 Input and Output Data
Input Data
  • Typed data: UserInformation(username, email and password), PersonalInformation(name,TIM,adress,birth date,sex, phone number) 
  • Selected data: (none)
Output Data
  • (In)Success of the operation
1.6. System Sequence Diagram (SSD)
US04-SSD
@startuml
autonumber
actor "Administrator" as RC

activate RC
RC -> ":System" : asks to register new Employee
activate ":System"
":System" --> RC : requests data (name,TIM,adress,birth date,sex, phone numberl)
deactivate ":System"

RC -> ":System" : asks account information
activate ":System"
":System" --> RC : requests data (username,email,password)
deactivate ":System"

RC -> ":System" : confirms the data
activate ":System"
":System" --> RC : informs operation success
deactivate ":System"

deactivate RC
@enduml




1.7 Other Relevant Remarks
Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.

2. OO Analysis
2.1. Relevant Domain Model Excerpt
In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.

USXX-MD

2.2. Other Remarks
Some questions sent by other users that have yet to be answered by the client should be taken into account.
3. Design - User Story Realization
3.1. Rationale
The rationale grounds on the SSD interactions and the identified input/output data.

Interaction ID	Question: Which class is responsible for...	Answer	Justification (with patterns)
Step 1	Register a new Employee		
Step 2			
Step 3			
Step 4			
Step 5			
Step 6			
Systematization
According to the taken rationale, the conceptual classes promoted to software classes are:

Class1
Class2
Class3
Other software classes (i.e. Pure Fabrication) identified: * xxxxUI
* xxxxController

3.2. Sequence Diagram (SD)
In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.

USXX-SD

3.3. Class Diagram (CD)
In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.

USXX-CD

4. Tests
In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.

DO NOT COPY ALL DEVELOPED TESTS HERE

Test 1: Check that it is not possible to create an instance of the Example class with null values.

@Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
    Exemplo instance = new Exemplo(null, null);
}
It is also recommended to organize this content by subsections.

5. Construction (Implementation)
In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.

It is also recommended to organize this content by subsections.

6. Integration and Demo
In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.

7. Observations
In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.