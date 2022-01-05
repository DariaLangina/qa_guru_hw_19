package dlangina.base;

import static dlangina.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Api {

  public static void setUpRestAssured() {
    RestAssured.baseURI = "https://reqres.in";
    RestAssured.basePath = "/";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  public String getInformationOfUserById(int id) {
    return given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .pathParams("id", id)
        .get(EndPoints.user)
        .then()
        .statusCode(200)
        .extract().body().asString();
  }

  public String getTotal() {
    return given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .get(EndPoints.users)
        .then()
        .statusCode(200)
        .extract().body().jsonPath().getString("total");
  }

  public void createUser(String name, String job) {
    given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body("{\"name\": " + "\"" + name + "\"" + ", " + "\"job\": " + "\"" + job + "\"" + "}")
        .post(EndPoints.users)
        .then()
        .statusCode(201)
        .body("id", notNullValue())
        .body("name", is(name))
        .body("job", is(job))
        .body("createdAt", notNullValue());
  }

  public void updateUser(int id, String name, String job) {
    given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body("{\"name\": " + "\"" + name + "\"" + ", " + "\"job\": " + "\"" + job + "\"" + "}")
        .pathParams("id", id)
        .put(EndPoints.user)
        .then()
        .statusCode(200)
        .body("name", is(name))
        .body("job", is(job))
        .body("updatedAt", notNullValue())
        .extract().body().asString();
  }

  public void registrationByApi() {
    given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
        .post(EndPoints.register)
        .then()
        .statusCode(200)
        .body("id", is(4))
        .body("token", is("QpwL5tke4Pnpja7X4"));
  }
}
