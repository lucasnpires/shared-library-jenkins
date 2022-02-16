def call(Map config = [:], String comandoTerraform) {
    def dirExecucao = "terraform/${config.cloud}/${config.resourceType}/${config.projectName}"

    if(comandoTerraform.equals('version')){
        sh "terraform --version"
    } 
    
    else if(comandoTerraform.equals('init')){
        sh """
            echo Cloud: $config.cloud
            echo ResourceType: $config.resourceType
            echo Nome Projeto: $config.projectName
            echo Diretório de Execucão: $dirExecucao

            cd $dirExecucao && terraform $comandoTerraform
        """
        userInput = getInput()

        config.projectName = userInput.ProjectName
        config.cloud = userInput.Cloud
        //config.resourceType = userInput.ResourceType
    } 
    
    else if(comandoTerraform.equals('fmt') || comandoTerraform.equals('validate') || comandoTerraform.equals('plan') || comandoTerraform.equals('apply') || comandoTerraform.equals('destroy')){
        sh "cd ${dirExecucao} && terraform ${comandoTerraform}"
    } else {
        sh "echo command not permited. Use init, fmt, validate, plan, apply or destroy"
    }
}


def getInput(){                  
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
                )
            ]
        )

        return userInput
    }
}