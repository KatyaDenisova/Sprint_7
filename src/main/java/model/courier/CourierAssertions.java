package model.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierAssertions extends CourierClient{

    @Step("Создание курьера успешно")
    public ValidatableResponse createCourierSuccess(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("ok",equalTo(true));
    }

    @Step("Успешный логин")
    public int logInSuccess(ValidatableResponse response){
    return response
        .assertThat()
        .statusCode(200)
            .body("id",notNullValue())
        .extract()
        .path("id");
    }
    @Step("Курьер не создан, недостаточно данных для создания учетной записи")
    public ValidatableResponse notCreated(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("code",equalTo(400))
                .body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Step("Неуспешный логин, учетная запись не найдена")
    public ValidatableResponse logInFalse(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
