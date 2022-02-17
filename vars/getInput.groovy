def call (String inputName, String referencedInput){
    def choiceObject = [
        name: inputName,
        description: '',
        message: 'Terraform IaC',
        referencedInput: referencedInput,
        choices: []
    ]

    if(inputName.equals('Cloud')){
        choiceObject.description = "Qual cloud deseja executar?"
        choiceObject.choices = getClouds()
        
        executeInput(choiceObject)
    } else if(inputName.equals('ResourceType')){
        choiceObject.description = "Qual o ResourceType dentro da $referencedInput?"
        choiceObject.choices = getResourceTypes(referencedInput)
        
        executeInput(choiceObject)
    }
    
    else {
        throw new RuntimeException("Config PipelineType not exists. Please choose from the options: iac, helm")
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
                ),                                
                //choice(
                //    name: 'ResourceType', 
                //    choices: getResourceTypes('azure'),
                //    description: 'Resource Type'
                //),
                //choice(
                //    name: 'ProjectName', 
                //    choices: getProjects('kubernetes'),
                //    description: 'Nome do Projeto'
                //),
            ]
        )

        return userInput
    }  
}

def getClouds(){
    return ['oci','azure']
}

def getResourceTypes(String cloud){
    if(cloud.equals('oci')){
        return ['container_registry','compute_instance','identity','kubernetes','network','object_storage']
    } else {
        return ['No ResourcesType in Cloud']
    }
}

def getProjects(String resourceType){   

    if(resourceType.equals('kubernetes')){
        return ['oke_private', 'oke_public']
    } else if(resourceType.equals('identity')) {
        return ['auth_token']
    } else if(resourceType.equals('vnc_default')) {
        return ['auth_token']
    } else if(resourceType.equals('object_storage')) {
        return ['storage_terraform_state']
    } else {
        return ['No Projects in ResourceType']
    }
    
}