import br.com.renner.utils.ChoicesInputs

def call(){
    def parametersTerraform = [
        projectName: '',
        resourceType: '', 
        cloud: '',
    ]

    inputDeploy = new ChoicesInputs()

    script {

        // Select Cloud provisioning IaC
        userInput = getInput("Cloud", "Qual cloud deseja executar?", "Cloud", inputDeploy.getClouds())               
        parametersTerraform.cloud = userInput
        
        // Select ResourceType provisioning in Cloud
        userInput = getInput("ResourceType", "Qual o ResourceType dentro da $parametersTerraform.cloud?", "IaC - Resource Type", inputDeploy.getResourceTypes(parametersTerraform.cloud)) 
        parametersTerraform.resourceType = userInput
        
        // Select ProjectName provisioning in Cloud
        userInput = getInput("ProjectName", "Qual o Projeto $parametersTerraform.resourceType deseja executar?", "IaC - Project Name", inputDeploy.getProjects(parametersTerraform.resourceType))  
        parametersTerraform.projectName = userInput     

        return parametersTerraform
    }

}