def call(){
    pipeline {
        environment {
            registry = "us-phoenix-1.ocir.io/axxalonri6o1/devops/teste"
            registryCredential = 'dockerhub_id'
            dockerImage = ''
        }
        agent {
            kubernetes {
                yaml libraryResource('dind-pod-template.yaml')
            }
        }

        stages {
            stage('Run Docker') {
                steps {
                   script {
                        dockerImage = docker.build registry + ":$BUILD_NUMBER"
                    }
                }
            }
        }
    }
}