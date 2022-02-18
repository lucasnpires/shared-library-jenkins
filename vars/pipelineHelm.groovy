
def call(Map config = [:]){

    def parametersHelm = [
        cloud: '',
        helm: '',
        cluster: '',
        squad: '',
        environmentDeploy: ''

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

                    // Select Squad - posop, plata, cocre
                    script{
                        userInput = getInput('Squad', '')                        
                        parametersHelm.squad = userInput
                    }  

                    // Select Env - dev, hml, prod, sup 
                    script{
                        userInput = getInput('Env', '')                        
                        parametersHelm.environmentDeploy = userInput
                    }
                }
            }

            stage('Helm Validate') {
                steps {
                    container('helm') {                         
                        executeHelm('validate', parametersHelm)
                    }
                }
            }
        }
    }
}