
def call(Map parametersDocker = [:], String commandDocker) { 
    def image = " $parametersDocker.registry/$parametersDocker.projectName:$parametersDocker.version"

    if(commandDocker.equals('build')){
        sh """
            cd $parametersDocker.projectName         
            docker build -t $image --no-cache .
        """
    } else if(commandDocker.equals('push')){        
        withCredentials([
                string(credentialsId: 'oci-registry-user', variable: 'userRegistryOCI'),
                string(credentialsId: 'oci-registry-pwd', variable: 'passRegistryOCI')
            ]) {
                sh "docker login us-phoenix-1.ocir.io -u '$userRegistryOCI' -p '$passRegistryOCI'"
                sh "docker push $image"
        } 
    }
}
