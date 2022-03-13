def call(){
    def parametersHelm = [
        cloud: '',
        name: '',
        cluster: '',
        squad: '',
        environmentDeploy: ''
    ]

    inputDeploy = new getInputDeploy()


    script {        
        // Select Cloud provisioning Helm
        userInput = getInput("Cloud", "Qual cloud deseja executar?", "Cloud", inputDeploy.getClouds())
        parametersHelm.cloud = userInput
        
        // Select Helm provisioning in Cloud
        userInput = getInput("Helm", "Qual o Helm que deseja fazer o deploy na Cloud: $parametersHelm.cloud?", "Deploy - Helm", inputDeploy.getHelms()) 
        parametersHelm.name = userInput
        
        // Select Cluster in Cloud provisioning to Helm        
        userInput = getInput("Cluster", "Qual o Cluster da $parametersHelm.cloud deseja fazer o deploy?", "Deploy - Cluster", inputDeploy.getClusters(parametersHelm.cloud))
        parametersHelm.cluster = userInput
        
        // Select Squad - posop, plata, cocre
        userInput = getInput("Squad",  "Qual a squad responsável pelo deploy?", "Deploy - Squad", inputDeploy.getSquads()) 
        parametersHelm.squad = userInput
       
        // Select Env - dev, hml, prod, sup                    
        userInput = getInput("Environment",  "Qual o environment do deploy (dev, hml, prod, sup)?", "Deploy - Environment", inputDeploy.getEnvironments())
        parametersHelm.environmentDeploy = userInput

        return parametersHelm
    }
}