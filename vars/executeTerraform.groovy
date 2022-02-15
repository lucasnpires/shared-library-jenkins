def call(Map config = [:]) {
    sh "echo Executar comando: ${config.command} na ${config.cloud}"
}