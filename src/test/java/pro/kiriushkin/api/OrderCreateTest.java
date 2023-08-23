package pro.kiriushkin.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Тесты: создание заказа")
public class OrderCreateTest extends BasicTest {
    OrderActions orderActions = new OrderActions();
    OrderGenerator generator = new OrderGenerator();
    String orderId;

    @After
    public void deleteOrderAfterTest() { orderActions.deleteOrderById(orderId); }

    @DisplayName("Тест: Создание заказа")
    @Test
    public void testOrderCreating() {
        Order order = generator.random();
        ValidatableResponse response = orderActions.createOrder(order);
        orderId = response.extract().path("_id");

        response.assertThat()
                .statusCode(HTTP_CREATED)
                .body("_id", notNullValue());
    }
}
