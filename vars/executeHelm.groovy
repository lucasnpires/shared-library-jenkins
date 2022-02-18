def call(String comandoHelm, Map parametersHelm = [:]) {

    if(comandoHelm.equals('validate')){
        sh """
            echo helm upgrade --install $parametersHelm.helm $parametersHelm.repo -n $parametersHelm.namespace --values ./nginx-ingress-values.yaml --create-namespace --debug --wait --atomic --cleanup-on-fail
        """
    } else if(comandoHelm.equals('install')){

    }

}
