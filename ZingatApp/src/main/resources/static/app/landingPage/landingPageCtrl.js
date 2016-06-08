angular.module('LandingPageController', [ ])
    .controller("landingPageCtrl",["$scope","$http", function($scope,$http){
        /*Show First bucket as selected*/
        $scope.selected = 0;
        
        /*Autosuggesstion for search*/
        $scope.loadTags = function(query) {
            return $http.get('json/tags.json');
        };
        
        /*Buckets and their corresponding counts to be displayed */
        $scope.listGroup = [{
            "bucketName":"New",
            "bucketCount":20
        },{
            "bucketName":"Assigned",
            "bucketCount":47
        },{
            "bucketName":"Completed",
            "bucketCount":10
        }];
        
        /*Actual File Content of selected file*/
        $scope.fileContent = "<p>VITAL SIGNS<br>Temp: 99.3<br>Pulse: 81<br>Resp: 10<br>BP: 126/90<br>Wt: 110 pounds<br>Pulse Ox: 99% on room air<br>Primary Care: &nbsp;Dr. Tasaddaq<br><br>CURRENT MEDICATIONS: &nbsp;<br>1. Sudafed.<br>2. Aleve.<br><br>ALLERGIES: &nbsp;Penicillin.<br><br>LATEX SENSITIVITY: &nbsp;No latex sensitivities.<br><br>CHIEF COMPLAINT/HISTORY OF PRESENT ILLNESS: &nbsp;The patient states for approximately 1 week she has been feeling sick, the whole time she has been having sinus pressure and a cough. &nbsp;She states the last few days she started developing some ear pain, increasing cough that is making it so that it is very difficult to sleep and a lot of facial pain, especially behind her eyes and anterior to her teeth. &nbsp;She has had some definite chills, possible fever.<br><br>PERTINENT PAST HISTORY/SOCIAL HISTORY: &nbsp;None. &nbsp;Vaccinations are up-to-date.<br><br>FAMILY HISTORY: &nbsp;Noncontributory.<br><br>REVIEW OF SYSTEMS: &nbsp;See HPI.<br><br>PHYSICAL EXAM<br>GENERAL: A 64-year-old female, alert and oriented, fully cogent. &nbsp; &nbsp;<br>SKIN: Unremarkable.<br>NECK: Supple without lymphadenopathy or nuchal rigidity.<br>HEENT: Conjunctivae are benign. &nbsp;Oral mucosa is moist. &nbsp;There is postnasal drip present. &nbsp;There is no tonsillar hypertrophy. &nbsp;The right ear is obscured by cerumen. &nbsp;The left ear TM is partially obscured by cerumen, does not have any erythema present. &nbsp;There is tenderness to the maxillary sinuses, none to the frontal.<br>LUNGS: Clear and equal bilaterally with normal respiratory effort.<br>HEART: Regular rate and rhythm without murmurs or gallops.<br><br>DIAGNOSIS: &nbsp;Acute sinusitis.<br><br>TREATMENT ADMINISTERED IN AHC: &nbsp;None.<br><br><br><br><br>DISCHARGE PLAN/INSTRUCTIONS:<br>1. Rest.<br>2. Increase fluids.<br>3. Z-Pak, use as directed.<br>4. Follow-up with Dr. Tasaddaq in 2-3 days if no improvement.<br><br>DISPOSITION: &nbsp;Home, stable condition. &nbsp;<br><br>DISCHARGE TIME: 1414<br><br><br><br>&nbsp;</p>";
        
        
        /*Contents to be displayed in middle when search is called*/
        $scope.roles = [{
            "fileName" : "File 1",
            "fileId" : "1",
            "receivedDate" : "09/14/2015",
            "assigneeName":" Assignee By:Abhi",
            "fileStatus":"Assigned",
            "statusClass":"freshBg",
            "checkBoxVisible" : true
        },{
            "fileName" : "File 2",
            "fileId" : "2",
            "receivedDate" : "01/17/2010",
            "assigneeName":" Assignee By:Vishal",
            "fileStatus":"Fresh",
            "statusClass":"rejecteBg",
            "checkBoxVisible" : true            
        },{
            "fileName" : "File 3",
            "fileId" : "3",
            "receivedDate" : "01/27/2016",
            "assigneeName":" Assignee By:Harshal",
            "fileStatus":"Completed",
            "statusClass":"allocateTL",
            "checkBoxVisible" : false            
        },{
            "fileName" : "File 4",
            "fileId" : "4",
            "receivedDate" : "12/07/2012",
            "assigneeName":" Assignee By:Akash",
            "fileStatus":"Rejected",
            "statusClass":"completedBg",
            "checkBoxVisible" : true           
        },{
            "fileName" : "File 5",
            "fileId" : "5",
            "receivedDate" : "01/27/2016",
            "assigneeName":" Assignee By:Harshal",
            "fileStatus":"Completed",
            "statusClass":"allocateTL",
            "checkBoxVisible" : false            
        },{
            "fileName" : "File 6",
            "fileId" : "6",
            "receivedDate" : "12/07/2012",
            "assigneeName":" Assignee By:Akash",
            "fileStatus":"Rejected",
            "statusClass":"completedBg",
            "checkBoxVisible" : false
        },{
            "fileName" : "File 7",
            "fileId" : "7",
            "receivedDate" : "01/19/2015",
            "assigneeName":" Assignee By:Ganesh",
            "fileStatus":"Rejected",
            "statusClass":"allocatedCoder",
            "checkBoxVisible" : true            
        },{
            "fileName" : "File 8",
            "fileId" : "8",
            receivedDate : "01/19/2015",
            "assigneeName":" Assignee By:Ganesh",
            "fileStatus":"Rejected",
            "statusClass":"allocatedCoder",
            "checkBoxVisible" : true            
        },{
            "fileName" : "File 9",
            "fileId" : "9",
            "receivedDate" : "08/08/2010",
            "assigneeName":" Assignee By:Abhi",
            "fileStatus":"Assigned",
            "statusClass":"freshBg",
            "checkBoxVisible" : false           
        },{
            "fileName" : "File 10",
            "fileId" : "10",
            "receivedDate" : "01/17/2010",
            "assigneeName":" Assignee By:Vishal",
            "fileStatus":"Fresh",
            "statusClass":"rejecteBg",
            "checkBoxVisible" : true           
        }];
        
          $scope.user = {
            roles: []
          };
          $scope.checkAll = function() {
              if($scope.mycheckbox == true){
                  $scope.user.roles = angular.copy($scope.roles);
                  console.log($scope.user.roles)
              }else{
                  $scope.user.roles = [];
              }
          };
        
        
        
        /*Function called on Bucket clicked*/
        $scope.changeBucket = function(index) {
           $scope.selected = index; 
        };
        
        
         $scope.$on('scrollbar.show', function(){
          console.log('Scrollbar show');
        });
        $scope.$on('scrollbar.hide', function(){
          console.log('Scrollbar hide');
        });
        
        
        
    }]);