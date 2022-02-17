def parametersTerraform = [
    projectName: '',
    resourceType: '', 
    cloud: '',
    applyDestroy: ''
]

def call(Map config = [:]){
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
                        userInput = getInput() 
                        parametersTerraform.projectName = userInput.ProjectName         
                        parametersTerraform.cloud = userInput.Cloud
                        parametersTerraform.resourceType = userInput.ResourceType
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
                        config.applyDestroy = getInputApprovalDestroy()          
                    }
                }
            }

            stage('TF Apply') {
                when { 
                    expression {
                        config.applyDestroy == 'apply'
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
                        config.applyDestroy == 'destroy'
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