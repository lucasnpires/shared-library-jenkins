def call (){                      
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: 'Preencha as informações para executar a pipeline', 
            parameters: [
                [$class: 'ChoiceParameter', 
                    choiceType: 'PT_SINGLE_SELECT', 
                    description: 'Select the Environemnt from the Dropdown List', 
                    filterLength: 1, 
                    filterable: false, 
                    name: 'Env', 
                    script: [
                        $class: 'GroovyScript', 
                        fallbackScript: [
                            classpath: [], 
                            sandbox: false, 
                            script: 
                                "return['Could not get The environemnts']"
                        ], 
                        script: [
                            classpath: [], 
                            sandbox: false, 
                            script: 
                                "return['dev','stage','prod']"
                        ]
                    ]
                ],
                //choice(
                //    name: 'Cloud', 
                //    choices: getClouds(),
                //    description: 'Cloud'
                //),                                
                //choice(
                //    name: 'ProjectName', 
                //    choices: getProjects('oci'),
                //    description: 'Nome do Projeto'
                //),
                //choice(
                //    name: 'ResourceType', 
                //    choices: ['kubernetes','compute_instance',''],
                //    description: 'Resource Type'
                //)
            ]
        )

        return userInput
    }    
}

def getClouds(){
    return ['oci','azure']
}

def getProjects(String cloud){

    if(cloud.equals('oci')){
        return ['oke_public', 'oke_private']
    } else {
        return ['No Projects in Cloud']
    }
    
}





service_tier_map = [
  "web": [
    ["service_name": "user_frontend", "release_tag": "1.0.0" ],
    ["service_name": "admin_frontend", "release_tag": "1.0.2" ],
  ],
  "backend": [
    ["service_name": "admin_service", "release_tag": "2.1.0" ],
    ["service_name": "finance_service", "release_tag": "2.2.0" ],
    ["service_name": "payment_service", "release_tag": "3.2.0" ],
  ],
  "database": [
    ["service_name": "dynamo_db", "release_tag": "5.4.1"],
    ["service_name": "mysql", "release_tag": "3.2.1"],
    ["service_name": "postgresql", "release_tag": "1.2.3"],
  ],
]

html_to_be_rendered = "<table><tr>"
service_list = service_tier_map[tier]

service_list.each { service ->
  html_to_be_rendered = """
    ${html_to_be_rendered}
    <tr>
    <td>
    <input name=\"value\" alt=\"${service.service_name}\" json=\"${service.service_name}\" type=\"checkbox\" class=\" \">
    <label title=\"${service.service_name}\" class=\" \">${service.service_name}</label>
    </td>
    <td>
    <input type=\"text\" class=\" \" name=\"value\" value=\"${service.release_tag}\"> </br>
    </td>
    </tr>
"""
}


html_to_be_rendered = "${html_to_be_rendered}</tr></table>"

return html_to_be_rendered