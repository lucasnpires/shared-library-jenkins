def call (String inputName, String inputDescription,  String inputMessage){
    
    def choiceObject = [
        name: inputName,
        description: inputDescription,
        message: inputMessage
    ]    

    executeInput(choiceObject)
}

def executeInput(Map choiceObject = [:]){
    timeout ( time: 20, unit: "MINUTES" )  {
        def userInput = input(
            id: 'userInput', 
            message: choiceObject.message, 
            parameters: [
                string(
                    name: choiceObject.name,                     
                    description: choiceObject.description,
                    trim: true,
                    defaultValue : '1.0.0'
                )
            ]
        )
        return userInput
    }  
}

