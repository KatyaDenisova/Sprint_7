package courier;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Client;
import model.courier.CourierAssertions;
import model.courier.CourierClient;
import model.courier.CourierGenerator;
import model.courier.Credentials;
import model.request.WrongCredentials;
import model.response.OrdersRes;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@Tag("Логин курьера")
public class LoginCourierTest {

    public final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    int courierID;
    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    @DisplayName("Курьер может авторизоваться")
    public void postLoginCourier() {
        var courier = generator.random();
    ValidatableResponse courierRes = client.createCourier(courier);
    check.createCourierSuccess(courierRes);
    Credentials creds = Credentials.from(courier);
    ValidatableResponse logInRes = client.logIn(creds);
    check.logInSuccess(logInRes);
    }

    @Test
    @DisplayName("Для авторизации нужно передать все обязательные поля")
    public void postLoginCourierAuthFields() {
        var courier = generator.random();
        ValidatableResponse courierRes = client.createCourier(courier);
        check.createCourierSuccess(courierRes);
        WrongCredentials wrongCredentials = WrongCredentials.from(courier);
        client.logInWrong(wrongCredentials)
                .assertThat()
                .statusCode(400)
                        .contentType(ContentType.JSON)
                        .body("code", equalTo(400))
                        .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Система вернёт ошибку, если неправильно указать логин или пароль")
    public void postLoginCourierWrongFields() {
        String wrongJson = "{\"login\": \"YOnat1h7\",\"password\": \"gena123\", \"firstName\": \"Букин\"}";
           ValidatableResponse response = given()
                    .baseUri("http://qa-scooter.praktikum-services.ru")
                    .body(wrongJson)
                    .when()
                    .post("/api/v1/courier/login")
                    .then().log().all();
                    check.logInFalse(response);
        }

    @After
    public void deleteCourier(){
        if (courierID > 0)
          client.deleteCourier(courierID);
    }
}
