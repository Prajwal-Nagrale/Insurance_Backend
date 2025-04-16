pipeline {
  tools {
  maven 'praj-maven'
}
  agent any
  stages {
    stage('Cleaning the project') {
             
            steps {
                echo 'cleaning project ...'
                
                  sh "mvn clean -f UserService/pom.xml"
                
            }
        }
        
    stage('Artifact Construction') {
             
            steps {
                echo "artificat contruction"
              
                  sh "mvn package -f UserService/pom.xml"
                
                
            }
      }
  }
}
