-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: hcc_reportmaster
-- ------------------------------------------------------
-- Server version	5.7.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allocater_tl_map`
--

DROP TABLE IF EXISTS `allocater_tl_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allocater_tl_map` (
  `allocater_id` int(11) NOT NULL AUTO_INCREMENT,
  `allocater_firstname` varchar(50) DEFAULT NULL,
  `allocater_middlename` varchar(50) DEFAULT NULL,
  `allocater_lastname` varchar(50) DEFAULT NULL,
  `tl_id` int(11) NOT NULL DEFAULT '0',
  `tl_firstname` varchar(50) DEFAULT NULL,
  `tl_middlename` varchar(50) DEFAULT NULL,
  `tl_lastname` varchar(50) DEFAULT NULL,
  `allocater_client_id` int(11) DEFAULT NULL,
  `allocater_client_name` varchar(50) DEFAULT NULL,
  `tl_client_id` int(11) DEFAULT NULL,
  `tl_client_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`allocater_id`,`tl_id`),
  KEY `tl_id` (`tl_id`),
  KEY `tl_client_id` (`tl_client_id`),
  KEY `allocater_client_id` (`allocater_client_id`),
  CONSTRAINT `allocater_tl_map_ibfk_1` FOREIGN KEY (`tl_id`) REFERENCES `user_master` (`user_id`),
  CONSTRAINT `allocater_tl_map_ibfk_2` FOREIGN KEY (`allocater_id`) REFERENCES `user_master` (`user_id`),
  CONSTRAINT `allocater_tl_map_ibfk_3` FOREIGN KEY (`tl_client_id`) REFERENCES `client_master` (`client_id`),
  CONSTRAINT `allocater_tl_map_ibfk_4` FOREIGN KEY (`allocater_client_id`) REFERENCES `client_master` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocater_tl_map`
--

LOCK TABLES `allocater_tl_map` WRITE;
/*!40000 ALTER TABLE `allocater_tl_map` DISABLE KEYS */;
INSERT INTO `allocater_tl_map` VALUES (1,'Abhijit',NULL,'Chikhalikar',2,'Abhi',NULL,'Chikar',1,'SGS',1,'SGS'),(1,'Abhijit',NULL,'Chikhalikar',3,'@bhi',NULL,'Chikr',1,'SGS',1,'SGS');
/*!40000 ALTER TABLE `allocater_tl_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bucket_master`
--

DROP TABLE IF EXISTS `bucket_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bucket_master` (
  `bucket_id` int(11) NOT NULL AUTO_INCREMENT,
  `bucket_value` text,
  `bucket_description` text,
  PRIMARY KEY (`bucket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bucket_master`
--

LOCK TABLES `bucket_master` WRITE;
/*!40000 ALTER TABLE `bucket_master` DISABLE KEYS */;
INSERT INTO `bucket_master` VALUES (1,'New',NULL),(2,'Allocated',NULL),(3,'Draft',NULL),(4,'Completed',NULL),(5,'Ready For Allocation',NULL),(6,'Not Yet Suggested',NULL),(7,'Erroer Files',NULL),(8,'Rejected',NULL);
/*!40000 ALTER TABLE `bucket_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_master`
--

DROP TABLE IF EXISTS `client_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_master` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` text,
  `client_short_name` varchar(50) DEFAULT NULL,
  `client_address_1` varchar(50) DEFAULT NULL,
  `client_address_2` varchar(50) DEFAULT NULL,
  `client_city` varchar(50) DEFAULT NULL,
  `client_state` varchar(50) DEFAULT NULL,
  `client_zipcode` varchar(15) DEFAULT NULL,
  `client_country` varchar(30) DEFAULT NULL,
  `client_timezone` varchar(30) DEFAULT NULL,
  `client_primary_phone_number` varchar(20) DEFAULT NULL,
  `client_secondary_phone_number` varchar(20) DEFAULT NULL,
  `client_email_id` varchar(50) DEFAULT NULL,
  `client_alternate_email_id` varchar(50) DEFAULT NULL,
  `client_fax_number` varchar(20) DEFAULT NULL,
  `client_pager_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_master`
--

