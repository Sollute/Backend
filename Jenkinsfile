pipeline {

    agent any
    
    tools {
        maven 'maven-for-jenkins'
    }
    
    stages {

        stage('Build the project') {
            steps {
                sh "mv sollute.service /etc/systemd/system"
                sh "mvn clean package"
            }
        }
        
        stage('Deploy docker container in EC2 Instance') {
            steps {
                sh "systemctl restart sollute.service"
            }
        }
    }

}
