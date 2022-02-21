def call(){
    def parametersDocker

    pipeline {       
        agent {
            kubernetes {
                yaml libraryResource('dind-pod-template.yaml')
            }
        }

        stages {
            stage('Input Values') {
                steps {
                    script{
                        parametersDocker = inputsDocker()
                    }                    
                }
            }

            stage('Run Docker') {
                steps {
                   container('docker') {
                        sh 'docker version'
                    }
                }
            }
        }
    }
}