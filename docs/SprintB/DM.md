# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Test
* Report


---

**Transaction Line Itemss**

* Sample

---

**Product/Service related to a Transaction or Transaction Line Item**

* Parameter

---


**Transaction Records**

* TestResults
* ChemicalAnalysisResults
* ResultsValidation
* Test&ResultsComparison
 

---  


**Roles of People or Organizations**


* Client
* Doctor
* Receptionist
* MedicalLabTechnician
* ClinicalChemistTechnologist
* SpecialistDoctor
* LaboratoryCoordinator
* Courier
* Administrator

---


**Places**

  
* ClinicalAnalysisLaboratory 
* ChemicalLaboratory
---

**Noteworthy Events**

* Chemical Analysis
* Diagnosis
* Registration
* TestVerification
* ClientNotification


---


**Physical Objects**

* Prescription
* LabOrder


---


**Descriptions of Things**

* TypeOfTest
* TestReferenceValues


---


**Catalogs**




---


**Containers**

* TestResultList


---


**Elements of Containers**

* TestResults


---


**Organizations**

* Many Labs
* Company
 

---

**Other External/Collaborating Systems**

* NHS
* API
* SortingAlgorithm
* Algorithm
* BenchmarkAlgorithm
* CentralApplication
 


---


**Records of finance, work, contracts, legal matters**



---


**Financial Instruments**



---


**Documents mentioned/used to perform some work/**

* ChemicalAnalysisResults

---



###**Rationale to identify associations between conceptual classes**###

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

+ **_A_** is physically or logically part of **_B_**
+ **_A_** is physically or logically contained in/on **_B_**
+ **_A_** is a description for **_B_**
+ **_A_** known/logged/recorded/reported/captured in **_B_**
+ **_A_** uses or manages or owns **_B_**
+ **_A_** is related with a transaction (item) of **_B_**
+ etc.



| Concept (A) 		|  Association   	|  Concept (B) |
|----------	   		|:-------------:		|------:       |
| Administrator  	| is part of    		 	| Company  |
| Company  	| owns    		 	| ClinicalAnalysisLaboratories  |
|   	| owns    		 	| ChemicalLaboratory  |
| ClinicalAnalysisLaboratories  	| collects    		 	| Sample  |
| SpecialistDoctor  	| writes    		 	| Report  |
|   	| performs    		 	| ResultsValidation  |
|   	| reviews    		 	| TestResults  |
|   	| can check    		 	| TestResultList  |
| Report   	| is sent to client   		 	| Client  |
|    	| contains   		 	| TestResults  |
| Receptionist  	| registers    		 	| Client  |
| MedicalLabTechnician  	| conducts    		 	| Test  |
| Client  	| brings    		 	| LabOrder  |
|   	| performs a    		 	| Test  |
| Test  	| is a   		 	| TypeOfTest  |
|   	| produces a   		 	| Sample  |
| Sample  	| are sent to    		 	| ChemicalLaboratory  |
|   	| is received by   		 	| ChemicalChemistTechnologist  |
| ClinicalChemistTechnologist  	| produces    		 	| TestResults  |
|   	| can check    		 	| TestResultList  |
| Test Results  	| are used in    		 	| NHSReport  |
|   	| must be validated by    		 	| LaboratoryCoordinator  |
| LaboratoryCoordinator 	| can check   		 	| TestResultList  |




## Domain Model

__________

![DM.svg](/docs/SprintB/ImagesUsed/DM.svg)



