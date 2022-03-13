
def call(Map config = [:]){

    def parametersHelm

    pipeline {
        agent {
            kubernetes {
                yaml libraryResource('helm-pod-template.yaml')
            }
        }
        stages {
            stage('Input Values') {
                steps {
                    script {
                        parametersHelm = inputsHelm()
                    }
                }
            }

            stage('Helm Validate') {
                steps {
                    container('helm') {                         
                        executeHelm('validate', parametersHelm)
                    }
                }
            }

            stage('Helm Install') {
                steps {
                    container('helm') {                         
                        executeHelm('install', parametersHelm)
                    }
                }
            }
        }
    }
}