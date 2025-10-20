pipeline {
    agent any
    
    tools {
        maven 'Maven'      
        jdk 'Java'       
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
                script {
                    if (isUnix()) {
                        sh 'mvn clean compile'
                    } else {
                        bat 'mvn clean compile'
                    }
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Running Selenium tests...'
                script {
                    if (isUnix()) {
                        sh 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
                    } else {
                        bat 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
                    }
                }
            }
        }
        
        stage('Publish Reports') {
            steps {
                echo 'Publishing test reports...'
                
                // Publish HTML reports (ExtentReports)
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport_*.html',
                    reportName: 'Extent Test Report',
                    reportTitles: 'Selenium Test Execution Report'
                ])
                
                // Publish TestNG results
                step([$class: 'Publisher', 
                      reportFilenamePattern: '**/testng-results.xml'])
            }
        }
    }
    
    post {
        always {
            echo 'Archiving artifacts...'
            archiveArtifacts artifacts: 'screenshots/**/*.png', 
                             allowEmptyArchive: true
            
            archiveArtifacts artifacts: 'reports/**/*.html', 
                             allowEmptyArchive: true
        }
        
        success {
            echo 'Build and tests completed successfully!'
        }
        
        failure {
            echo 'Build or tests failed. Check the console output for details.'
        }
        
        unstable {
            echo 'Build is unstable. Some tests may have failed.'
        }
    }
}
