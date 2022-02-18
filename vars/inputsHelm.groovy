def call(){
    def parametersHelm = [
        cloud: '',
        helm: '',
        cluster: '',
        squad: '',
        environmentDeploy: ''
    ]


    script {
        
        // Select Cloud provisioning Helm
        userInput = getInput('Cloud', '')
        parametersHelm.cloud = userInput
        
        // Select Helm provisioning in Cloud
        userInput = scriptInput('Helm', parametersHelm.cloud)                        
        parametersHelm.helm = userInput
        
        // Select Cluster in Cloud provisioning to Helm
        userInput = getInput('Cluster', parametersHelm.cloud)                        
        parametersHelm.cluster = userInput
        
        // Select Squad - posop, plata, cocre
        userInput = getInput('Squad', '')                        
        parametersHelm.squad = userInput
       
       // Select Env - dev, hml, prod, sup 
        userInput = getInput('Env', '')                        
        parametersHelm.environmentDeploy = userInput

        return parametersHelm

    }
}