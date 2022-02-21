
def call(Map parametersDocker = [:]) {    

    sh """
        cd $parametersDocker.projectName         
        docker build -t us-phoenix-1.ocir.io/axxalonri6o1/devops/$parametersDocker.projectName:$parametersDocker.version --no-cache -f Dockerfile-$parametersDocker.version .
    """
}
