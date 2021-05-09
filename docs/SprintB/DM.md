# OO Analysis #


## Rationale to identify domain conceptual classes ##

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




