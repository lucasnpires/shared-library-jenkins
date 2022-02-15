def call(String command) {
  if (command == 'init') {
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
            stage("Imprimir Parâmetros") {
                steps {
                    sh 'env | sort'
                }
            }
            stage('Terraform Init') {
                steps {
                    container('terraform') {          
                        sh 'init'
                    }
                }
            }
        }
    }
  }
}