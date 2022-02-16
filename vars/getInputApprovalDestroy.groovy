def call (){                      
    timeout ( time: 20, unit: "MINUTES" )  {
        def approvalDestroyInput  = input(
            id: 'approvalDestroyInput', 
            message: 'Apply or Destroy Terraform?', 
            parameters: [
                choice(
                    name: 'ApplyDestroy', 
                    choices: ['apply', 'destroy'],
                    description: 'Escolha a opção que deseja executar'
                )
            ]
        )        
        return approvalDestroyInput
    }    
}