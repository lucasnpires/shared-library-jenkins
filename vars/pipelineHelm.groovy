
def call(Map config = [:]){


    pipeline {
        agent {
            kubernetes {
                yaml libraryResource('helm-pod-template.yaml')
            }
        }
        stages {
            stage('Input Values') {
                steps {
                    parametersHelm = inputsHelm()
                }
            }

            stage('Helm Validate') {
                steps {
                    container('helm') {                         
                        executeHelm('validate', parametersHelm)
                    }
                }
            }
        }
    }
}