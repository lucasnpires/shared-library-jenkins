def call (String inputName, String referencedInput){
    def choiceObject = [
        name: inputName,
        description: '',
        message: '',
        referencedInput: referencedInput,
        choices: []
    ]

    inputDeploy = new getInputDeploy()

    if(inputName.equals('Cloud')){
        choiceObject.description = "Qual cloud deseja executar?"
        choiceObject.message = "Cloud"
        choiceObject.choices = inputDeploy.getClouds()
        
        executeInput(choiceObject)
    } else if(inputName.equals('ResourceType')){
        choiceObject.description = "Qual o ResourceType dentro da $referencedInput?"
        choiceObject.message = "IaC - Resource Type"
        choiceObject.choices = inputDeploy.getResourceTypes(referencedInput)
        
        executeInput(choiceObject)
    } else if(inputName.equals('ProjectName')){
        choiceObject.description = "Qual o Projeto $referencedInput deseja executar?"
        choiceObject.message = "IaC - Project Name"
        choiceObject.choices = inputDeploy.getProjects(referencedInput)
        
        executeInput(choiceObject)
    } else if(inputName.equals('Helm')){
        choiceObject.description = "Qual o Helm que deseja fazer o deploy na Cloud: $referencedInput?"
        choiceObject.message = "Deploy - Helm"
        choiceObject.choices = inputDeploy.getHelms()
        
        executeInput(choiceObject)
    } else if(inputName.equals('Cluster')){
        choiceObject.description = "Qual o Cluster da $referencedInput deseja fazer o deploy?"
        choiceObject.message = "Deploy - Cluster"
        choiceObject.choices = inputDeploy.getClusters(referencedInput)
        
        executeInput(choiceObject)
    }  else if(inputName.equals('Squad')){
        choiceObject.description = "Qual a squad responsável pelo deploy?"
        choiceObject.message = "Deploy - Squad"
        choiceObject.choices = inputDeploy.getSquads()
        
        executeInput(choiceObject)
    }  else if(inputName.equals('Env')){
        choiceObject.description = "Qual o environment do deploy (dev, hml, prod, sup)?"
        choiceObject.message = "Deploy - Environment"
        choiceObject.choices = inputDeploy.getEnvironments()
        
        executeInput(choiceObject)
    } else {
        throw new RuntimeException("InputName not exists.")
    }

}

def executeInput(Map choiceObject = [:]){
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: choiceObject.message, 
            parameters: [
                choice(
                    name: choiceObject.name, 
                    choices: choiceObject.choices,
                    description: choiceObject.description
                )
            ]
        )
        return userInput
    }  
}

