pipeline {
    agent any
    stages {
        stage('Build Receita') {
            steps {
                build job: 'api.receitas', wait: true
            }
        }
        stage('Build Ingrediente') {
            steps {
                build job: 'api.ingrediente', wait: true
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
        stage('Deploy on k8s') {
            steps {
                withCredentials([ string(credentialsId: 'minikube-credentials', variable: 'api_token') ]) {
                    sh 'kubectl --token $api_token --server https://host.docker.internal:52011  --insecure-skip-tls-verify=true apply -f ./k8s/deployment.yaml '
                    sh 'kubectl --token $api_token --server https://host.docker.internal:52011  --insecure-skip-tls-verify=true apply -f ./k8s/service.yaml '
                }
            }
        }
    }
}