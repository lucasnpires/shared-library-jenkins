def call(Map config = [:]){
    pipelineDefinition(config)    
}

def pipelineDefinition(Map config = [:]){    
    
    if(config.pipelineType.equals('iac')){
        executePipelineIaC(config)
    } else {
        throw new RuntimeException("PipelineType not exists. Please choose from the options: iac, helm")
    }
}


def executePipelineIaC(Map config = [:]){

    if(config.pipelineTool.equals('terraform')){
        pipelineTerraform(config)
    }  else if (config.pipelineTool.equals('equals')){
        pipelineAnsible('ansible')
    }
}