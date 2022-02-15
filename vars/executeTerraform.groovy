def call(Map config = [:], String comandoTerraform) {
    sh "echo Cloud: ${config.cloud}"
    sh "echo Command: ${comandoTerraform}"

    if(comandoTerraform.equals('version')){
        sh "terraform --version"
    } else if(comandoTerraform.equals('init')){
        sh "echo terraform init"
    }
}