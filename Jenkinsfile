pipeline {

    agent any
    
    tools {
        maven 'maven-for-jenkins'
    }
    
    stages {
        stage('Build the project') {
            steps {
                sh "mvn clean package"
                sh 'pwd'
            }
        }
        stage('Deploy docker container in EC2 Instance') {
            steps {
                sh 'pwd'
                sh "java -jar target/estoque-certo-1.0.jar &"
            }
        }
    }

}
