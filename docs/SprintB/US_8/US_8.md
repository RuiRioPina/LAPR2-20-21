# US 8 - Register a new clinical analysis laboratory

## 1. Requirements Engineering

### 1.1. User Story Description

As an administrator, I want to register a new clinical  analysis laboratory stating which kind of test(s) it operates.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document**

"All the tests (clinical blood tests and Covid 19 tests) performed by the network of laboratories are
registered locally by the medical lab technicians who collect the samples. The samples are sent
daily to the chemical laboratory where the chemical analy se s are performed and results obtained."

**From the client clarifications**

* **Question:** Are the test types typed in or selected from those that the program has? 
	* **Answer:** The test types are selected from a list.

* **Question:** Which type/format has LaboratoryID, address, name, phone number, and TIN number on Clinical Analysis Laboratory?
	* **Answer:** Each Clinical Analysis Laboratory is characterized by the following attributes:
		* Laboratory ID: five alphanumeric characters;
		* Name: A string with no more than 20 characters;
		* Address: A string with no more than 30 characters;
		* Phone Number: 11 digit number;
		* TIN number: 10 digit number;
		* Type of tests performed by the lab.
	 All information is required.

* **Question:** Is there a maximum limit of types of tests a clinical analysis laboratory can operate?
	* **Answer:** No.

* **Question:** We know through the specifications document that "All Many Labs clinical analysis laboratories perform clinical blood tests".
My question therefore is: When creating a new Clinical Analysis Laboratory, should the system automatically record that it operates that type of test or should the person who is creating it select it manually while selecting other types of tests? Or other option?
	* **Answer:** The administrator should select, manually, all the types of tests to be performed by the Clinical Analysis Laboratory.


### 1.3. Acceptance Criteria

* AC1: Laboratory ID should have five alphanumeric characters;
* AC2: Name should be a string with no more than 20 characters;
* AC3: Address should be a string with no more than 30 characters;
* AC4: Phone Number should be a 11 digit number;
* AC5: TIN number should be a 10 digit number;
* AC6: Should refer the type of tests performed by the lab;

### 1.4. Found out Dependencies

This US has dependency with the US9- As an administrator, I want to specify a new type of test and its collecting methods, because the administrator needs to know what type of test the clinical analysis laboratory performs in order to register it. 

### 1.5 Input and Output Data

**Input Data**

* Typed data:
	* Laboratory ID;
	* Name;
	* Adress;
	* Phone Number;
	* TIN number;
	
* Selected data:
	* Type of tests;

**Output data:**

* (In)Success of the operation.


### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and outputted to fulfill the requirement. All interactions must be numbered.*

![USXX-SSD](USXX-SSD.svg)


### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.* 

![USXX-MD](USXX-MD.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |							 |             |                              |
| Step 2  		 |							 |             |                              |
| Step 3  		 |							 |             |                              |
| Step 4  		 |							 |             |                              |
| Step 5  		 |							 |             |                              |
| Step 6  		 |							 |             |                              |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Class1
 * Class2
 * Class3

Other software classes (i.e. Pure Fabrication) identified: 
 * xxxxUI  
 * xxxxController

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.* 

![USXX-SD](USXX-SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![USXX-CD](USXX-CD.svg)

# 4. Tests 
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.* 

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





