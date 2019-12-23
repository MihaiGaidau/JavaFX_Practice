# Intruction to run the application

## using intelliJ IDEA 

1. open a specific project
2. Add a JavaFX module dependency
    1. File
    2. ProjectStructure
    3. Modules
    4. Dependency
    5. Select SDK (preferable the last disponible one)
    6. Add the module (if you have the javaFX library downloaded and extracted skip the first 2 steps)  
        1. Download the javaFX library from [here](https://gluonhq.com/products/javafx) (download for the save version of the installed SDK)
        2. Extract it into a folder
        3. ...back in the IDE
        4. "**+**"
        5. JARs or directories.
        6. select the **lib** folder from the extracted JavaFX library
        7. OK -> Apply -> OK
    7. Add configurations for IDE
       1. Run
       2. Edit configurations
       3. Application
       4. Main
       5. VM options:

                --module-path $(replace this with the path to lib folder of JavaFX library) --add-modules javafx.controls,javafx.fxml

    8. ### run the application


## Note: 
## the path to the library needs to be changed (in IDE) if the project is opened on different machines!!!