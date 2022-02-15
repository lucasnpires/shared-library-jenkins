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
                - name: maven
                  image: maven:alpine
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
            stage('Run maven') {
                steps {
                    container('maven') {
                        sh 'mvn -version'
                    }
                }
            }
        }
    }
  }
}