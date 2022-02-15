def call(String pipeline, Map config = [:]){
    if(pipeline.equals('terraform')){
        pipeline {
            agent {
                kubernetes {
                    yaml '''
                    apiVersion: v1
                    kind: Pod
                    spec:
                    containers:
                    - name: terraform
                    image: hashicorp/terraform:latest
                    command:
                    - cat
                    tty: true
                    '''
                }
            }
            stages {
                stage('TF Version') {
                    steps {
                        container('terraform') {          
                            executeTerraform('version', config)
                        }
                    }
                }
            }
        }        
    }
}