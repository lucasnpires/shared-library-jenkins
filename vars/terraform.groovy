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
                  image: hashicorp/terraform
                  command:
                   - cat
                  tty: true
                '''
            }
        }
        stages {
            stage('TF Apply') {
                steps {
                    container('terraform') {
                        sh 'terraform apply -input=false myplan'
                    }
                }
            }                
        }
    }
  }
}