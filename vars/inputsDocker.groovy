import br.com.renner.utils.ChoicesInputs

def call(){
    def parametersDocker = [
        projectName: '',
        version: ''
    ]

    def inputs = new ChoicesInputs()

    script {

        // Select Project Name
        userInput = getInput("ProjectName", "Qual é Nome do Projeto?", "Docker Project Name", inputs.getProjects())               
        parametersDocker.projectName = userInput

        userInput = getInputString("Version", "Qual é a versão da imagem? Ex.: 1.0.0", "Docker Project Name")               
        parametersDocker.version = userInput   

         // Select Registry Repo
        userInput = getInput("RegistryRepo", "Qual é Registry Repo que deseja fazer o push?", "Docker Registry Repo", inputs.getReposRegistrys())               
        parametersDocker.registry = userInput           

        return parametersDocker
    }

}