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

            stage('Build Image Docker') {
                steps {
                   container('docker') {
                        sh "cd $parametersDocker.projectName && ls -lha && docker version"
                    }
                }
            }
        }
    }
}