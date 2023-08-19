package order;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import model.response.OrdersRes;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
@Tag("Получение списка заказов")
public class OrdersListTest {
    @Before
    public void before() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    @DisplayName("В тело ответа возвращается список заказов")
    public void getOrders() {
        OrdersRes ordersRes = given()
                .baseUri("http://qa-scooter.praktikum-services.ru/api/v1/orders")
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("/api/v1/orders")
                .body().as(OrdersRes.class);
        MatcherAssert.assertThat(ordersRes, notNullValue());
    }
}
