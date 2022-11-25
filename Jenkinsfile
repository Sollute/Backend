pipeline {

    agent any

    stages {

        stage('Get the new updates from GitHub') {
            steps {
                sh "cd /Backend"
                sh "git pull git@github.com:Sollute/Backend.git"
            }
        }

        stage('Build the project') {
            steps {
                sh "cd /Backend"
                sh "mvn clean package"
            }
        }

        stage('Deploy docker container in EC2 Instance') {
            steps {
                sh "java -jar /Backend/target/estoque-certo-1.0.jar"
            }
        }

    }

}