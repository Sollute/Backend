pipeline {

    agent any
    
    tools {
        maven 'maven-for-jenkins'
    }
    
    stages {

        stage('Get the new updates from GitHub') {
            steps {
                dir("/home/ubuntu/deploy_git_correto/") {
                   sh "git pull origin main" 
                }
            }
        }

        stage('Build the project') {
            steps {
                sh "cd /home/ubuntu/deploy_git_correto/"
                sh "mvn clean package"
            }
        }

        stage('Deploy docker container in EC2 Instance') {
            steps {
                sh "java -jar /home/ubuntu/deploy_git_correto/Backend/target/estoque-certo-1.0.jar"
            }
        }

    }

}
