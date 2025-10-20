  pipeline {
    agent any
    
    environment {
        // IntelliJ IDEA's bundled Maven path
        MAVEN_HOME = 'C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2025.2\\plugins\\maven\\lib\\maven3'
        JAVA_HOME = 'C:\\Users\\manis\\.jdks\\graalvm-jdk-21.0.8'
        PATH = "${env.MAVEN_HOME}\\bin;${env.JAVA_HOME}\\bin;${env.PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling the project...'
                bat 'mvn clean compile'
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Running Selenium tests...'
                bat 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
        
        stage('Publish Reports') {
            steps {
                echo 'Publishing test reports...'
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport_*.html',
                    reportName: 'Extent Test Report',
                    reportTitles: 'Test Execution Report'
                ])
            }
        }
    }
    
    post {
        always {
            echo 'Archiving artifacts...'
            archiveArtifacts artifacts: 'screenshots/**/*.png', 
                             allowEmptyArchive: true
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
