pipeline {

    agent any
    
    tools {
        maven 'maven-for-jenkins'
    }
    
    stages {

        stage('Get the new updates from GitHub') {
            steps {
                dir("/tmp/Backend/") {
                    sh "git config --global --add safe.directory /tmp/Backend"
                    sh "git pull origin main" 
                }
            }
        }

       stage('Build the project') {
            steps {
                dir("/tmp/Backend/") {
                    sh "git config --global --add safe.directory /tmp/Backend"
                    sh "mvn clean package"
                }
            }
        }

        stage('Deploy docker container in EC2 Instance') {
            steps {
                sh "java -jar /home/ubuntu/deploy_git_correto/Backend/target/estoque-certo-1.0.jar"
            }
        }
    }

}
