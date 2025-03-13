package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import listeners.ExtentITestListener;

import static io.restassured.RestAssured.*;

public class APIStepDefs {

    Response response;

    @Given("I call GET API endpoint {string}")
    public void i_call_get_api(String endpoint) {
        try {
            ExtentITestListener.getTest().info("Calling GET endpoint: " + endpoint);

            response = given()
                    .log().all()
                    .when()
                    .get(endpoint);

            ExtentITestListener.getTest().info("Response: " + response.getBody().asString());
        } catch (Exception e) {
            ExtentITestListener.getTest().fail("GET API failed with exception: " + e.getMessage());
        }
    }

    @Then("I should receive status code {int}")
    public void i_should_receive_status_code(Integer statusCode) {
        try {
            response.then().statusCode(statusCode);
            ExtentITestListener.getTest().pass("Received expected status code: " + statusCode);
            ExtentITestListener.getTest().pass("Received expected status code: " + statusCode);
        } catch (AssertionError ae) {
            ExtentITestListener.getTest().fail("Expected status code: " + statusCode + ", but got: " + response.statusCode());
        }
    }
}
