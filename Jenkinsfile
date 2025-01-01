pipeline {
    agent any
    tools {
        maven 'Maven' // Ensure this matches the Maven installation name in Jenkins
    }
    environment {
        CHROME_DRIVER_PATH = 'C:\\Program Files\\chromedriver-win64\\chromedriver.exe'  // Update path if needed
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
                 script {
                    // Set the system property for the ChromeDriver path
                    System.setProperty("webdriver.chrome.driver", "${env.CHROME_DRIVER_PATH}")
                }
                bat '''mvn clean install
                ''' // Clean and compile the project to ensure binaries are available
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
                    -Dsonar.exclusions=**/test/**  // Exclude files from test directories
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
