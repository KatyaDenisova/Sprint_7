package model.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Client;
import model.request.Order;

import static org.hamcrest.CoreMatchers.notNullValue;

public class Orders extends Client {
    static final String ORDER_API = "/api/v1/orders";

    @Step("Создание заказа")
    public static ValidatableResponse createOrder(Order createdOrder) {
        return spec()
                .body(createdOrder)
                .when()
                .post(ORDER_API)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("track", notNullValue());
    }

}
