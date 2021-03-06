def call(Map parametersTerraform = [:], String comandoTerraform) {
    def dirExecucao = "terraform/$parametersTerraform.cloud/$parametersTerraform.resourceType/$parametersTerraform.projectName"    

    if(comandoTerraform.equals('version')){
        sh "terraform --version"
    } 
    
    else if(comandoTerraform.equals('init')){       

        sh """
            echo Cloud: $parametersTerraform.cloud
            echo ResourceType: $parametersTerraform.resourceType
            echo Nome Projeto: $parametersTerraform.projectName
            echo Diretório de Execucão: $dirExecucao

            cd $dirExecucao && terraform $comandoTerraform
        """
    } 
    
    else if(comandoTerraform.equals('fmt') || comandoTerraform.equals('validate') || comandoTerraform.equals('plan') || comandoTerraform.equals('apply') || comandoTerraform.equals('destroy')){
        sh "cd ${dirExecucao} && terraform ${comandoTerraform}"
    } else {
        sh "echo command not permited. Use init, fmt, validate, plan, apply or destroy"
    }
}
