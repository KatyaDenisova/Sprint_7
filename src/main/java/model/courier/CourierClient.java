package model.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Client;
import model.request.Courier;
import model.request.WrongCredentials;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierClient extends Client {

    static final String COURIER_API = "/api/v1/courier";
    static final String COURIER_DELETE = "/api/v1/courier/:id";
    private final WrongCredentials wrongCredentials = new WrongCredentials();

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_API)
                .then().log().all();
    }
    @Step("Логин")
    public ValidatableResponse logIn(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_API + "/login")
                .then().log().all();
    }
    @Step("Логин с неверными параметрами")
    public ValidatableResponse logInWrong(WrongCredentials wrongCredentials) {
        return spec()
                .body(wrongCredentials)
                .when()
                .post(COURIER_API + "/login")
                .then().log().all();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(int courierID) {
        return spec()
                .body(courierID)
                .when()
                .delete(COURIER_DELETE  + "courierID")
                .then().log().all();
    }
    @Step("Курьер без кредов")
    public ValidatableResponse courierWithoutFirlds() {
        return spec()
                .body(wrongCredentials)
                .when()
                .post(COURIER_API)
                .then().log().all();
    }
}

