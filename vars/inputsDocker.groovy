def call(){
    def parametersDocker = [
        projectName: '',
    ]

    inputs = new getInputsDocker()

    script {

        // Select Project Name
        userInput = getInput("ProjectName", "Qual é Nome do Projeto?", "Docker Project Name", inputs.getProjects())               
        parametersDocker.cloud = projectName

        return parametersDocker
    }

}