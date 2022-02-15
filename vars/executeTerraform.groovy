def call(Map config = [:]) {
    sh "echo Cloud: ${config.cloud}"
    sh "echo Command: ${config.command}"
    if(config.command.equals('version')){
        sh "terraform --version"
    }
}