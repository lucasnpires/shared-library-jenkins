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
                - name: alpine
                  image: alpine
                  command:
                   - cat
                  tty: true
                '''
            }
        }
        stages {
            stage('Terraform Init') {
                steps {
                    container('alpine') {          
                        sh "echo terraform init"
                    }
                }
            }
        }
    }
  }
}