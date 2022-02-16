def call (){                      
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: 'Apply or Destroy Terraform?', 
            parameters: [
                choice(
                    name: 'ApplyDestroy', 
                    choices: ['apply', 'destroy'],
                    description: 'Escolha a opção que deseja executar'
                )
            ]
        )
        return userInput
    }    
}