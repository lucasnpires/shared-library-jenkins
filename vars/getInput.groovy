def call (){                      
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: 'Preencha as informações para executar a pipeline', 
            parameters: [
                choice(
                        name: 'Cloud', 
                        choices: ['oci', 'azure', 'aws'],
                        description: 'Cloud'
                ),                                
                choice(
                        name: 'ProjectName', 
                        choices: ['oke_public', 'oke_private'],
                        description: 'Nome do Projeto'
                ),
                choice(
                        name: 'ResourceType', 
                        choices: ['kubernetes'],
                        description: 'Resource Type'
                )
            ]
        )

        return userInput
    }    
}