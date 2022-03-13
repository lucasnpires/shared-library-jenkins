
def call(Map config = [:]){

    def parametersTerraform

    pipeline {
        agent {
            kubernetes {
                yaml libraryResource('terraform-pod-template.yaml')
            }
        }
        stages {
            stage('Input Values') {
                steps {
                    script{
                        parametersTerraform = inputsTerraform()
                    }                    
                }
            }

            stage('TF Init') {
                steps {
                    container('terraform') { 
                        //commands to use: version, init, fmt, validate, plan, apply, destroy
                        executeTerraform(parametersTerraform, 'init')
                    }
                }
            }

            stage('TF Validate') {
                steps {
                    container('terraform') {                     
                        executeTerraform(parametersTerraform, 'validate')
                        executeTerraform(parametersTerraform, 'fmt')
                    }
                }
            }
            
            stage('TF Plan') {
                steps {
                    container('terraform') {                     
                        executeTerraform(parametersTerraform, 'plan')                    
                    }
                }
            }

            stage('TF Approval') {
                steps {
                    script {              
                        parametersTerraform.applyDestroy = getInputApprovalDestroy()          
                    }
                }
            }

            stage('TF Apply') {
                when { 
                    expression {
                        parametersTerraform.applyDestroy == 'apply'
                    }                
                }            
                steps {
                    script {
                        sh "echo terraform apply"
                    }
                    //container('terraform') {                     
                    //    executeTerraform(parametersTerraform, 'apply')                    
                    //}
                }
            }

            stage('TF Destroy') {
                when { 
                    expression {
                        parametersTerraform.applyDestroy == 'destroy'
                    }                
                }            
                steps {
                    script {
                        sh "echo terraform destroy"
                    }
                    //container('terraform') {                     
                    //    executeTerraform(parametersTerraform, 'destroy')                    
                    //}
                }
            }
        }
    }
}