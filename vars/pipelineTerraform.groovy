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
                        config.projectName = userInput.ProjectName         
                        config.cloud = userInput.Cloud
                        config.resourceType = userInput.ResourceType
                    }
                }
            }

            stage('TF Init') {
                steps {
                    container('terraform') { 
                        //commands to use: version, init, fmt, validate, plan, apply, destroy
                        executeTerraform(config, 'init')
                    }
                }
            }

            stage('TF Validate') {
                steps {
                    container('terraform') {                     
                        executeTerraform(config, 'validate')
                        executeTerraform(config, 'fmt')
                    }
                }
            }

            stage('TF Plan') {
                steps {
                    container('terraform') {                     
                        executeTerraform(config, 'plan')                    
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
                    //    executeTerraform(config, 'apply')                    
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
                    //    executeTerraform(config, 'destroy')                    
                    //}
                }
            }
        }
    }
}