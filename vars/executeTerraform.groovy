def call(String command) {
    sh "terraform --version"
    
    //if (command.equals('version')) {
        //sh "echo cloud: ${config.cloud}"
        //sh "echo resourceType: ${config.resourceType}"
        //sh "echo dirTerraform: ${config.cloud}/${config.resourceType}"
    //}
}