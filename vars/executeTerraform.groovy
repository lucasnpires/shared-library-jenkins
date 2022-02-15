def call(Map config = [:]) {
    sh "Cloud: ${config.cloud}"
    sh "Command: ${config.command}"
}