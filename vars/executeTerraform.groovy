def call(Map config = [:], String comandoTerraform) {
    def dirExecucao = "terraform/${config.cloud}/${config.resourceType}/${config.projectName}"

    sh "echo Cloud: ${config.cloud}"
    sh "echo resourceType: ${config.resourceType}"
    sh "echo projectName: ${config.projectName}"
    sh "echo dirExecucao: ${dirExecucao}"

    if(comandoTerraform.equals('version')){
        sh "terraform --version"
    } else if(comandoTerraform.equals('init') || comandoTerraform.equals('fmt') || comandoTerraform.equals('validate') || comandoTerraform.equals('plan') || comandoTerraform.equals('apply') || comandoTerraform.equals('destroy')){
        sh "cd ${dirExecucao} && ls -lha && terraform ${comandoTerraform}"
    } else {
        sh "echo command not permited. Use init, fmt, validate, plan, apply or destroy"
    }
}