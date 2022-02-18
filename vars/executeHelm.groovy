def call(String comandoHelm, Map parametersHelm = [:]) {

    def helmAlias = "$parametersHelm.squad-$parametersHelm.helm-$parametersHelm.environmentDeploy"

    if(comandoHelm.equals('validate')){
        sh """
            cd $parametersHelm.name
            ls -lha
            echo helm upgrade --install $helmAlias ../../helm-charts/$parametersHelm.name -n $helmAlias --values ./$parametersHelm.name-values.yaml --create-namespace --debug --wait --atomic --cleanup-on-fail
        """
    } else if(comandoHelm.equals('install')){

    }

}
