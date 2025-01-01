pipeline {
    agent any
    tools {
        maven 'Maven' // Ensure this matches the Maven installation name in Jenkins
    }
    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'Maven-java'
        SONAR_PROJECT_NAME = 'Maven-java'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Siddhant40/java-maven.git' // Replace with your GitHub repository URL
            }
        }
        stage('Clean and Compile') {
            steps {
                bat '''mvn build install
                ''' // Clean and compile the project to ensure binaries are available
            }
        }
        stage('Unit Tests') {
            steps {
                bat 'mvn test' // Run unit tests
            }
        }
        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('SonarQube') // Jenkins credential ID for SonarQube token
            }
            steps {
                bat """
                    mvn clean verify sonar:sonar ^
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^
                    -Dsonar.projectName=${SONAR_PROJECT_NAME} ^
                    -Dsonar.sources=. ^
                   
                    -Dsonar.host.url=${SONAR_HOST_URL} ^
                    -Dsonar.login=%SONAR_TOKEN%
                """
            }
        }
    }
    post {
        success {
            echo 'Build, testing, and analysis completed successfully!'
        }
        failure {
            echo 'Build, testing, or analysis failed!'
        }
        always {
            echo 'Pipeline execution finished.'
        }
    }
}
