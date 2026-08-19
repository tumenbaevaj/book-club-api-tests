package specs.user;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateUserSpec {

    public static ResponseSpecification successfulUpdateUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath(
                    "schemas/user/successful_update_user_response_schema.json"))
            .expectBody("id", notNullValue())
            .expectBody("username", notNullValue())
            .build();

    public static ResponseSpecification invalidPutUpdateUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .expectBody(matchesJsonSchemaInClasspath(
                    "schemas/user/invalid_put_update_user_response_schema.json"))
            .expectBody("firstName", notNullValue())
            .expectBody("lastName", notNullValue())
            .expectBody("email", notNullValue())
            .build();

    public static ResponseSpecification successfulGetUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("id", notNullValue())
            .expectBody("username", notNullValue())
            .build();

    public static ResponseSpecification successfulDeleteUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(204)
            .build();
}
