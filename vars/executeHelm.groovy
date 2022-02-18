def call(String comandoHelm, Map parametersHelm = [:]) {

    def helmName = parametersHelm.helm

    if(comandoHelm.equals('validate')){
        sh """
            cd $helmName
            ls -lha
            echo helm upgrade --install $helmName ../../helm-charts/$helmName -n $helmName --values ./$helmName-values.yaml --create-namespace --debug --wait --atomic --cleanup-on-fail
        """
    } else if(comandoHelm.equals('install')){

    }

}
