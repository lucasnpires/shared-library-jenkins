def call(Map config = [:], String comandoTerraform) {
    def dirExecucao = "terraform/${config.cloud}/${config.resourceType}/${config.projectName}"

    sh "echo Cloud: ${config.cloud}"
    sh "echo resourceType: ${config.resourceType}"
    sh "echo projectName: ${config.projectName}"
    sh "echo dirExecucao: ${dirExecucao}"

    if(comandoTerraform.equals('version')){
        sh "terraform --version"
    } else if(comandoTerraform.equals('init')){
        sh "cd ${dirExecucao} && ls -lha"
        sh "echo terraform init"
    } else if(comandoTerraform.equals('fmt')){
        sh "echo terraform fmt"
    } else if(comandoTerraform.equals('validate')){
        sh "echo terraform validate"
    } else if(comandoTerraform.equals('plan')){
        sh "echo terraform plan"
    } else if(comandoTerraform.equals('apply')){
        sh "echo terraform apply"
    } else if(comandoTerraform.equals('destroy')){
        sh "echo terraform destroy"
    } else {
        sh "echo command not permited. Use init, fmt, validate, plan, apply or destroy"
    }
}