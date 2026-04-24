pipeline {
    agent any
    stages {
        stage('Compile & Package') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('Deploy Stack') {
            steps {
                sh 'docker-compose up -d --build'
            }
        }
        stage('Health Check') {
            steps {
                sh 'docker ps'
            }
        }
    }
}