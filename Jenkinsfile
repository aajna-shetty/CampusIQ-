pipeline {
agent any


stages {
    stage('Compile & Package') {
        steps {
            sh 'chmod +x mvnw'
            sh './mvnw clean package -DskipTests'
        }
    }

    stage('Health Check') {
        steps {
            sh 'ls target'
        }
    }
}


}
