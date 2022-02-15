def call(Map config = [:], String comandoTerraform) {
    def dirExecucao = "terraform/${config.cloud}/${config.resourceType}/${config.projectName}"

    sh """
        echo Cloud: $config.cloud
        echo ResourceType: $config.resourceType
        echo Nome Projeto: $config.projectName
        echo Diretório de Execucão: $dirExecucao
    """

    if(comandoTerraform.equals('version')){
        sh "terraform --version"
    } 
    
    else if(comandoTerraform.equals('init')){
        withCredentials([file(credentialsId: 'rsa-azdevops-git', variable: 'FILE')]) {
            sh """
                GIT_SSH_COMMAND="ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no"
                mkdir -p ~/.ssh
                cp -ru "$FILE" ~/.ssh/id_rsa
                cat ~/.ssh/id_rsa
                cd $dirExecucao && ls -lha && terraform $comandoTerraform
            """                        
        }
    } 
    
    else if(comandoTerraform.equals('fmt') || comandoTerraform.equals('validate') || comandoTerraform.equals('plan') || comandoTerraform.equals('apply') || comandoTerraform.equals('destroy')){
        sh """
            cd ${dirExecucao}
            ls -lha 
            echo terraform ${comandoTerraform}
        """
    } else {
        sh "echo command not permited. Use init, fmt, validate, plan, apply or destroy"
    }
}