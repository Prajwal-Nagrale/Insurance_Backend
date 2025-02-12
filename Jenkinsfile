pipeline {
  agent any
  stages {
    stage('Cleaning the project') {
             
            steps {
                echo 'cleaning project ...'
                withMaven {
                  sh "mvn clean "
                }
            }
        }
        
    stage('Artifact Construction') {
             
            steps {
                echo "artificat contruction"
              withMaven {
                  sh "mvn package"
                }
                
            }
      }
  }
}
