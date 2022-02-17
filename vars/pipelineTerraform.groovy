
def call(Map config = [:]){

    def parametersTerraform = [
        projectName: '',
        resourceType: '', 
        cloud: '',
        applyDestroy: ''
    ]

    pipeline {
        agent {
            kubernetes {
                yaml libraryResource('terraform-pod-template.yaml')
            }
        }
        stages {
            stage('Input Values') {
                steps {

                    // input values cloud
                    script{
                        userInput = getInput('Cloud', '')                         
                        parametersTerraform.cloud = userInput
                    }

                    script{
                        userInput = getInput('ResourceType', parametersTerraform.cloud)                        
                        parametersTerraform.resourceType = userInput
                    }

                    script{
                        userInput = getInput('ProjectName', parametersTerraform.resourceType)                           
                        parametersTerraform.projectName = userInput     
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