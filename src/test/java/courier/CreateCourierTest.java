package courier;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.courier.CourierAssertions;
import model.courier.CourierClient;
import model.courier.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

@Tag("Создание курьера")
public class CreateCourierTest {

    public final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();

    private final CourierAssertions check = new CourierAssertions();
    int courierID;

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    @DisplayName("Курьера можно создать")
    public void postCreateCourier() {
        var courier = generator.random();
        ValidatableResponse courierRes = client.createCourier(courier);
        check.createCourierSuccess(courierRes);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void postCantCreateCourier() {
        var courier = generator.random();
        ValidatableResponse courierRes = client.createCourier(courier);
        check.createCourierSuccess(courierRes);
        client.createCourier(courier)
                .statusCode(409)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }


    @Test
    @DisplayName("Чтобы создать курьера, нужно передать в ручку все обязательные поля")
    public void postCourierRequiredFields() {
        ValidatableResponse courierRes = client.courierWithoutFirlds();
        check.notCreated(courierRes);
    }


    @Test
    @DisplayName("Успешный запрос возвращает ok: true")
    public void postCreateCourierAnswer() {
        var courier = generator.random();
        client.createCourier(courier)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("ok", equalTo(true));
    }

    @After
    public void deleteCourier(){
         if (courierID > 0)
       client.deleteCourier(courierID);
    }
}

