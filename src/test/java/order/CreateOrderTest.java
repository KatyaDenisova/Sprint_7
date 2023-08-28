package order;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import model.Client;
import model.order.Orders;
import model.order.OrderGenerator;
import model.order.OrderGeneratorOneColor;
import model.order.OrderGeneratorWithoutColor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

@Tag("Создание заказа")
public class CreateOrderTest {
    private final OrderGenerator order = new OrderGenerator();
    private final OrderGeneratorOneColor orderOne = new OrderGeneratorOneColor();
    private final OrderGeneratorWithoutColor orderWithoutC = new OrderGeneratorWithoutColor();

    @Before
    public void before() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    @DisplayName("Можно указать оба цвета")
    public void postCreateOrderTwoColour() {
        var createdOrder = order.random();
        Orders.createOrder(createdOrder);
    }
    @Test
    @DisplayName("Можно указать один из цветов")
    public void postCreateOrderOneColour() {
        var createdOrder = orderOne.random();
        Orders.createOrder(createdOrder);
    }
    @Test
    @DisplayName("Можно совсем не указывать цвет")
    public void postCreateOrderWithoutColour() {
        var createdOrder = orderWithoutC.random();
        Orders.createOrder(createdOrder);
    }
}
