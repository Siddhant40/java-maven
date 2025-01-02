pipeline {
    agent any
    tools {
        maven 'Maven' 
    }
    environment {
        CHROME_DRIVER_PATH = 'C:\\Program Files\\chromedriver-win64\\chromedriver.exe'  
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'Maven-java'
        SONAR_PROJECT_NAME = 'Maven-java'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Siddhant40/java-maven.git' 
            }
        }
        stage('Clean and Compile') {
            steps {
                
                bat '''
                mvn clean install
                ''' 
            }
        }

        stage('SonarQube Analysis - Test Files Only') {
            environment {
                SONAR_TOKEN = credentials('SonarQube') 
            }
            steps {
                bat """
                    mvn clean verify sonar:sonar ^ 
                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} ^ 
                    -Dsonar.projectName=${SONAR_PROJECT_NAME} ^ 
                    -Dsonar.sources=src/test/java ^ 
                    -Dsonar.host.url=${SONAR_HOST_URL} ^ 
                    -Dsonar.login=%SONAR_TOKEN% ^ 
                    -Dsonar.tests=src/test/java ^ 
                    -Dsonar.exclusions=**/src/main/**
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
