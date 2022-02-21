package br.com.renner.utils.ChoicesInputs

class ChoicesInputs implements Serializable {

    ArrayList getProjects(){
        return ['agent-jenkins-terraform-base', 'jenkins-master']
    }

    ArrayList getReposRegistrys(){
        return ['us-phoenix-1.ocir.io/axxalonri6o1/devops', 'us-phoenix-1.ocir.io/axxalonri6o1/teste']
    }
}