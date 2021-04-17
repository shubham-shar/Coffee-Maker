# Coffee-Maker

Backend app to update the user with the status of their ordered beverage.
Maven is being used to handle dependencies and packaging of the Java service.

## Getting Started
Clone the github Repo and import in intellij (or your choice of IDE) \
**Notes:** 
- If you have the zipped project, just unzip it and import in any IDE
- The config file for main program can be found in `src/main/config/` folder
- The config files for test program can be found in `src/test/config/` folder

## Built With
* Java 11
* [Maven](https://maven.apache.org/)

### Prerequisites
- Java 11
- Maven 
- Intellij (or your choice of IDE)

### Installing
- Install java 11 \
  You can use [sdman](https://sdkman.io/install) and choose 11.0.9.j9-adpt as java<br>
  `sdk install java 11.0.9.j9-adpt`
- Install Maven \
  Visit [this link](https://maven.apache.org/install.html) in order to install maven in your local.

### Running the project
- To run Test cases, execute below command in terminal at root level
    ```
    mvn test
    ```
- To run the program, execute below commands in terminal at root level
    ```
    mvn package
    java -cp target/coffee-maker-1.0.jar com.assignment.coffeemaker.MainApplication src/main/config/machine.json
    ```
  
## Improvements
- Logic to make beverage can be improved (as of now, the beverages are made in the order they are sent)
- More testing needs to be done.

## Authors
- **Shubham Sharma** - *Owner* - [Github](https://github.com/shubham-shar)

## Acknowledgments
- [Baeldung](https://www.baeldung.com)
- [StackoverFlow](https://stackoverflow.com/)