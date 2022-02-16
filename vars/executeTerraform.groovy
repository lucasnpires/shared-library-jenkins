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
    } 
    
    else if(comandoTerraform.equals('fmt') || comandoTerraform.equals('validate') || comandoTerraform.equals('plan') || comandoTerraform.equals('apply') || comandoTerraform.equals('destroy')){
        sh """
            cd ${dirExecucao}          
            terraform ${comandoTerraform}
        """
    } else {
        sh "echo command not permited. Use init, fmt, validate, plan, apply or destroy"
    }
}