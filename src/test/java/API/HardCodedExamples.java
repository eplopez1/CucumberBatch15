package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI= RestAssured.baseURI= "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODUyOTAxMDgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTMzMzMwOCwidXNlcklkIjoiNTM5NiJ9.KEfIWXtfOzKfLTIUaWHIcfFA9QTQh1GcvmFxwQy99YI";
    static  String employee_id;

    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification preparedRequest=given().
                header("Content-Type","application/json")
                .header("Authorization",token).queryParam("employee_id",employee_id);

        //hitting the endpoint
        Response response=preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        //verify the response
        response.then().assertThat().statusCode(200);

        String tempEmpId=response.jsonPath().getString("employee.employee_id");

        // we have two empid, one is global and second is local
        Assert.assertEquals(employee_id,tempEmpId);



    }
    @Test
    public void acreateEmployee() {
        // prepare the Request

        RequestSpecification preparedRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Chris\",\n" +
                        "  \"emp_lastname\": \"Pun\",\n" +
                        "  \"emp_middle_name\": \"Big\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-05-20\",\n" +
                        "  \"emp_status\": \"Employeed\",\n" +
                        "  \"emp_job_title\": \"Rapper\"\n" +
                        "}");

        // hitting the endpoint
        Response response = preparedRequest.when().post("/createEmployee.php");

        // verifying the assertions

        response.then().assertThat().statusCode(201);
        //it will print the ouput in the console

        //we are capturing employee id from the response
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        response.prettyPrint();
        //verifying the firstname
        response.then().assertThat()
                .body("Employee.emp_firstname", equalTo("Chris"));
        response.then().assertThat()
                .body("Employee.emp_lastname", equalTo("Pun"));

        // verify the response headers
        response.then().assertThat().header("Content-Type", "application/json");
        System.out.println("My test case is passed");

    }

        //in homework, create a method to get all emoloyee status and job title
        @Test
        public void cupdateEmployee(){
            RequestSpecification preparedRequest = given().
                    header("Content-Type","application/json").
                    header("Authorization", token).body("{ " +
                            "\"employee_id\": \""+employee_id+"\",\n" +
                            "        \"emp_firstname\": \"Chris\",\n" +
                            "        \"emp_lastname\": \"Pun\",\n" +
                            "        \"emp_middle_name\": \"Big\",\n" +
                            "        \"emp_gender\": \"M\",\n" +
                            "        \"emp_birthday\": \"2003-05-20\",\n" +
                            "        \"emp_status\": \"shakey\",\n" +
                            "        \"emp_job_title\": \"ninja\"}");

            //hitting the endpoint
            Response response = preparedRequest.when().put("/updateEmployee.php");
            response.then().assertThat().statusCode(200);
            //for verification
            response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        }
    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification preparedRequest = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                queryParam("employee_id",employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        //if you want to verify the body of the response.
        //you can do that using hamcrest matchers

    }

    }


