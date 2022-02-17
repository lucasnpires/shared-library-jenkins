def call(Map config = [:]){
    pipelineDefinition(config)    
}

/*  Método responsável por avaliar qual o PipelineType
    Exemplo: iac, deploy, etc...
**/
def pipelineDefinition(Map config = [:]){        
    if(config.pipelineType.equals('iac')){
        executePipelineIaC(config)
    } else if(config.pipelineType.equals('deploy')){
        executePipelineDeploy(config)
    } else {
        throw new RuntimeException("Config PipelineType not exists. Please choose from the options: iac, deploy")
    }
}

/*  Método responsável por executar a pipeline de IaC, baseado no paramâmetro pipelineTool
    Exemplo: terraform, ansible, etc...
**/
def executePipelineIaC(Map config = [:]){

    if(config.pipelineTool.equals('terraform')){
        pipelineTerraform(config)
    }  else if (config.pipelineTool.equals('ansible')){
        pipelineAnsible()
    } else {
        throw new RuntimeException("Config PipelineTool not exists. Please choose from the options: terraform, ansible")
    }
}

/*  Método responsável por executar a pipeline de Helm, baseado no paramâmetro pipelineTool
    Exemplo: terraform, ansible, etc...
**/
def executePipelineDeploy(Map config = [:]){

    if(config.pipelineTool.equals('helm')){
        pipelineHelm(config)
    }  else if (config.pipelineTool.equals('kubectl')){
        pipelineKubectl()
    } else {
        throw new RuntimeException("Config PipelineTool not exists. Please choose from the options: helm, kubectl")
    }
}