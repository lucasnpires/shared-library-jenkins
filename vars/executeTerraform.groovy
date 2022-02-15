def call(Map config = [:]) {
    sh "echo Execute Command ${config.command} in Cloud: ${config.cloud}"
}