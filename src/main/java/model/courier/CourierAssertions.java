package model.courier;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourierAssertions extends CourierClient{
    private final CourierClient client = new CourierClient();

    public void createCourierSuccess(ValidatableResponse response){
        boolean created = response
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
        assert created;
    }

    public int logInSuccess(ValidatableResponse response){
    return response
        .assertThat()
        .statusCode(200)
            .body("id",notNullValue())
        .extract()
        .path("id");
    }
    public ValidatableResponse notCreated(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("code",equalTo(400))
                .body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }
    public ValidatableResponse logInFalse(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
