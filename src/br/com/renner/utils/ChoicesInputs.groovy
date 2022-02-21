package br.com.renner.utils

class ChoicesInputs implements Serializable {

    ArrayList getProjects(){
        return ['agent-jenkins-terraform-base', 'jenkins-master']
    }

    ArrayList getReposRegistrys(){
        return ['us-phoenix-1.ocir.io/axxalonri6o1/devops', 'us-phoenix-1.ocir.io/axxalonri6o1/teste']
    }

    ArrayList getClouds(){
        return ['oci','azure']
    }

    ArrayList getResourceTypes(String cloud){
        if(cloud.equals('oci')){
            return ['container_registry','compute_instance','identity','kubernetes','network','object_storage']
        } else {        
            throw new Exception("No ResourcesType in Cloud: $cloud")
        }
    }

    ArrayList getProjects(String resourceType){   

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

    ArrayList getClusters(String cloud){

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

    ArrayList getHelms(){
        return ['gitlab','wikijs','jenkins','nginx-ingress','nexus','sonar']
    }

    ArrayList getEnvironments(){
        return ['dev','hml','prod','sup']
    }

    ArrayList getSquads(){
        return ['posop','plata','cocre']
    }
}