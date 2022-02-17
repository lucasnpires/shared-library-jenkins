def call (){                      
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
                    name: 'ProjectName', 
                    choices: ['oke_public', 'oke_private'],
                    description: 'Nome do Projeto'
                ),
                choice(
                    name: 'ResourceType', 
                    choices: ['kubernetes','compute_instance',''],
                    description: 'Resource Type'
                )
            ]
        )

        return userInput
    }    
}

def getClouds(){
    return ['oci','azure']
}