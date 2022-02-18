def call(String comandoHelm, Map parametersHelm = [:]) {

    if(comandoHelm.equals('validate')){
        sh """
            echo helm upgrade --install $parametersHelm.helm $parametersHelm. -n jenkins-helm --values ./nginx-ingress-values.yaml --create-namespace --debug --wait --atomic --cleanup-on-fail
        """
    } else if(comandoHelm.equals('install')){

    }

}
