pipeline {
    agent any

    tools {
        maven "maven3.8.7"
    }

    stages {
         stage('Test and Compile') {
            steps {
                echo ' -- Testing project --'
                bat 'mvn test'
		        bat 'mvn clean compile'
            }
        }
         stage('Package') {
            steps {
                echo ' -- Packaging project --'
		        bat 'mvn clean package'
            }
        }
        stage('Analyzing project') {
            steps {
                echo ' -- Analyzing project -- '
                bat 'mvn sonar:sonar -Dsonar.login=squ_80a9d90b556bf1107ab4d5b26cb73ffc4fbf9a79'
            }
	}
         stage('Build Image & Run Container') {
            steps {
                echo ' -- Building image -- '
                bat "docker build -t img_predictioncrime:1.${env.BUILD_NUMBER} ."
                bat "docker tag img_predictioncrime:1.${env.BUILD_NUMBER} img_predictioncrime:1.${env.BUILD_NUMBER}"
                echo ' -- Running container -- '
                bat "docker run -d -p 8080:8080 --name c_predictioncrime img_predictioncrime:1.${env.BUILD_NUMBER}"
            }
        }
    }
}

