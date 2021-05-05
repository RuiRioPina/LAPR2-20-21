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

* **Question1:** Of all the information that we have about the CLA, what would be the bare minimum to be able to successfully register a new CLA?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7536)
	* **Answer:** All information is required.

* **Question2:** Should information regarding the execution or not of covid-19 tests in the CLA be recorded or is it preferable that it is by default that it does not?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7536)
	* **Answer2:** From the Project Description we get: "All Many Labs clinical analysis laboratories perform clinical blood tests, and a subset of these laboratories also performs Covid-19 tests".
From Sprint B requirements we get: "US8: As an administrator, I want to register a new clinical analysis laboratory stating which kind of test(s) it operates."

* **Question3:** Is there a maximum limit of types of tests a clinical analysis laboratory can operate?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7553)
	* **Answer3:** No.

* **Question4:** We know through the specifications document that "All Many Labs clinical analysis laboratories perform clinical blood tests".
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7553)
My question therefore is: When creating a new Clinical Analysis Laboratory, should the system automatically record that it operates that type of test or should the person who is creating it select it manually while selecting other types of tests? Or other option?
	* **Answer4:** The administrator should select, manually, all the types of tests to be performed by the Clinical Analysis Laboratory.

* **Question5:** Which type/format has LaboratoryID, address, name, phone number, and TIN number on Clinical Analysis Laboratory?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7636)
	* **Answer5:** Each Clinical Analysis Laboratory is characterized by the following attributes:
		* Laboratory ID: five alphanumeric characters;
		* Name: A string with no more than 20 characters;
		* Address: A string with no more than 30 characters;
		* Phone Number: 11 digit number;
		* TIN number: 10 digit number;
		* Type of tests performed by the lab.
	 All information is required.

* **Question6:** Does Chemical Laboratory have the same type/format of the attributes of Clinical Analysis Laboratory?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7636)
	* **Answer6:** For the Chemical Laboratory there is no need to specify the type of tests to be performed. Other than this, the attributes and their type/format are the ones presented in my previous answer.

* **Question7:** Are the test types typed in or selected from those that the program has? 
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7707)
	* **Answer7:** The test types are selected from a list.

* **Question8:** The creation of new parameters, parameter categories, test types, the client data introduced during the client registration and basically all the data that is registered in the application has to remain after the closing of the application (saved on the disk)?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7707)
	* **Answer8:** For now there is no need to save the data. In the following sprints we will introduce new requirements that will clarify this point.

* **Question9:** What information is associated with a clinical Analysis laboratory?(e.g. designation, localization...)What rules are applicable to such data?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7749)
	* **Answer9:** I already answered this question. It is not a good approach to ask the same question two times.

		"Each Clinical Analysis Laboratory is characterized by the following attributes:

		* Laboratory ID;
		* Name;
		* Address;
		* Phone Number;
		* TIN number.

		The Chemical Laboratory is characterized by the following attributes:

		* Name;
		* Address;
		* Phone Number;
		* TIN number.

		Many Labs company has only one chemical lab."

* **Question10:** When registering a new laboratory does the administrator selects or write the type of tests performed by a clinical Analysis laboratory?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7749)
	* **Answer10:** Yes. In Sprint B requirements we get: "US8: As an administrator, I want to register a new clinical analysis laboratory stating which kind of test(s) it operates".

* **Question11:** According to US8 "As an administrator, I want to register a new clinical analysis laboratory stating which kind of test(s) it operates" its supposed to create a new clinical analysis laboratory. However, in several previous posts awnsers you mentioned the Chemical Laboratory. Are both of labs connected to this sprint?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7859)
	* **Answer11:** Please read carefully the Project Description and Sprint B requirements.

* **Question12:** When the administrator is registering a Clinical Analysis Laboratory and typing the information does he type the laboratory ID or is it generated by the system afterwards?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7862)
	* **Answer12:** The Laboratory ID is introduced manually.

* **Question13:** When starting a new clinical analysis laboratory registration, should the entered requested data be showed to the user for confirmation purposes?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7873)
	* **Answer13:** It is always a good practice to validate and ask for confirmation.

* **Question14:** Are two Clinical Analysis Laboratories with the same:
	a) Laboratory ID OR
	b) name OR
	c) address OR
	d) phone Number OR
	e) TIN number
allowed to exist?
[Link](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7911)
	* **Answer14:** Only the name of two CAL can be same.


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

![US8-SSD](ImagesUsed/US8_SSD.svg)


### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.* 

![US8-MD](ImagesUsed/US8_MD.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1: asks to register new clinical analysis laboratory 		 |							 |             |                              |
| Step 2  		 |							 |             |                              |
| Step 3  		 |							 |             |                              |
| Step 4  		 |							 |             |                              |
| Step 5  		 |							 |             |                              |
| Step 6  		 |							 |             |                              |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * ClinicalAnalysisLaboratory


Other software classes (i.e. Pure Fabrication) identified: 
 * RegisterClinicalAnalysisLaboratoryUI  
 * RegisterClinicalAnalysisLaboratoryController

## 3.2. Sequence Diagram (SD)

![US8-SD](ImagesUsed/US8_SD.svg)

## 3.3. Class Diagram (CD)

![US8-CD](ImagesUsed/US8_CD.svg)

# 4. Tests 
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.* 

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null, null, null, null, null);
	}

**Test 2:** Check that it is not possible to create an instance of the Example class with the same attributes as other instance (exept name).

	@Test(expected = IllegalArgumentException.class)
		public void ensureSameAttributesIsNotAllowed() {
		Exemplo instance1 = new Exemplo(laboratoryID, name, adress, phoneNumber, tin, typeOfTest)
		Exemplo instance2 = new Exemplo(laboratoryID, name, adress, phoneNumber, tin, typeOfTest)
 		instance1==instance2

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





