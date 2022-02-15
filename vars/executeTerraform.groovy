def call(String command) {
    if (command.equals('version')) {
        sh 'terraform --version'
    }
}