LOCK TABLES `client_master` WRITE;
/*!40000 ALTER TABLE `client_master` DISABLE KEYS */;
INSERT INTO `client_master` VALUES (1,'Sun Gard System','SGS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `client_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_state_map`
--

DROP TABLE IF EXISTS `country_state_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_state_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(50) DEFAULT NULL,
  `state_name` varchar(50) DEFAULT NULL,
  `city_name` varchar(50) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `state_name` (`state_name`,`city_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_state_map`
--

LOCK TABLES `country_state_map` WRITE;
/*!40000 ALTER TABLE `country_state_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `country_state_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_master`
--

DROP TABLE IF EXISTS `document_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` varchar(50) NOT NULL,
  `document_name` varchar(50) NOT NULL,
  `document_path` varchar(150) NOT NULL,
  `document_rejection_comment` varchar(100) DEFAULT NULL,
  `document_rejection_reason` varchar(50) DEFAULT NULL,
  `document_current_status` varchar(50) NOT NULL,
  `document_current_status_id` int(5) NOT NULL,
  `document_recived_datetime` datetime DEFAULT NULL,
  `document_parsed_datetime` datetime DEFAULT NULL,
  `document_assigned_datetime` datetime DEFAULT NULL,
  `document_start_datetime` datetime DEFAULT NULL,
  `document_rejected_datetime` datetime DEFAULT NULL,
  `document_end_datetime` datetime DEFAULT NULL,
  `document_contents` text,
  `document_suggested_codes` varchar(50) DEFAULT NULL,
  `document_accepted_codes` varchar(50) DEFAULT NULL,
  `document_rejected_codes` varchar(50) DEFAULT NULL,
  `document_final_codes` varchar(50) DEFAULT NULL,
  `document_locked` varchar(1) DEFAULT NULL,
  `document_locked_by` varchar(50) DEFAULT NULL,
  `document_locked_by_id` int(11) DEFAULT NULL,
  `document_assigned_id` varchar(50) DEFAULT NULL,
  `document_assigned_name` varchar(50) DEFAULT NULL,
  `document_assignee_id` varchar(50) NOT NULL,
  `document_assignee_name` varchar(50) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_name` varchar(50) DEFAULT NULL,
  `physician_name` varchar(50) DEFAULT NULL,
  `report_type` varchar(50) DEFAULT NULL,
  `code_suggested_flag` varchar(1) DEFAULT 'N',
  `updated_by` varchar(50) DEFAULT NULL,
  `updated_by_id` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_master`
--

LOCK TABLES `document_master` WRITE;
/*!40000 ALTER TABLE `document_master` DISABLE KEYS */;
INSERT INTO `document_master` VALUES (1,'4.PDF','4.PDF','/home/local/EZDI/vishal.d/docparser/input/4.PDF',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nHPI \n\nDate/Time Seen by Provider 01/31/16 2350 \n\nPCP: none \n\nComplaint: nausea, vomiting \n\nSource of history: patient \n\nTiming - onset: hours (3) \n\nLocation of pain: upper abdomen \n\nRadiation of pain: none \n\nAssociated With: \n\nReports abdominal pain \n\nContext - history: chronic constipation \n\nContext - ingestion: \n\nDenies infectious exposure, Denies spoiled food \n\nExacerbated by: food, liquids \n\nAdditional hpi notes: \n\n24 yoM w/ hx of constipation presents to the ED c/o n/v with associated diffuse upper abd pain that \n\nbegan 3 hours PTA. Pt reports that he stopped smoking marijuana in December, and denies sick contacts \n\nor suspect food as his girlfriend ate the same food and currently has no sx. Pt reports chills but denies \n\ndiarrhea, urinary sx or any other associated sx. Pt also reports \" a more watery ejaculate than normal for \n\nthe past month.\" No radiation of pain, aggravating or relieving factors reported. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0351 \n\nReview of Systems \n\nConstitutional: \n\nReports: chills. \n\nRespiratory: \n\nDENIES: SOB. \n\nCardiovascular: \n\n\n\nDENIES: chest pain. \n\nGastrointestinal: \n\nnausea, vomiting, abdominal pain. DENIES: diarrhea, melena, hematochezia. \n\nGenitourinary: \n\nPage 1 of 7 \n\n \n\nDENIES: dysuria, hematuria. \n\nAll systems reviewed & negative except as marked. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0005 \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes (triage summary) \n\nAllergies: \n\nCoded Allergies: \n\nCephalosporins (UNKNOWN 01/31/16) cefaclor (From CECLOR) (UNKNOWN 01/31/16) loracarbef (From \n\nLORABID) (UNKNOWN 01/31/16) \n\nSocial history: \n\nDenies: alcohol, drugs, smoker. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0001 \n\nPhys Exam-Nausea/Vomit/Diarr \n\nGeneral: alert, well developed, well nourished \n\nHead/Eyes: normocephalic, PERRLA, EOMI, normal conjunctiva/sclera \n\nENT: moist mucous membranes \n\nNeck: supple/no men ingismus, full range of motion \n\nRespiratory/Chest: no distress (resp), normal breath sounds \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, no guarding/rebound, tenderness (upper abdomen), negative murphy\'s sign \n\n\n\nSkin: no rash, warm, dry \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits \n\nClinical Impression: \n\nPrimary Impression: Nausea & vomiting \n\nSecondary Impressions: Dyspepsia \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(2,'10.PDF','10.PDF','/home/local/EZDI/vishal.d/docparser/input/10.PDF',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nComplaint: suicidal ideation, depressed \n\nSource of history: patient \n\nTiming - onset: days (several) \n\nTiming - duration: since onset \n\nSeverity onset: moderate \n\nSeverity current: moderate \n\nAssociated With: \n\nReports alcohol use, Reports anxiety, Reports delusions, Reports depression, Denies drug use, Denies \n\nviolence \n\nContext - history: bipolar disorder, prior suicide attempt(s), schizophrenia (possible, patient denies) \n\nExacerbated by: alcohol use, drug abuse \n\nRelieved by: prescription medication \n\nPt. reports/records indicate: recent testing, recent doctor visit, similar symptoms previous \n\nAdditional hpi notes: \n\nPatient is a 42-year-old male with history of bipolar, possible schizophrenia per previous notes, \n\npolysubstance abuse and alcoholism who presents the emergency department complaining of several \n\ndays of worsening suicidal ideation. Patient states he plans to hang himself in his hotel room. Patient has \n\nbeen seen several times this year for similar symptoms. \n\nPatient states that when he is taking his Seroquel regularly his symptoms are significantly improved but \n\nhe ran out several days ago and has been medicating himself with alcohol and marijuana. Patient \n\nattributes this to his worsening SI, depression along with \"seeing demons\". Patient denies that he is \n\ndone anything to harm himself today. Patient is in the emergency department because \"I want help\". \n\nPatient admits to last alcoholic beverage 6 hours ago. Patient denies any drug use today. Patient reports \n\nthat he has attempted suicide at least 20 times in the past. Patient states he has a primary care \n\nphysician but missed his appointment and is unable to tell me who that appointment was with. \n\nRisk Strat-Psychiatric Illness \n\nSuicide risk factors: Previous attempt, Prior psych admission \n\nReview of Systems \n\nConstitutional: \n\n\n\nDenies: fever, chills, generalized weakness, fatigue, recent wt loss. \n\nSkin: \n\nDENIES: rash, redness, swelling. \n\nEyes: \n\nDENIES: redness, discharge, visual loss! blurred. \n\nENT: \n\nDENIES: earache, nasal congestion, sore throat, neck pain. \n\nRespiratory: \n\nDENIES: SOB, cough. \n\nCardiovascular: \n\nDENIES: chest pain, dyspnea on exertion, palpitations. \n\nGastrointestinal: \n\nDENIES: nausea, vomiting, abdominal pain. \n\nGenitourinary: \n\nDENIES: dysuria, frequency. \n\nMusculoskeletal: \n\nDENIES: neck pain, thoracic pain, lumbar pain, myalgias, extremity pain, joint pain. \n\nNeuro: \n\nDenies: headache, dizziness, lightheaded. \n\nPsych: \n\nstress, anxiety, depression, suicidal ideation, visual hallucination. DENIES: homicidal ideation, insomnia. \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes \n\nPast Medical History: \n\nReports: none. \n\n\n\nAdditional Medical History: \n\nPMH: \n\nasthma anxiety depression bipolar I mania paranoid schizophrenia \n\nHome medications: \n\nReported Medications \n\nLORazepam (ATIVAN) (Unknown Dose) PO BID \n\nQUEtiapine (SEROquel) 50 MG PO BEDTIME \n\nMIRTAZAPINE (REMERON) 15 MG PO BEDTIME \n\nAllergies: \n\nCoded Allergies: \n\nhaloperidol (From HALDOL) (Intermediate, tongue swelling 01/01/16) penicillin (swelling 01/01/16) \n\nAdditional Surgical History: \n\nPS H: \n\nleft knee \n\nPast Family History: \n\nFATHER \n\nFamily History: Unremarkable, Onset: Unknown. \n\nSmoking status 13 years/older: Current every day smoker \n\nSocial history: \n\nReports: alcohol, drugs (Marijuana). \n\nOccupation: \n\nunemployed \n\nPhys Exam-Psychiatric Illness \n\nGeneral: alert, oriented X 3, cooperative, physical appearance (disheveled) \n\nHead/Eyes: atraumatic, normocephalic, PERRL, EOMI, normal conjunctiva-sclera, no signs of skull injury \n\n\n\nENT: moist mucous membranes \n\nNeck: full range of motion, no masses or swelling \n\nRespiratory/Chest: no respiratory distress, breath sounds normal \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, non-tender, no distention, normal bowel sounds \n\nExtremities: \n\nAssessment: non-tender, no swelling, normal gait \n\nBack: normal inspection \n\nSkin: warm, dry \n\nLymphatic: neck normal \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits, no sensory deficits, normal gait \n\nPsychiatric: anxious, suicidal ideation, visual hallucinations, delusions \n\nClinical Impression: \n\nPrimary Impression: Suicidal ideation \n\nSecondary Impressions: Alcohol intoxication, Bipolar disorder \n\nplan \n\nI evaluated and participated in the management of the patient. My co-signature indicates that I have \n\nreviewed this chart and I agree with the findings and plan of care as documented. My personal H&P \n\nfindings include: \n\nSuicidal ideation. Etoh. \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(3,'7.pdf','7.pdf','/home/local/EZDI/vishal.d/docparser/input/7.pdf',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nComplaint: suicidal ideation, depressed \n\nSource of history: patient \n\nTiming - onset: days (several) \n\nTiming - duration: since onset \n\nSeverity onset: moderate \n\nSeverity current: moderate \n\nAssociated With: \n\nReports alcohol use, Reports anxiety, Reports delusions, Reports depression, Denies drug use, Denies \n\nviolence \n\nContext - history: bipolar disorder, prior suicide attempt(s), schizophrenia (possible, patient denies) \n\nExacerbated by: alcohol use, drug abuse \n\nRelieved by: prescription medication \n\nPt. reports/records indicate: recent testing, recent doctor visit, similar symptoms previous \n\nAdditional hpi notes: \n\nPatient is a 42-year-old male with history of bipolar, possible schizophrenia per previous notes, \n\npolysubstance abuse and alcoholism who presents the emergency department complaining of several \n\ndays of worsening suicidal ideation. Patient states he plans to hang himself in his hotel room. Patient has \n\nbeen seen several times this year for similar symptoms. \n\nPatient states that when he is taking his Seroquel regularly his symptoms are significantly improved but \n\nhe ran out several days ago and has been medicating himself with alcohol and marijuana. Patient \n\nattributes this to his worsening SI, depression along with \"seeing demons\". Patient denies that he is \n\ndone anything to harm himself today. Patient is in the emergency department because \"I want help\". \n\nPatient admits to last alcoholic beverage 6 hours ago. Patient denies any drug use today. Patient reports \n\nthat he has attempted suicide at least 20 times in the past. Patient states he has a primary care \n\nphysician but missed his appointment and is unable to tell me who that appointment was with. \n\nRisk Strat-Psychiatric Illness \n\nSuicide risk factors: Previous attempt, Prior psych admission \n\nReview of Systems \n\nConstitutional: \n\n\n\nDenies: fever, chills, generalized weakness, fatigue, recent wt loss. \n\nSkin: \n\nDENIES: rash, redness, swelling. \n\nEyes: \n\nDENIES: redness, discharge, visual loss! blurred. \n\nENT: \n\nDENIES: earache, nasal congestion, sore throat, neck pain. \n\nRespiratory: \n\nDENIES: SOB, cough. \n\nCardiovascular: \n\nDENIES: chest pain, dyspnea on exertion, palpitations. \n\nGastrointestinal: \n\nDENIES: nausea, vomiting, abdominal pain. \n\nGenitourinary: \n\nDENIES: dysuria, frequency. \n\nMusculoskeletal: \n\nDENIES: neck pain, thoracic pain, lumbar pain, myalgias, extremity pain, joint pain. \n\nNeuro: \n\nDenies: headache, dizziness, lightheaded. \n\nPsych: \n\nstress, anxiety, depression, suicidal ideation, visual hallucination. DENIES: homicidal ideation, insomnia. \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes \n\nPast Medical History: \n\nReports: none. \n\n\n\nAdditional Medical History: \n\nPMH: \n\nasthma anxiety depression bipolar I mania paranoid schizophrenia \n\nHome medications: \n\nReported Medications \n\nLORazepam (ATIVAN) (Unknown Dose) PO BID \n\nQUEtiapine (SEROquel) 50 MG PO BEDTIME \n\nMIRTAZAPINE (REMERON) 15 MG PO BEDTIME \n\nAllergies: \n\nCoded Allergies: \n\nhaloperidol (From HALDOL) (Intermediate, tongue swelling 01/01/16) penicillin (swelling 01/01/16) \n\nAdditional Surgical History: \n\nPS H: \n\nleft knee \n\nPast Family History: \n\nFATHER \n\nFamily History: Unremarkable, Onset: Unknown. \n\nSmoking status 13 years/older: Current every day smoker \n\nSocial history: \n\nReports: alcohol, drugs (Marijuana). \n\nOccupation: \n\nunemployed \n\nPhys Exam-Psychiatric Illness \n\nGeneral: alert, oriented X 3, cooperative, physical appearance (disheveled) \n\nHead/Eyes: atraumatic, normocephalic, PERRL, EOMI, normal conjunctiva-sclera, no signs of skull injury \n\n\n\nENT: moist mucous membranes \n\nNeck: full range of motion, no masses or swelling \n\nRespiratory/Chest: no respiratory distress, breath sounds normal \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, non-tender, no distention, normal bowel sounds \n\nExtremities: \n\nAssessment: non-tender, no swelling, normal gait \n\nBack: normal inspection \n\nSkin: warm, dry \n\nLymphatic: neck normal \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits, no sensory deficits, normal gait \n\nPsychiatric: anxious, suicidal ideation, visual hallucinations, delusions \n\nClinical Impression: \n\nPrimary Impression: Suicidal ideation \n\nSecondary Impressions: Alcohol intoxication, Bipolar disorder \n\nplan \n\nI evaluated and participated in the management of the patient. My co-signature indicates that I have \n\nreviewed this chart and I agree with the findings and plan of care as documented. My personal H&P \n\nfindings include: \n\nSuicidal ideation. Etoh. \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(4,'2.pdf','2.pdf','/home/local/EZDI/vishal.d/docparser/input/2.pdf',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nComplaint: suicidal ideation, depressed \n\nSource of history: patient \n\nTiming - onset: days (several) \n\nTiming - duration: since onset \n\nSeverity onset: moderate \n\nSeverity current: moderate \n\nAssociated With: \n\nReports alcohol use, Reports anxiety, Reports delusions, Reports depression, Denies drug use, Denies \n\nviolence \n\nContext - history: bipolar disorder, prior suicide attempt(s), schizophrenia (possible, patient denies) \n\nExacerbated by: alcohol use, drug abuse \n\nRelieved by: prescription medication \n\nPt. reports/records indicate: recent testing, recent doctor visit, similar symptoms previous \n\nAdditional hpi notes: \n\nPatient is a 42-year-old male with history of bipolar, possible schizophrenia per previous notes, \n\npolysubstance abuse and alcoholism who presents the emergency department complaining of several \n\ndays of worsening suicidal ideation. Patient states he plans to hang himself in his hotel room. Patient has \n\nbeen seen several times this year for similar symptoms. \n\nPatient states that when he is taking his Seroquel regularly his symptoms are significantly improved but \n\nhe ran out several days ago and has been medicating himself with alcohol and marijuana. Patient \n\nattributes this to his worsening SI, depression along with \"seeing demons\". Patient denies that he is \n\ndone anything to harm himself today. Patient is in the emergency department because \"I want help\". \n\nPatient admits to last alcoholic beverage 6 hours ago. Patient denies any drug use today. Patient reports \n\nthat he has attempted suicide at least 20 times in the past. Patient states he has a primary care \n\nphysician but missed his appointment and is unable to tell me who that appointment was with. \n\nRisk Strat-Psychiatric Illness \n\nSuicide risk factors: Previous attempt, Prior psych admission \n\nReview of Systems \n\nConstitutional: \n\n\n\nDenies: fever, chills, generalized weakness, fatigue, recent wt loss. \n\nSkin: \n\nDENIES: rash, redness, swelling. \n\nEyes: \n\nDENIES: redness, discharge, visual loss! blurred. \n\nENT: \n\nDENIES: earache, nasal congestion, sore throat, neck pain. \n\nRespiratory: \n\nDENIES: SOB, cough. \n\nCardiovascular: \n\nDENIES: chest pain, dyspnea on exertion, palpitations. \n\nGastrointestinal: \n\nDENIES: nausea, vomiting, abdominal pain. \n\nGenitourinary: \n\nDENIES: dysuria, frequency. \n\nMusculoskeletal: \n\nDENIES: neck pain, thoracic pain, lumbar pain, myalgias, extremity pain, joint pain. \n\nNeuro: \n\nDenies: headache, dizziness, lightheaded. \n\nPsych: \n\nstress, anxiety, depression, suicidal ideation, visual hallucination. DENIES: homicidal ideation, insomnia. \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes \n\nPast Medical History: \n\nReports: none. \n\n\n\nAdditional Medical History: \n\nPMH: \n\nasthma anxiety depression bipolar I mania paranoid schizophrenia \n\nHome medications: \n\nReported Medications \n\nLORazepam (ATIVAN) (Unknown Dose) PO BID \n\nQUEtiapine (SEROquel) 50 MG PO BEDTIME \n\nMIRTAZAPINE (REMERON) 15 MG PO BEDTIME \n\nAllergies: \n\nCoded Allergies: \n\nhaloperidol (From HALDOL) (Intermediate, tongue swelling 01/01/16) penicillin (swelling 01/01/16) \n\nAdditional Surgical History: \n\nPS H: \n\nleft knee \n\nPast Family History: \n\nFATHER \n\nFamily History: Unremarkable, Onset: Unknown. \n\nSmoking status 13 years/older: Current every day smoker \n\nSocial history: \n\nReports: alcohol, drugs (Marijuana). \n\nOccupation: \n\nunemployed \n\nPhys Exam-Psychiatric Illness \n\nGeneral: alert, oriented X 3, cooperative, physical appearance (disheveled) \n\nHead/Eyes: atraumatic, normocephalic, PERRL, EOMI, normal conjunctiva-sclera, no signs of skull injury \n\n\n\nENT: moist mucous membranes \n\nNeck: full range of motion, no masses or swelling \n\nRespiratory/Chest: no respiratory distress, breath sounds normal \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, non-tender, no distention, normal bowel sounds \n\nExtremities: \n\nAssessment: non-tender, no swelling, normal gait \n\nBack: normal inspection \n\nSkin: warm, dry \n\nLymphatic: neck normal \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits, no sensory deficits, normal gait \n\nPsychiatric: anxious, suicidal ideation, visual hallucinations, delusions \n\nClinical Impression: \n\nPrimary Impression: Suicidal ideation \n\nSecondary Impressions: Alcohol intoxication, Bipolar disorder \n\nplan \n\nI evaluated and participated in the management of the patient. My co-signature indicates that I have \n\nreviewed this chart and I agree with the findings and plan of care as documented. My personal H&P \n\nfindings include: \n\nSuicidal ideation. Etoh. \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(5,'1.pdf','1.pdf','/home/local/EZDI/vishal.d/docparser/input/1.pdf',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nHPI \n\nDate/Time Seen by Provider 01/31/16 2350 \n\nPCP: none \n\nComplaint: nausea, vomiting \n\nSource of history: patient \n\nTiming - onset: hours (3) \n\nLocation of pain: upper abdomen \n\nRadiation of pain: none \n\nAssociated With: \n\nReports abdominal pain \n\nContext - history: chronic constipation \n\nContext - ingestion: \n\nDenies infectious exposure, Denies spoiled food \n\nExacerbated by: food, liquids \n\nAdditional hpi notes: \n\n24 yoM w/ hx of constipation presents to the ED c/o n/v with associated diffuse upper abd pain that \n\nbegan 3 hours PTA. Pt reports that he stopped smoking marijuana in December, and denies sick contacts \n\nor suspect food as his girlfriend ate the same food and currently has no sx. Pt reports chills but denies \n\ndiarrhea, urinary sx or any other associated sx. Pt also reports \" a more watery ejaculate than normal for \n\nthe past month.\" No radiation of pain, aggravating or relieving factors reported. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0351 \n\nReview of Systems \n\nConstitutional: \n\nReports: chills. \n\nRespiratory: \n\nDENIES: SOB. \n\nCardiovascular: \n\n\n\nDENIES: chest pain. \n\nGastrointestinal: \n\nnausea, vomiting, abdominal pain. DENIES: diarrhea, melena, hematochezia. \n\nGenitourinary: \n\nPage 1 of 7 \n\n \n\nDENIES: dysuria, hematuria. \n\nAll systems reviewed & negative except as marked. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0005 \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes (triage summary) \n\nAllergies: \n\nCoded Allergies: \n\nCephalosporins (UNKNOWN 01/31/16) cefaclor (From CECLOR) (UNKNOWN 01/31/16) loracarbef (From \n\nLORABID) (UNKNOWN 01/31/16) \n\nSocial history: \n\nDenies: alcohol, drugs, smoker. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0001 \n\nPhys Exam-Nausea/Vomit/Diarr \n\nGeneral: alert, well developed, well nourished \n\nHead/Eyes: normocephalic, PERRLA, EOMI, normal conjunctiva/sclera \n\nENT: moist mucous membranes \n\nNeck: supple/no men ingismus, full range of motion \n\nRespiratory/Chest: no distress (resp), normal breath sounds \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, no guarding/rebound, tenderness (upper abdomen), negative murphy\'s sign \n\n\n\nSkin: no rash, warm, dry \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits \n\nClinical Impression: \n\nPrimary Impression: Nausea & vomiting \n\nSecondary Impressions: Dyspepsia \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(6,'9.PDF','9.PDF','/home/local/EZDI/vishal.d/docparser/input/9.PDF',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nComplaint: suicidal ideation, depressed \n\nSource of history: patient \n\nTiming - onset: days (several) \n\nTiming - duration: since onset \n\nSeverity onset: moderate \n\nSeverity current: moderate \n\nAssociated With: \n\nReports alcohol use, Reports anxiety, Reports delusions, Reports depression, Denies drug use, Denies \n\nviolence \n\nContext - history: bipolar disorder, prior suicide attempt(s), schizophrenia (possible, patient denies) \n\nExacerbated by: alcohol use, drug abuse \n\nRelieved by: prescription medication \n\nPt. reports/records indicate: recent testing, recent doctor visit, similar symptoms previous \n\nAdditional hpi notes: \n\nPatient is a 42-year-old male with history of bipolar, possible schizophrenia per previous notes, \n\npolysubstance abuse and alcoholism who presents the emergency department complaining of several \n\ndays of worsening suicidal ideation. Patient states he plans to hang himself in his hotel room. Patient has \n\nbeen seen several times this year for similar symptoms. \n\nPatient states that when he is taking his Seroquel regularly his symptoms are significantly improved but \n\nhe ran out several days ago and has been medicating himself with alcohol and marijuana. Patient \n\nattributes this to his worsening SI, depression along with \"seeing demons\". Patient denies that he is \n\ndone anything to harm himself today. Patient is in the emergency department because \"I want help\". \n\nPatient admits to last alcoholic beverage 6 hours ago. Patient denies any drug use today. Patient reports \n\nthat he has attempted suicide at least 20 times in the past. Patient states he has a primary care \n\nphysician but missed his appointment and is unable to tell me who that appointment was with. \n\nRisk Strat-Psychiatric Illness \n\nSuicide risk factors: Previous attempt, Prior psych admission \n\nReview of Systems \n\nConstitutional: \n\n\n\nDenies: fever, chills, generalized weakness, fatigue, recent wt loss. \n\nSkin: \n\nDENIES: rash, redness, swelling. \n\nEyes: \n\nDENIES: redness, discharge, visual loss! blurred. \n\nENT: \n\nDENIES: earache, nasal congestion, sore throat, neck pain. \n\nRespiratory: \n\nDENIES: SOB, cough. \n\nCardiovascular: \n\nDENIES: chest pain, dyspnea on exertion, palpitations. \n\nGastrointestinal: \n\nDENIES: nausea, vomiting, abdominal pain. \n\nGenitourinary: \n\nDENIES: dysuria, frequency. \n\nMusculoskeletal: \n\nDENIES: neck pain, thoracic pain, lumbar pain, myalgias, extremity pain, joint pain. \n\nNeuro: \n\nDenies: headache, dizziness, lightheaded. \n\nPsych: \n\nstress, anxiety, depression, suicidal ideation, visual hallucination. DENIES: homicidal ideation, insomnia. \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes \n\nPast Medical History: \n\nReports: none. \n\n\n\nAdditional Medical History: \n\nPMH: \n\nasthma anxiety depression bipolar I mania paranoid schizophrenia \n\nHome medications: \n\nReported Medications \n\nLORazepam (ATIVAN) (Unknown Dose) PO BID \n\nQUEtiapine (SEROquel) 50 MG PO BEDTIME \n\nMIRTAZAPINE (REMERON) 15 MG PO BEDTIME \n\nAllergies: \n\nCoded Allergies: \n\nhaloperidol (From HALDOL) (Intermediate, tongue swelling 01/01/16) penicillin (swelling 01/01/16) \n\nAdditional Surgical History: \n\nPS H: \n\nleft knee \n\nPast Family History: \n\nFATHER \n\nFamily History: Unremarkable, Onset: Unknown. \n\nSmoking status 13 years/older: Current every day smoker \n\nSocial history: \n\nReports: alcohol, drugs (Marijuana). \n\nOccupation: \n\nunemployed \n\nPhys Exam-Psychiatric Illness \n\nGeneral: alert, oriented X 3, cooperative, physical appearance (disheveled) \n\nHead/Eyes: atraumatic, normocephalic, PERRL, EOMI, normal conjunctiva-sclera, no signs of skull injury \n\n\n\nENT: moist mucous membranes \n\nNeck: full range of motion, no masses or swelling \n\nRespiratory/Chest: no respiratory distress, breath sounds normal \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, non-tender, no distention, normal bowel sounds \n\nExtremities: \n\nAssessment: non-tender, no swelling, normal gait \n\nBack: normal inspection \n\nSkin: warm, dry \n\nLymphatic: neck normal \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits, no sensory deficits, normal gait \n\nPsychiatric: anxious, suicidal ideation, visual hallucinations, delusions \n\nClinical Impression: \n\nPrimary Impression: Suicidal ideation \n\nSecondary Impressions: Alcohol intoxication, Bipolar disorder \n\nplan \n\nI evaluated and participated in the management of the patient. My co-signature indicates that I have \n\nreviewed this chart and I agree with the findings and plan of care as documented. My personal H&P \n\nfindings include: \n\nSuicidal ideation. Etoh. \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(7,'8.pdf','8.pdf','/home/local/EZDI/vishal.d/docparser/input/8.pdf',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nComplaint: suicidal ideation, depressed \n\nSource of history: patient \n\nTiming - onset: days (several) \n\nTiming - duration: since onset \n\nSeverity onset: moderate \n\nSeverity current: moderate \n\nAssociated With: \n\nReports alcohol use, Reports anxiety, Reports delusions, Reports depression, Denies drug use, Denies \n\nviolence \n\nContext - history: bipolar disorder, prior suicide attempt(s), schizophrenia (possible, patient denies) \n\nExacerbated by: alcohol use, drug abuse \n\nRelieved by: prescription medication \n\nPt. reports/records indicate: recent testing, recent doctor visit, similar symptoms previous \n\nAdditional hpi notes: \n\nPatient is a 42-year-old male with history of bipolar, possible schizophrenia per previous notes, \n\npolysubstance abuse and alcoholism who presents the emergency department complaining of several \n\ndays of worsening suicidal ideation. Patient states he plans to hang himself in his hotel room. Patient has \n\nbeen seen several times this year for similar symptoms. \n\nPatient states that when he is taking his Seroquel regularly his symptoms are significantly improved but \n\nhe ran out several days ago and has been medicating himself with alcohol and marijuana. Patient \n\nattributes this to his worsening SI, depression along with \"seeing demons\". Patient denies that he is \n\ndone anything to harm himself today. Patient is in the emergency department because \"I want help\". \n\nPatient admits to last alcoholic beverage 6 hours ago. Patient denies any drug use today. Patient reports \n\nthat he has attempted suicide at least 20 times in the past. Patient states he has a primary care \n\nphysician but missed his appointment and is unable to tell me who that appointment was with. \n\nRisk Strat-Psychiatric Illness \n\nSuicide risk factors: Previous attempt, Prior psych admission \n\nReview of Systems \n\nConstitutional: \n\n\n\nDenies: fever, chills, generalized weakness, fatigue, recent wt loss. \n\nSkin: \n\nDENIES: rash, redness, swelling. \n\nEyes: \n\nDENIES: redness, discharge, visual loss! blurred. \n\nENT: \n\nDENIES: earache, nasal congestion, sore throat, neck pain. \n\nRespiratory: \n\nDENIES: SOB, cough. \n\nCardiovascular: \n\nDENIES: chest pain, dyspnea on exertion, palpitations. \n\nGastrointestinal: \n\nDENIES: nausea, vomiting, abdominal pain. \n\nGenitourinary: \n\nDENIES: dysuria, frequency. \n\nMusculoskeletal: \n\nDENIES: neck pain, thoracic pain, lumbar pain, myalgias, extremity pain, joint pain. \n\nNeuro: \n\nDenies: headache, dizziness, lightheaded. \n\nPsych: \n\nstress, anxiety, depression, suicidal ideation, visual hallucination. DENIES: homicidal ideation, insomnia. \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes \n\nPast Medical History: \n\nReports: none. \n\n\n\nAdditional Medical History: \n\nPMH: \n\nasthma anxiety depression bipolar I mania paranoid schizophrenia \n\nHome medications: \n\nReported Medications \n\nLORazepam (ATIVAN) (Unknown Dose) PO BID \n\nQUEtiapine (SEROquel) 50 MG PO BEDTIME \n\nMIRTAZAPINE (REMERON) 15 MG PO BEDTIME \n\nAllergies: \n\nCoded Allergies: \n\nhaloperidol (From HALDOL) (Intermediate, tongue swelling 01/01/16) penicillin (swelling 01/01/16) \n\nAdditional Surgical History: \n\nPS H: \n\nleft knee \n\nPast Family History: \n\nFATHER \n\nFamily History: Unremarkable, Onset: Unknown. \n\nSmoking status 13 years/older: Current every day smoker \n\nSocial history: \n\nReports: alcohol, drugs (Marijuana). \n\nOccupation: \n\nunemployed \n\nPhys Exam-Psychiatric Illness \n\nGeneral: alert, oriented X 3, cooperative, physical appearance (disheveled) \n\nHead/Eyes: atraumatic, normocephalic, PERRL, EOMI, normal conjunctiva-sclera, no signs of skull injury \n\n\n\nENT: moist mucous membranes \n\nNeck: full range of motion, no masses or swelling \n\nRespiratory/Chest: no respiratory distress, breath sounds normal \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, non-tender, no distention, normal bowel sounds \n\nExtremities: \n\nAssessment: non-tender, no swelling, normal gait \n\nBack: normal inspection \n\nSkin: warm, dry \n\nLymphatic: neck normal \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits, no sensory deficits, normal gait \n\nPsychiatric: anxious, suicidal ideation, visual hallucinations, delusions \n\nClinical Impression: \n\nPrimary Impression: Suicidal ideation \n\nSecondary Impressions: Alcohol intoxication, Bipolar disorder \n\nplan \n\nI evaluated and participated in the management of the patient. My co-signature indicates that I have \n\nreviewed this chart and I agree with the findings and plan of care as documented. My personal H&P \n\nfindings include: \n\nSuicidal ideation. Etoh. \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(8,'3.PDF','3.PDF','/home/local/EZDI/vishal.d/docparser/input/3.PDF',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nHPI \n\nDate/Time Seen by Provider 01/31/16 2350 \n\nPCP: none \n\nComplaint: nausea, vomiting \n\nSource of history: patient \n\nTiming - onset: hours (3) \n\nLocation of pain: upper abdomen \n\nRadiation of pain: none \n\nAssociated With: \n\nReports abdominal pain \n\nContext - history: chronic constipation \n\nContext - ingestion: \n\nDenies infectious exposure, Denies spoiled food \n\nExacerbated by: food, liquids \n\nAdditional hpi notes: \n\n24 yoM w/ hx of constipation presents to the ED c/o n/v with associated diffuse upper abd pain that \n\nbegan 3 hours PTA. Pt reports that he stopped smoking marijuana in December, and denies sick contacts \n\nor suspect food as his girlfriend ate the same food and currently has no sx. Pt reports chills but denies \n\ndiarrhea, urinary sx or any other associated sx. Pt also reports \" a more watery ejaculate than normal for \n\nthe past month.\" No radiation of pain, aggravating or relieving factors reported. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0351 \n\nReview of Systems \n\nConstitutional: \n\nReports: chills. \n\nRespiratory: \n\nDENIES: SOB. \n\nCardiovascular: \n\n\n\nDENIES: chest pain. \n\nGastrointestinal: \n\nnausea, vomiting, abdominal pain. DENIES: diarrhea, melena, hematochezia. \n\nGenitourinary: \n\nPage 1 of 7 \n\n \n\nDENIES: dysuria, hematuria. \n\nAll systems reviewed & negative except as marked. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0005 \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes (triage summary) \n\nAllergies: \n\nCoded Allergies: \n\nCephalosporins (UNKNOWN 01/31/16) cefaclor (From CECLOR) (UNKNOWN 01/31/16) loracarbef (From \n\nLORABID) (UNKNOWN 01/31/16) \n\nSocial history: \n\nDenies: alcohol, drugs, smoker. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0001 \n\nPhys Exam-Nausea/Vomit/Diarr \n\nGeneral: alert, well developed, well nourished \n\nHead/Eyes: normocephalic, PERRLA, EOMI, normal conjunctiva/sclera \n\nENT: moist mucous membranes \n\nNeck: supple/no men ingismus, full range of motion \n\nRespiratory/Chest: no distress (resp), normal breath sounds \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, no guarding/rebound, tenderness (upper abdomen), negative murphy\'s sign \n\n\n\nSkin: no rash, warm, dry \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits \n\nClinical Impression: \n\nPrimary Impression: Nausea & vomiting \n\nSecondary Impressions: Dyspepsia \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(9,'6.PDF','6.PDF','/home/local/EZDI/vishal.d/docparser/input/6.PDF',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nHPI \n\nDate/Time Seen by Provider 01/31/16 2350 \n\nPCP: none \n\nComplaint: nausea, vomiting \n\nSource of history: patient \n\nTiming - onset: hours (3) \n\nLocation of pain: upper abdomen \n\nRadiation of pain: none \n\nAssociated With: \n\nReports abdominal pain \n\nContext - history: chronic constipation \n\nContext - ingestion: \n\nDenies infectious exposure, Denies spoiled food \n\nExacerbated by: food, liquids \n\nAdditional hpi notes: \n\n24 yoM w/ hx of constipation presents to the ED c/o n/v with associated diffuse upper abd pain that \n\nbegan 3 hours PTA. Pt reports that he stopped smoking marijuana in December, and denies sick contacts \n\nor suspect food as his girlfriend ate the same food and currently has no sx. Pt reports chills but denies \n\ndiarrhea, urinary sx or any other associated sx. Pt also reports \" a more watery ejaculate than normal for \n\nthe past month.\" No radiation of pain, aggravating or relieving factors reported. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0351 \n\nReview of Systems \n\nConstitutional: \n\nReports: chills. \n\nRespiratory: \n\nDENIES: SOB. \n\nCardiovascular: \n\n\n\nDENIES: chest pain. \n\nGastrointestinal: \n\nnausea, vomiting, abdominal pain. DENIES: diarrhea, melena, hematochezia. \n\nGenitourinary: \n\nPage 1 of 7 \n\n \n\nDENIES: dysuria, hematuria. \n\nAll systems reviewed & negative except as marked. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0005 \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes (triage summary) \n\nAllergies: \n\nCoded Allergies: \n\nCephalosporins (UNKNOWN 01/31/16) cefaclor (From CECLOR) (UNKNOWN 01/31/16) loracarbef (From \n\nLORABID) (UNKNOWN 01/31/16) \n\nSocial history: \n\nDenies: alcohol, drugs, smoker. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0001 \n\nPhys Exam-Nausea/Vomit/Diarr \n\nGeneral: alert, well developed, well nourished \n\nHead/Eyes: normocephalic, PERRLA, EOMI, normal conjunctiva/sclera \n\nENT: moist mucous membranes \n\nNeck: supple/no men ingismus, full range of motion \n\nRespiratory/Chest: no distress (resp), normal breath sounds \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, no guarding/rebound, tenderness (upper abdomen), negative murphy\'s sign \n\n\n\nSkin: no rash, warm, dry \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits \n\nClinical Impression: \n\nPrimary Impression: Nausea & vomiting \n\nSecondary Impressions: Dyspepsia \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL),(10,'5.PDF','5.PDF','/home/local/EZDI/vishal.d/docparser/input/5.PDF',NULL,NULL,'',111,NULL,NULL,NULL,NULL,NULL,NULL,'\nHPI \n\nDate/Time Seen by Provider 01/31/16 2350 \n\nPCP: none \n\nComplaint: nausea, vomiting \n\nSource of history: patient \n\nTiming - onset: hours (3) \n\nLocation of pain: upper abdomen \n\nRadiation of pain: none \n\nAssociated With: \n\nReports abdominal pain \n\nContext - history: chronic constipation \n\nContext - ingestion: \n\nDenies infectious exposure, Denies spoiled food \n\nExacerbated by: food, liquids \n\nAdditional hpi notes: \n\n24 yoM w/ hx of constipation presents to the ED c/o n/v with associated diffuse upper abd pain that \n\nbegan 3 hours PTA. Pt reports that he stopped smoking marijuana in December, and denies sick contacts \n\nor suspect food as his girlfriend ate the same food and currently has no sx. Pt reports chills but denies \n\ndiarrhea, urinary sx or any other associated sx. Pt also reports \" a more watery ejaculate than normal for \n\nthe past month.\" No radiation of pain, aggravating or relieving factors reported. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0351 \n\nReview of Systems \n\nConstitutional: \n\nReports: chills. \n\nRespiratory: \n\nDENIES: SOB. \n\nCardiovascular: \n\n\n\nDENIES: chest pain. \n\nGastrointestinal: \n\nnausea, vomiting, abdominal pain. DENIES: diarrhea, melena, hematochezia. \n\nGenitourinary: \n\nPage 1 of 7 \n\n \n\nDENIES: dysuria, hematuria. \n\nAll systems reviewed & negative except as marked. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0005 \n\nHistory-Medical/Family/Social \n\n)( Reviewed nursing notes: Yes (triage summary) \n\nAllergies: \n\nCoded Allergies: \n\nCephalosporins (UNKNOWN 01/31/16) cefaclor (From CECLOR) (UNKNOWN 01/31/16) loracarbef (From \n\nLORABID) (UNKNOWN 01/31/16) \n\nSocial history: \n\nDenies: alcohol, drugs, smoker. \n\nPortions of this section were transcribed by Schlachtenhau,Brooke on 02/01/16 at 0001 \n\nPhys Exam-Nausea/Vomit/Diarr \n\nGeneral: alert, well developed, well nourished \n\nHead/Eyes: normocephalic, PERRLA, EOMI, normal conjunctiva/sclera \n\nENT: moist mucous membranes \n\nNeck: supple/no men ingismus, full range of motion \n\nRespiratory/Chest: no distress (resp), normal breath sounds \n\nCardiovascular: regular rate and rhythm, normal heart sounds \n\nAbdomen: soft, no guarding/rebound, tenderness (upper abdomen), negative murphy\'s sign \n\n\n\nSkin: no rash, warm, dry \n\nNeurologic: alert, oriented X 3, normal speech, no motor deficits \n\nClinical Impression: \n\nPrimary Impression: Nausea & vomiting \n\nSecondary Impressions: Dyspepsia \n\n\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'parser','parser',NULL,NULL,NULL,NULL,'N',NULL,NULL,NULL);
/*!40000 ALTER TABLE `document_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_log`
--

DROP TABLE IF EXISTS `event_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `action_type` varchar(50) DEFAULT NULL,
  `execution_date` datetime NOT NULL,
  `completion_date` datetime DEFAULT NULL,
  `session_id` varchar(1000) NOT NULL DEFAULT '',
  `ip_address` varchar(15) NOT NULL DEFAULT '',
  `is_active` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `event_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_log`
--

LOCK TABLES `event_log` WRITE;
/*!40000 ALTER TABLE `event_log` DISABLE KEYS */;
INSERT INTO `event_log` VALUES (1,1,'Login','2016-06-07 03:14:07',NULL,'asdafad15478','192.168.17.88',1),(2,2,'Logout','2016-06-07 05:14:07','2016-06-07 05:14:07','asdafad154','192.168.17.88',0);
/*!40000 ALTER TABLE `event_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `final_accepted_codes`
--

DROP TABLE IF EXISTS `final_accepted_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `final_accepted_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_id` varchar(20) NOT NULL,
  `code_evidance` varchar(250) DEFAULT NULL,
  `code_description` varchar(500) DEFAULT NULL,
  `documen_id` varchar(100) DEFAULT NULL,
  `updated_user_id` int(11) DEFAULT NULL,
  `updated_user_name` varchar(100) DEFAULT NULL,
  `updated_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_id` (`code_id`),
  KEY `updated_user_id` (`updated_user_id`),
  CONSTRAINT `final_accepted_codes_ibfk_1` FOREIGN KEY (`updated_user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `final_accepted_codes`
--

LOCK TABLES `final_accepted_codes` WRITE;
/*!40000 ALTER TABLE `final_accepted_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `final_accepted_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_master`
--

DROP TABLE IF EXISTS `patient_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` varchar(50) NOT NULL,
  `document_name` varchar(50) NOT NULL,
  `document_path` varchar(50) NOT NULL,
  `patient_name` varchar(50) DEFAULT NULL,
  `patient_mrn` varchar(50) DEFAULT NULL,
  `patient_ssn` varchar(50) DEFAULT NULL,
  `patient_accountno` varchar(50) DEFAULT NULL,
  `patient_dob` datetime DEFAULT NULL,
  `patient_gender` varchar(5) DEFAULT NULL,
  `patient_age` int(2) DEFAULT NULL,
  `patient_address` varchar(50) DEFAULT NULL,
  `patient_city` varchar(50) DEFAULT NULL,
  `patient_state` varchar(50) DEFAULT NULL,
  `patient_zip` varchar(50) DEFAULT NULL,
  `patient_location` varchar(50) DEFAULT NULL,
  `patient_admitdate` datetime DEFAULT NULL,
  `patient_dischargdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_master`
--

LOCK TABLES `patient_master` WRITE;
/*!40000 ALTER TABLE `patient_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rejection_reason_list`
--

DROP TABLE IF EXISTS `rejection_reason_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rejection_reason_list` (
  `rejection_reason_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `rejection_reason_list` text,
  PRIMARY KEY (`rejection_reason_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rejection_reason_list`
--

LOCK TABLES `rejection_reason_list` WRITE;
/*!40000 ALTER TABLE `rejection_reason_list` DISABLE KEYS */;
INSERT INTO `rejection_reason_list` VALUES (1,'Text Not Clear.'),(2,'Codes Not Suggested Properly.');
/*!40000 ALTER TABLE `rejection_reason_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_bucket_rightside_map`
--

DROP TABLE IF EXISTS `role_bucket_rightside_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_bucket_rightside_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `bucket_id` int(11) DEFAULT NULL,
  `bucket_value` varchar(50) DEFAULT NULL,
  `rightside_column_key` varchar(50) DEFAULT NULL,
  `rightside_column_name` varchar(50) DEFAULT NULL,
  `rightside_column_sequence` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bucket_id` (`bucket_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_bucket_rightside_map_ibfk_1` FOREIGN KEY (`bucket_id`) REFERENCES `bucket_master` (`bucket_id`),
  CONSTRAINT `role_bucket_rightside_map_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_bucket_rightside_map`
--

LOCK TABLES `role_bucket_rightside_map` WRITE;
/*!40000 ALTER TABLE `role_bucket_rightside_map` DISABLE KEYS */;
INSERT INTO `role_bucket_rightside_map` VALUES (1,1,'Allocater',1,'New',NULL,NULL,NULL),(2,1,'Allocater',2,'Allocated','File Assigned To : ','document_assigned_name',1),(3,1,'Allocater',2,'Allocated','File Assigned Date : ','document_assigned_datetime',2),(4,1,'Allocater',2,'Allocated','Work Started Date : ','document_start_datetime',3),(5,1,'Allocater',2,'Allocated','File Current Status : ','document_current_status',4),(6,1,'Allocater',4,'Completed','File Assigned To : ','document_assigned_name',1),(7,1,'Allocater',4,'Completed','File Assigned Date : ','document_assigned_datetime',2),(8,1,'Allocater',4,'Completed','Work Started Date : ','document_start_datetime',3),(9,1,'Allocater',4,'Completed','Work End Date : ','document_end_datetime',4),(10,2,'TL_Allocater',1,'New',NULL,NULL,NULL),(11,2,'TL_Allocater',2,'Allocated','File Assigned To : ','document_assigned_name',1),(12,2,'TL_Allocater',2,'Allocated','File Assigned Date : ','document_assigned_datetime',2),(13,2,'TL_Allocater',2,'Allocated','Work Started Date : ','document_start_datetime',3),(14,2,'TL_Allocater',2,'Allocated','File Current Status : ','document_current_status',4),(15,2,'TL_Allocater',4,'Completed','File Assigned To : ','document_assigned_name',1),(16,2,'TL_Allocater',4,'Completed','File Assigned Date : ','document_assigned_datetime',2),(17,2,'TL_Allocater',4,'Completed','Work Started Date : ','document_start_datetime',3),(18,2,'TL_Allocater',4,'Completed','Work End Date : ','document_end_datetime',4),(19,3,'TL_Allocater_Coder',1,'New','File Recived Date : ','document_recived_datetime',1),(20,3,'TL_Allocater_Coder',1,'New','File Assigned Date : ','document_assigned_datetime',2),(21,3,'TL_Allocater_Coder',1,'New','File Assigned By : ','document_assignee_name',3),(22,3,'TL_Allocater_Coder',2,'Allocated','File Assigned To : ','document_assigned_name',1),(23,3,'TL_Allocater_Coder',2,'Allocated','File Assigned Date : ','document_assigned_datetime',2),(24,3,'TL_Allocater_Coder',2,'Allocated','Work Started Date : ','document_start_datetime',3),(25,3,'TL_Allocater_Coder',2,'Allocated','File Current Status : ','document_current_status',4),(26,3,'TL_Allocater_Coder',3,'Draft','File Recived Date : ','document_recived_datetime',1),(27,3,'TL_Allocater_Coder',3,'Draft','File Assigned Date : ','document_assigned_datetime',2),(28,3,'TL_Allocater_Coder',3,'Draft','File Assigned By : ','document_assignee_name',3),(29,3,'TL_Allocater_Coder',3,'Draft','Work Started Date : ','document_start_datetime',4),(30,3,'TL_Allocater_Coder',4,'Completed','File Recived Date : ','document_recived_datetime',1),(31,3,'TL_Allocater_Coder',4,'Completed','File Assigned Date : ','document_assigned_datetime',2),(32,3,'TL_Allocater_Coder',4,'Completed','File Assigned By : ','document_assignee_name',3),(33,3,'TL_Allocater_Coder',4,'Completed','Work Started Date : ','document_start_datetime',4),(34,3,'TL_Allocater_Coder',4,'Completed','Work End Date : ','document_end_datetime',5),(35,4,'Coder',1,'New','File Recived Date : ','document_recived_datetime',1),(36,4,'Coder',1,'New','File Assigned Date : ','document_assigned_datetime',2),(37,4,'Coder',1,'New','File Assigned By : ','document_assignee_name',3),(38,4,'Coder',3,'Draft','File Recived Date : ','document_recived_datetime',1),(39,4,'Coder',3,'Draft','File Assigned Date : ','document_assigned_datetime',2),(40,4,'Coder',3,'Draft','File Assigned By : ','document_assignee_name',3),(41,4,'Coder',3,'Draft','Work Started Date : ','document_start_datetime',4),(42,4,'Coder',4,'Completed','File Recived Date : ','document_recived_datetime',1),(43,4,'Coder',4,'Completed','File Assigned Date : ','document_assigned_datetime',2),(44,4,'Coder',4,'Completed','File Assigned By : ','document_assignee_name',3),(45,4,'Coder',4,'Completed','Work Started Date : ','document_start_datetime',4),(46,4,'Coder',4,'Completed','Work End Date : ','document_end_datetime',5);
/*!40000 ALTER TABLE `role_bucket_rightside_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_bucket_status_map`
--

DROP TABLE IF EXISTS `role_bucket_status_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_bucket_status_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `role_name` text,
  `bucket_id` int(11) DEFAULT NULL,
  `bucket_value` varchar(50) DEFAULT NULL,
  `status_id` int(11) NOT NULL,
  `status_css_class` text,
  `status_value` text,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `bucket_id` (`bucket_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `role_bucket_status_map_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `role_bucket_status_map_ibfk_2` FOREIGN KEY (`bucket_id`) REFERENCES `bucket_master` (`bucket_id`),
  CONSTRAINT `role_bucket_status_map_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `status_master` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_bucket_status_map`
--

LOCK TABLES `role_bucket_status_map` WRITE;
/*!40000 ALTER TABLE `role_bucket_status_map` DISABLE KEYS */;
INSERT INTO `role_bucket_status_map` VALUES (1,1,'Allocater',5,'Ready For Allocation',111,'readyForAllocationBg','Ready For Allocation'),(2,1,'Allocater',6,'Not Yet Suggested',777,'rejectedBg','Not Yet Suggested'),(3,1,'Allocater',7,'Erroer Files',888,'rejectedBg','Erroer Files'),(4,1,'Allocater',8,'Rejected',222,'rejectedBg','Rejected'),(5,1,'Allocater',2,'Allocated',333,'allocateToTLBg','Allocat to TL'),(6,1,'Allocater',2,'Allocated',444,'allocateToCoderBg','Allocat to Coder'),(7,1,'Allocater',4,'Completed',666,'codingCompletedBg','Coding Completed'),(8,2,'TL_Allocater',2,'Allocated',444,'allocateToCoderBg','Allocat to Coder'),(9,2,'TL_Allocater',4,'Completed',666,'codingCompletedBg','Coding Completed'),(10,3,'TL_Allocater_Coder',1,'New',333,'allocateToTLBg','Allocat to TL'),(11,3,'TL_Allocater_Coder',3,'Draft',555,'draftBg','Draft'),(12,3,'TL_Allocater_Coder',4,'Completed',666,'codingCompletedBg','Coding Completed'),(13,4,'Coder',1,'New',444,'allocateToTLBg','Allocat to Coder'),(14,4,'Coder',3,'Draft',555,'draftBg','Draft'),(15,4,'Coder',4,'Completed',666,'codingCompletedBg','Coding Completed'),(16,2,'TL_Allocater',1,'New',333,'allocateToTLBg','Allocat to TL');
/*!40000 ALTER TABLE `role_bucket_status_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_master`
--

DROP TABLE IF EXISTS `role_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_master` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_master`
--

LOCK TABLES `role_master` WRITE;
/*!40000 ALTER TABLE `role_master` DISABLE KEYS */;
INSERT INTO `role_master` VALUES (1,'Allocater'),(2,'TL_Allocater'),(3,'TL_Allocater_Coder'),(4,'Coder'),(5,'Admin');
/*!40000 ALTER TABLE `role_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_default_settings`
--

DROP TABLE IF EXISTS `roles_default_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_default_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `role_name` text,
  `document_allocation` int(11) DEFAULT '0',
  `edit_document` int(11) DEFAULT '0',
  `reject_document` int(11) DEFAULT '0',
  `view_document` int(11) DEFAULT '0',
  `draft_document` int(11) DEFAULT '0',
  `complete_confirmation_message` int(11) DEFAULT '0',
  `draft_confirmation_message` int(11) DEFAULT '0',
  `continue_the_samebucket` int(11) DEFAULT '0',
  `view_report` int(11) DEFAULT '0',
  `add_user` int(11) DEFAULT '0',
  `delete_user` int(11) DEFAULT '0',
  `modified_user` int(11) DEFAULT '0',
  `add_rule` int(11) DEFAULT '0',
  `delete_rule` int(11) DEFAULT '0',
  `modified_rule` int(11) DEFAULT '0',
  `client_id` int(11) DEFAULT NULL,
  `client_name` text,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `roles_default_settings_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `roles_default_settings_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client_master` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_default_settings`
--

LOCK TABLES `roles_default_settings` WRITE;
/*!40000 ALTER TABLE `roles_default_settings` DISABLE KEYS */;
INSERT INTO `roles_default_settings` VALUES (1,1,'Allocater',1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,'SGS'),(2,2,'TL_Allocater',1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,'SGS'),(3,3,'TL_Allocater_Coder',1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,'SGS'),(4,4,'Coder',0,1,1,0,1,1,1,1,1,0,0,0,0,0,0,1,'SGS'),(5,5,'Admin',0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,'SGS');
/*!40000 ALTER TABLE `roles_default_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sort_list_master`
--

DROP TABLE IF EXISTS `sort_list_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sort_list_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sort_list` varchar(50) DEFAULT NULL,
  `default_flag` varchar(1) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sort_list_master`
--

LOCK TABLES `sort_list_master` WRITE;
/*!40000 ALTER TABLE `sort_list_master` DISABLE KEYS */;
INSERT INTO `sort_list_master` VALUES (1,'Recived Date','Y',1),(2,'Report Type','N',2),(3,'Assigned Date','N',3);
/*!40000 ALTER TABLE `sort_list_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_master`
--

DROP TABLE IF EXISTS `status_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_master` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_value` text,
  `status_description` text,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=889 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_master`
--

LOCK TABLES `status_master` WRITE;
/*!40000 ALTER TABLE `status_master` DISABLE KEYS */;
INSERT INTO `status_master` VALUES (111,'Ready For Allocation',NULL),(222,'Rejected',NULL),(333,'Allocat to TL',NULL),(444,'Allocat to Coder',NULL),(555,'Draft',NULL),(666,'Coding Completed',NULL),(777,'Not Yet Suggested',NULL),(888,'Erroer Files',NULL);
/*!40000 ALTER TABLE `status_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t1`
--

DROP TABLE IF EXISTS `t1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t1` (
  `name` varchar(50) DEFAULT NULL,
  `jdoc` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t1`
--

LOCK TABLES `t1` WRITE;
/*!40000 ALTER TABLE `t1` DISABLE KEYS */;
INSERT INTO `t1` VALUES ('Abhijit','{\"age\": \"37\", \"bday\": \"Monday\"}'),('Abhijit','[\"a1\", \"a2\"]');
/*!40000 ALTER TABLE `t1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tl_coder_map`
--

DROP TABLE IF EXISTS `tl_coder_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tl_coder_map` (
  `tl_id` int(11) NOT NULL AUTO_INCREMENT,
  `tl_firstname` varchar(50) DEFAULT NULL,
  `tl_middlename` varchar(50) DEFAULT NULL,
  `tl_lastname` varchar(50) DEFAULT NULL,
  `coder_id` int(11) NOT NULL DEFAULT '0',
  `coder_firstname` varchar(50) DEFAULT NULL,
  `coder_middlename` varchar(50) DEFAULT NULL,
  `coder_lastname` varchar(50) DEFAULT NULL,
  `tl_client_id` int(11) DEFAULT NULL,
  `tl_client_name` varchar(50) DEFAULT NULL,
  `coder_client_id` int(11) DEFAULT NULL,
  `coder_client_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`tl_id`,`coder_id`),
  KEY `coder_id` (`coder_id`),
  KEY `tl_client_id` (`tl_client_id`),
  KEY `coder_client_id` (`coder_client_id`),
  CONSTRAINT `tl_coder_map_ibfk_1` FOREIGN KEY (`tl_id`) REFERENCES `user_master` (`user_id`),
  CONSTRAINT `tl_coder_map_ibfk_2` FOREIGN KEY (`coder_id`) REFERENCES `user_master` (`user_id`),
  CONSTRAINT `tl_coder_map_ibfk_3` FOREIGN KEY (`tl_client_id`) REFERENCES `client_master` (`client_id`),
  CONSTRAINT `tl_coder_map_ibfk_4` FOREIGN KEY (`coder_client_id`) REFERENCES `client_master` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tl_coder_map`
--

LOCK TABLES `tl_coder_map` WRITE;
/*!40000 ALTER TABLE `tl_coder_map` DISABLE KEYS */;
INSERT INTO `tl_coder_map` VALUES (2,'Abhi',NULL,'Chikar',4,'@b#i',NULL,'Chikarrrr',1,'SGS',1,'SGS'),(3,'@bhi',NULL,'Chikr',4,'@b#i',NULL,'Chikarrrr',1,'SGS',1,'SGS');
/*!40000 ALTER TABLE `tl_coder_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authentication`
--

DROP TABLE IF EXISTS `user_authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authentication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_name` varchar(250) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `active_flag` int(11) DEFAULT '1',
  `delete_flag` int(11) DEFAULT '0',
  `password_expiry_date` date DEFAULT NULL,
  `password_reset_flag` tinyint(1) NOT NULL DEFAULT '1',
  `max_count_lock` tinyint(2) unsigned NOT NULL DEFAULT '5',
  `lock_expiration_time_in_min` smallint(5) unsigned NOT NULL DEFAULT '10',
  `last_login_attempt_datetime` datetime DEFAULT NULL,
  `password_expire_days` smallint(5) unsigned NOT NULL DEFAULT '90',
  `failed_count` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `user_state` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `user_authentication_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`),
  CONSTRAINT `user_authentication_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `user_authentication_ibfk_3` FOREIGN KEY (`client_id`) REFERENCES `client_master` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authentication`
--

LOCK TABLES `user_authentication` WRITE;
/*!40000 ALTER TABLE `user_authentication` DISABLE KEYS */;
INSERT INTO `user_authentication` VALUES (1,1,'abhi_allocater','*40A7975F7DB122ADC4B07F70585BEDC681DC8269',1,'SGS','abhijit.chikhalikar9028@gmail.com',1,'Allocater',1,0,'2016-12-31',0,5,10,NULL,90,0,'Active'),(2,2,'abhi_tl_allocater','*40A7975F7DB122ADC4B07F70585BEDC681DC8269',1,'SGS','chikhalikarabhijit@gmail.com',2,'TL_Allocater',1,0,'2016-12-31',0,5,10,NULL,90,0,'Active'),(3,3,'abhi_tl_allo_coder','*40A7975F7DB122ADC4B07F70585BEDC681DC8269',1,'SGS','chikhalikarabhijit@gmail.com',3,'TL_Allocater_Coder',1,0,'2016-12-31',0,5,10,NULL,90,0,'Active'),(4,4,'abhi_coder','*40A7975F7DB122ADC4B07F70585BEDC681DC8269',1,'SGS','chikhalikarabhijit@gmail.com',4,'Coder',1,0,'2016-12-31',0,5,10,NULL,90,0,'Active'),(5,5,'abhi_admin','*40A7975F7DB122ADC4B07F70585BEDC681DC8269',1,'SGS','abhijit.chikhalikar9028@gmail.com',5,'Admin',1,0,'2016-12-31',0,5,10,NULL,90,0,'Active'),(6,6,'abhi_rule_admin','*40A7975F7DB122ADC4B07F70585BEDC681DC8269',1,'SGS','abhijit.chikhalikar9028@gmail.com',5,'Admin',1,0,'2016-12-31',0,5,10,NULL,90,0,'Active');
/*!40000 ALTER TABLE `user_authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `first_name` varchar(65) DEFAULT NULL,
  `middle_name` varchar(65) DEFAULT NULL,
  `last_name` varchar(65) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `credential` varchar(10) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `alternate_email_id` varchar(50) DEFAULT NULL,
  `address_1` varchar(50) DEFAULT NULL,
  `address_2` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zipcode` varchar(15) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `timezone` varchar(30) DEFAULT NULL,
  `primary_phone_number` varchar(20) DEFAULT NULL,
  `secondary_phone_number` varchar(20) DEFAULT NULL,
  `fax_number` varchar(20) DEFAULT NULL,
  `pager_number` varchar(20) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `client_name` varchar(50) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `active_flag` int(11) DEFAULT '1',
  `delete_flag` int(11) DEFAULT '0',
  `updated_datetime` datetime DEFAULT NULL,
  `user_log_description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `user_master_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `user_master_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client_master` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (1,'abhi_allocater','Abhijit','B.','Chikhalikar','M','1988-11-05',NULL,'abhijit.chikhalikar9028@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',1,'Allocater',1,0,'2016-06-07 00:00:00','New User Adding'),(2,'abhi_tl_allocater','Abhi','B.','Chikar','M','1988-11-05',NULL,'chikhalikarabhijit@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',2,'TL_Allocater',1,0,'2016-06-07 00:00:00','New User Adding'),(3,'abhi_tl_allo_coder','@bhi','B.','Chikr','M','1988-11-05',NULL,'chikhalikarabhijit@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',3,'TL_Allocater_Coder',1,0,'2016-06-07 00:00:00','New User Adding'),(4,'abhi_coder','@b#i','B.','Chikarrr','M','1988-11-05',NULL,'chikhalikarabhijit@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',4,'Coder',1,0,'2016-06-07 00:00:00','New User Adding'),(5,'abhi_admin','Abhijit','B.','Chikarrr','M','1988-11-05',NULL,'abhijit.chikhalikar9028@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',5,'Admin',1,0,'2016-06-07 00:00:00','New User Adding'),(6,'abhi_rule_admin','Abhijit','B.','Chikarrr','M','1988-11-05',NULL,'abhijit.chikhalikar9028@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',5,'Admin',1,0,'2016-06-07 00:00:00','New User Adding');
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master_log`
--

DROP TABLE IF EXISTS `user_master_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master_log` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `first_name` varchar(65) DEFAULT NULL,
  `middle_name` varchar(65) DEFAULT NULL,
  `last_name` varchar(65) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `credential` varchar(10) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `alternate_email_id` varchar(50) DEFAULT NULL,
  `address_1` varchar(50) DEFAULT NULL,
  `address_2` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zipcode` varchar(15) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `timezone` varchar(30) DEFAULT NULL,
  `primary_phone_number` varchar(20) DEFAULT NULL,
  `secondary_phone_number` varchar(20) DEFAULT NULL,
  `fax_number` varchar(20) DEFAULT NULL,
  `pager_number` varchar(20) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `client_name` varchar(50) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `active_flag` int(11) DEFAULT '1',
  `delete_flag` int(11) DEFAULT '0',
  `updated_datetime` datetime NOT NULL,
  `user_log_description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`updated_datetime`),
  KEY `role_id` (`role_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `user_master_log_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `user_master_log_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client_master` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master_log`
--

LOCK TABLES `user_master_log` WRITE;
/*!40000 ALTER TABLE `user_master_log` DISABLE KEYS */;
INSERT INTO `user_master_log` VALUES (1,'abhi_allocater','Abhijit','B.','Chikhalikar','M','1988-11-05',NULL,'abhijit.chikhalikar9028@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',1,'Allocater',1,0,'2016-06-07 00:00:00','New User Adding'),(2,'abhi_tl_allocater','Abhi','B.','Chikar','M','1988-11-05',NULL,'chikhalikarabhijit@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',2,'TL_Allocater',1,0,'2016-06-07 00:00:00','New User Adding'),(3,'abhi_tl_allo_coder','@bhi','B.','Chikr','M','1988-11-05',NULL,'chikhalikarabhijit@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',3,'TL_Allocater_Coder',1,0,'2016-06-07 00:00:00','New User Adding'),(4,'abhi_coder','@b#i','B.','Chikarrr','M','1988-11-05',NULL,'chikhalikarabhijit@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',4,'Coder',1,0,'2016-06-07 00:00:00','New User Adding'),(5,'abhi_admin','Abhijit','B.','Chikarrr','M','1988-11-05',NULL,'abhijit.chikhalikar9028@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',5,'Admin',1,0,'2016-06-07 00:00:00','New User Adding'),(6,'abhi_rule_admin','Abhijit','B.','Chikarrr','M','1988-11-05',NULL,'abhijit.chikhalikar9028@gmail.com',NULL,NULL,NULL,'Pune','MH','412105',NULL,NULL,'9028553747',NULL,NULL,NULL,1,'SGS',5,'Admin',1,0,'2016-06-07 00:00:00','New User Adding');
/*!40000 ALTER TABLE `user_master_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permitions`
--

DROP TABLE IF EXISTS `user_permitions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permitions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role_name` text,
  `document_allocation` int(11) DEFAULT '0',
  `edit_document` int(11) DEFAULT '0',
  `reject_document` int(11) DEFAULT '0',
  `view_document` int(11) DEFAULT '0',
  `draft_document` int(11) DEFAULT '0',
  `complete_confirmation_message` int(11) DEFAULT '0',
  `draft_confirmation_message` int(11) DEFAULT '0',
  `continue_the_samebucket` int(11) DEFAULT '0',
  `view_report` int(11) DEFAULT '0',
  `add_user` int(11) DEFAULT '0',
  `delete_user` int(11) DEFAULT '0',
  `modified_user` int(11) DEFAULT '0',
  `add_rule` int(11) DEFAULT '0',
  `delete_rule` int(11) DEFAULT '0',
  `modified_rule` int(11) DEFAULT '0',
  `client_id` int(11) DEFAULT NULL,
  `client_name` text,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `client_id` (`client_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_permitions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `user_permitions_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client_master` (`client_id`),
  CONSTRAINT `user_permitions_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user_master` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permitions`
--

LOCK TABLES `user_permitions` WRITE;
/*!40000 ALTER TABLE `user_permitions` DISABLE KEYS */;
INSERT INTO `user_permitions` VALUES (1,1,'abhi_allocater',1,'Allocater',1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,'SGS'),(2,2,'abhi_tl_allocater',2,'TL_Allocater',1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,'SGS'),(3,3,'abhi_tl_allo_coder',3,'TL_Allocater_Coder',1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,'SGS'),(4,4,'abhi_coder',4,'Coder',0,1,1,0,1,1,1,1,1,0,0,0,0,0,0,1,'SGS'),(5,5,'abhi_admin',1,'Admin',0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,'SGS'),(6,6,'abhi_rule_admin',1,'Admin',0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,'SGS');
/*!40000 ALTER TABLE `user_permitions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-06 11:26:12
