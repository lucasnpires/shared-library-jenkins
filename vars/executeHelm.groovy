def call(String comandoHelm, Map parametersHelm = [:]) {

    def helmAlias = "$parametersHelm.squad-$parametersHelm.name-$parametersHelm.environmentDeploy"

    if(comandoHelm.equals('validate')){
        sh """
            cd $parametersHelm.name            
            echo helm upgrade --install $helmAlias ../../helm-charts/$parametersHelm.name -n $helmAlias --values ./$parametersHelm.name-values.yaml --create-namespace --debug --wait --atomic --cleanup-on-fail --timeout 5m --dry-run
        """
    } else if(comandoHelm.equals('install')){
        sh """
            // login na cloud
            cd $parametersHelm.name            
            echo helm upgrade --install $helmAlias ../../helm-charts/$parametersHelm.name -n $helmAlias --values ./$parametersHelm.name-values.yaml --create-namespace --debug --wait --atomic --cleanup-on-fail --timeout 5m
        """
    }

}
