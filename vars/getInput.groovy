def call (String inputName, String inputDescription, String inputMessage, String [] inputChoices){
    
    def choiceObject = [
        name: inputName,
        description: inputDescription,
        message: inputMessage,        
        choices: inputChoices
    ]    

    executeInput(choiceObject)
}

def executeInput(Map choiceObject = [:]){
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: choiceObject.message, 
            parameters: [
                choice(
                    name: choiceObject.name, 
                    choices: choiceObject.choices,
                    description: choiceObject.description
                )
            ]
        )
        return userInput
    }  
}

