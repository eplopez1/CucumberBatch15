package Utils;

import org.apache.log4j.*;

public class Log {

    public static Logger log= Logger.getLogger(Log.class.getName());



    //If I want to print any message in between, I should print the logs

    public static void startTestCase(String testCaseName )
    // When the test starts, I should print the logs
    {
        log.info("*****************************");
        log.info("*****************************");
        log.info("********"+testCaseName+"********");

    }

    public static void endTestCAse(String testCaseName)
    //When my test stops, I should print the logs
    {
        log.info("###################################");
        log.info("###################################");
        log.info("############"+testCaseName+"############");


    }

    public static void info(String message)
    {
        log.info(message);
    }

    public static void  warning(String warning)
    {
        log.info(warning);

    }




    //==============================Maven Lifecycle======================

    //MAVEN LIFE CYCLE: This consists of several phase
    //clean:removes all the files generated by the previous build
    //validate: Checks the projects is correct and all the necessary information is available
    // compile: compiles the source code of the project
    //test: runs the test for the project
    // package: package the compiled code into a distributable format, such as JAR or WAR files
    // verify: runs and checks on results of integration tests to ensure quality criteria is met
    // install: installs the packages into local repository(popular interview question)
    // site: generates documentation for the project (for Developers)
    // deploy: copies the final package to remote repository for sharing with other developers or projects

}