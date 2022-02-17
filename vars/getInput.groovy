def call (){                      
      getInputCloud()
}

def getInputCloud(){
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: 'Preencha as informações para executar a pipeline', 
            parameters: [
                choice(
                    name: 'Cloud', 
                    choices: getClouds(),
                    description: 'Cloud'
                ),                                
                choice(
                    name: 'ResourceType', 
                    choices: getResourceTypes('oci'),
                    description: 'Resource Type'
                ),
                choice(
                    name: 'ProjectName', 
                    choices: getProjects('kubernetes'),
                    description: 'Nome do Projeto'
                ),
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
        return ['container_registry','compute_instance','identity','kubernetes','network','object_storage'],
    } else {
        return ['No ResourcesType in Cloud']
    }
}

def getProjects(String resourceType){   

    if(cloud.equals('kubernetes')){
        return ['oke_private', 'oke_public']
    } else if(cloud.equals('identity')) {
        return ['auth_token']
    } else if(cloud.equals('vnc_default')) {
        return ['auth_token']
    } else if(cloud.equals('object_storage')) {
        return ['storage_terraform_state']
    } else {
        return ['No Projects in ResourceType']
    }
    
}