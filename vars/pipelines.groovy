import br.com.renner.services.PipelineDefinition

def call(Map args=[:]){
    new PipelineDefinition(this).defineExecution("${args}")
}