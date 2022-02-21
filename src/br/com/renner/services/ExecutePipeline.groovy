package br.com.renner.services

import br.com.renner.utils.Constants

class ExecutePipeline implements Serializable {
    
    /*  Método responsável por executar a pipeline de IaC, baseado no paramâmetro pipelineTool
        Exemplo: terraform, ansible, etc...
    **/
    def iac(Map config = [:]){

        if(config.pipelineTool.equals(Constants.PIPELINE_TOOL_TERRAFORM)){
            pipelineTerraform(config)
        }  else if (config.pipelineTool.equals(Constants.PIPELINE_TOOL_ANSIBLE)){
            pipelineAnsible()
        } else {
            throw new RuntimeException("Config PipelineTool not exists. Please choose from the options: terraform, ansible")
        }
    }

    /*  Método responsável por executar a pipeline de Helm, baseado no paramâmetro pipelineTool
        Exemplo: terraform, ansible, etc...
    **/
    def deploy(Map config = [:]){

        if(config.pipelineTool.equals(Constants.PIPELINE_TOOL_HELM)){
            pipelineHelm(config)
        } else if (config.pipelineTool.equals(Constants.PIPELINE_TOOL_KUBECTL)){
            pipelineKubectl()
        } else {
            throw new RuntimeException("Config PipelineTool not exists. Please choose from the options: helm, kubectl")
        }
    }

    def build(Map config = [:]){

        def configExecuteInputDocker = config.executeInputDocker

        println(configExecuteInputDocker)

        if(config.pipelineTool.equals(Constants.PIPELINE_TOOL_DOCKER)){
            pipelineBuildDocker()
        }  else if (config.pipelineTool.equals(Constants.PIPELINE_TOOL_JAVA)){
            pipelineBuildJava()
        } else {
            throw new RuntimeException("Config PipelineTool not exists. Please choose from the options: docker, java")
        }
    }

}