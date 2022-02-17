def call(String comandoHelm, Map parametersHelm = [:]) {

    if(comandoHelm.equals('validate')){
        sh """
            helm upgrade --install $helmName
        """
    } else if(comandoHelm.equals('install')){

    }

}
