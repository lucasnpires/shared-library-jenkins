def call (String inputName, String referencedInput){

    if(inputName.equals('cloud')){
        getInput(inputName, 'Cloud', 'Preencha qual a cloud deseja executar o IaC')
    } else {

    }

}

def getInput(String inputName, String inputDescription, String message){
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: message, 
            parameters: [
                choice(
                    name: inputName, 
                    choices: getClouds(),
                    description: inputDescription
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