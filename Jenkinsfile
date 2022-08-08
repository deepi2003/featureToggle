pipeline {
    agent {
        node {
            label 'BadBuildAgent'
        }
    }
    environment {
        LATEST_VERSION = """${sh(
                returnStdout: true,
                script: 'echo -n "${BUILD_NUMBER}-${GIT_COMMIT:0:6}"'
            )}"""
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building package here'
                sh 'cp -prvf /appl/scripts/gradle.properties .'
                sh './gradlew clean build'
            }
        }
    }
    post {
        always {
            publishHTML (target: [
                                allowMissing: true,
                                alwaysLinkToLastBuild: false,
                                keepAll: true,
                                reportDir: 'build/reports/tests/test',
                                reportFiles: 'index.html',
                                reportName: "Test Report"
                ])
            
            cleanWs()
        }
    }
}
