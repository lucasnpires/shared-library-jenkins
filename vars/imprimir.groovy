def call(Map config = [:]) {
    sh """
        echo ProjectName: ${config.projectName}
        echo Cloud: ${config.cloud}
        echo Cloud: ${config.resourceType}
    """
}