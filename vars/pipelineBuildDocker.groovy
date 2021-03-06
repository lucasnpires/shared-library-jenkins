def call(Map config = [:]){
    def parametersDocker

    pipeline {       
        agent {
            kubernetes {
                yaml libraryResource('dind-pod-template.yaml')
            }
        }

        stages {            
            stage('Input Values') {
                when { 
                    expression {
                        config.executeInputDocker == true
                    }                
                }  
                steps {
                    script{
                        parametersDocker = inputsDocker()
                    }                    
                }
            }

            stage('Build Docker') {
                steps {
                   container('docker') {
                       executeDocker(parametersDocker, 'build')                        
                    }
                }
            }

            stage('Push Docker') {
                steps {
                   container('docker') {
                       executeDocker(parametersDocker, 'push')                        
                    }
                }
            }
        }
    }
}