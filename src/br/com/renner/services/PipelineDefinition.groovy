package br.com.renner.services

import br.com.renner.utils.Constants


class PipelineDefinition implements Serializable {

    private final def script

    PipelineDefinition(def script) {
        this.script = script
    }

    def executePipeline = new ExecutePipeline()
    
    /*  Método responsável por avaliar qual o PipelineType
        Exemplo: iac, deploy, etc...
    **/
    def defineExecution(Map config){        
        
        if(config.pipelineType.equals(Constants.PIPELINE_TYPE_IAC)){
            executePipeline.iac(config)
        } else if(config.pipelineType.equals(Constants.PIPELINE_TYPE_DEPLOY)){
            executePipeline.deploy(config)
        }  else if(config.pipelineType.equals(Constants.PIPELINE_TYPE_BUILD)){
            node {
                this.script.sh("$config")
            }
            //executePipeline.build(config.)
        } else {
            throw new RuntimeException("Config PipelineType not exists. Please choose from the options: iac, deploy")
        }
    }
}