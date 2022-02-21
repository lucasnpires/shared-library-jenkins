def call(){
    pipeline {
        agent {
            kubernetes {
                yaml libraryResource('dind-pod-template.yaml')
            }
        }

        stages {
            stage('Run Docker') {
                steps {
                    container('dind') {                         
                        sh "sudo docker ps -a"
                    }
                }
            }
        }
    }
}