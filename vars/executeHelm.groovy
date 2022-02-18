def call(String comandoHelm, Map parametersHelm = [:]) {

    if(comandoHelm.equals('validate')){
        sh """
            echo comandoHelm: $comandoHelm
            echo parametersHelm: $parametersHelm
        """
    } else if(comandoHelm.equals('install')){

    }

}
