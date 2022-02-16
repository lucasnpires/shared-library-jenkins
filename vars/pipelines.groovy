def call(Map config = [:]){
    pipelineDefinition(config)    
}

/*  Método responsável por avaliar qual o PipelineType
    Exemplo: iac, helm, etc...
**/
def pipelineDefinition(Map config = [:]){    
    
    if(config.pipelineType.equals('iac')){
        executePipelineIaC(config)
    } else if(config.pipelineType.equals('helm')){
        executePipelineHelm(config)
    } else {
        throw new RuntimeException("PipelineType not exists. Please choose from the options: iac, helm")
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
        throw new RuntimeException("PipelineTool not exists. Please choose from the options: terraform, ansible")
    }
}