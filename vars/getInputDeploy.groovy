def getClouds(){
    return ['oci','azure']
}

def getResourceTypes(String cloud){
    if(cloud.equals('oci')){
        return ['container_registry','compute_instance','identity','kubernetes','network','object_storage']
    } else {        
        throw new Exception("No ResourcesType in Cloud: $cloud")
    }
}

def getProjects(String resourceType){   

    if(resourceType.equals('kubernetes')){
        return ['oke_private', 'oke_public']
    } else if(resourceType.equals('identity')) {
        return ['auth_token']
    } else if(resourceType.equals('vnc_default')) {
        return ['auth_token']
    } else if(resourceType.equals('object_storage')) {
        return ['storage_terraform_state']
    } else {
        throw new Exception("No Projects in ResourceType: $resourceType")
    }    
}

def getClusters(String cloud){

    if(cloud.equals('oci')){
        return ['oke-01', 'oke-02']
    } else if(cloud.equals('azure')){
        return ['aks-01', 'aks-02']
    } else if(cloud.equals('azure')){
        return ['aks-01', 'aks-02']
    } else {
        throw new Exception("No clusters in cloud: $cloud")
    }

}

def getHelms(){
    return ['gitlab','wikijs','jenkins','nginx-ingress','nexus','sonar']
}

def getEnvironments(){
    return ['dev','hml','prod','sup']
}

def getSquads(){
    return ['posop','plata','crocre']
}