def call(String comandoHelm, Map parametersHelm = [:]) {

    if(comandoHelm.equals('validate')){
        sh """
            comandoHelm: $comandoHelm
            parametersHelm: $parametersHelm
        """
    } else if(comandoHelm.equals('install')){

    }

}
