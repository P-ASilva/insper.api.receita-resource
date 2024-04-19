pipeline {
    agent any
    stages {
        stage('Build Receita') {
            steps {
                build job: 'api.receitas-resource', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }      
        stage('Build Image') {
            steps {
                script {
                    receita = docker.build("pasilva2023/receita:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        receita.push("${env.BUILD_ID}")
                        receita.push("latest")
                    }
                }
            }
        }
    }
}