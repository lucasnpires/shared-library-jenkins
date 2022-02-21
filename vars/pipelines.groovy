import br.com.renner.services.PipelineDefinition

def call(Map config = [:]){
    new PipelineDefinition().defineExecution(config)
}