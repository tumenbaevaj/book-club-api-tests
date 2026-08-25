package api;

import io.qameta.allure.Step;
import models.registration.BlankUsernameRegistrationResponseModel;
import models.registration.ExistingUserResponseModel;
import models.registration.RegistrationBodyModel;
import models.registration.SuccessfulRegistrationResponseModel;
import models.user.*;

import static io.restassured.RestAssured.given;
import static specs.BaseSpec.baseRequestSpec;
import static specs.registration.RegistrationSpec.*;
import static specs.user.UpdateUserSpec.*;

public class UsersApiClient {

    @Step("Register user")
    public SuccessfulRegistrationResponseModel register(RegistrationBodyModel body) {
        return given(baseRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .spec(successfulRegistrationResponseSpec)
                .extract()
                .as(SuccessfulRegistrationResponseModel.class);
    }

    @Step("Register existing user")
    public ExistingUserResponseModel registerExistingUser(RegistrationBodyModel body) {
        return given(baseRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .spec(existingUserRegistrationResponseSpec)
                .extract()
                .as(ExistingUserResponseModel.class);
    }

    @Step("Register user with blank username")
    public BlankUsernameRegistrationResponseModel registerBlankUsername(RegistrationBodyModel body) {
        return given(baseRequestSpec)
                .body(body)
                .when()
                .post("/users/register/")
                .then()
                .spec(blankUsernameRegistrationResponseSpec)
                .extract()
                .as(BlankUsernameRegistrationResponseModel.class);
    }

    @Step("Update user with PUT")
    public UpdateUserResponseModel updateUserPut(UpdateUserBodyModel body, String accessToken) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .put("/users/me/")
                .then()
                .spec(successfulUpdateUserResponseSpec)
                .extract()
                .as(UpdateUserResponseModel.class);
    }

    @Step("Update user with PATCH")
    public UpdateUserResponseModel updateUserPatch(PatchUserBodyModel body, String accessToken) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .patch("/users/me/")
                .then()
                .spec(successfulUpdateUserResponseSpec)
                .extract()
                .as(UpdateUserResponseModel.class);
    }

    @Step("Update user with invalid PUT data")
    public InvalidPutUpdateUserResponseModel updateUserPutInvalid(
            InvalidPutUpdateUserBodyModel body,
            String accessToken) {

        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .put("/users/me/")
                .then()
                .spec(invalidPutUpdateUserResponseSpec)
                .extract()
                .as(InvalidPutUpdateUserResponseModel.class);
    }

    @Step("Get current user")
    public UpdateUserResponseModel getCurrentUser(String accessToken) {
        return given(baseRequestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/users/me/")
                .then()
                .spec(successfulGetUserResponseSpec)
                .extract()
                .as(UpdateUserResponseModel.class);
    }

    @Step("Delete current user")
    public void deleteCurrentUser(String accessToken) {
        given(baseRequestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete("/users/me/")
                .then()
                .spec(successfulDeleteUserResponseSpec);
    }
}
