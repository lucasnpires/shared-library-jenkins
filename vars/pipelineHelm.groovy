
def call(Map config = [:]){

    def parametersHelm = [
        cloud: '',
        helm: '',
        cluster: ''   
    ]


    pipeline {
        agent {
            kubernetes {
                yaml libraryResource('helm-pod-template.yaml')
            }
        }
        stages {
            stage('Input Values') {
                steps {

                    // Select Cloud provisioning Helm
                    script{
                        userInput = getInput('Cloud', '')                         
                        parametersHelm.cloud = userInput
                    }

                    // Select Helm provisioning in Cloud
                    script{
                        userInput = getInput('Helm', parametersHelm.cloud)                        
                        parametersHelm.helm = userInput
                    }

                    // Select Cluster in Cloud provisioning to Helm
                    script{
                        userInput = getInput('Cluster', parametersHelm.cloud)                        
                        parametersHelm.cluster = userInput
                    }
                }
            }
        }
    }
}