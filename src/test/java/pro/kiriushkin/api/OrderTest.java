package pro.kiriushkin.api;

import org.junit.Test;
import org.junit.After;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Тесты: заказ")
public class OrderTest extends BasicTest {
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
                    .and()
                    .body("_id", notNullValue());
        }
        @DisplayName("Тест: Получение заказа по id")
        @Test
        public void testGetOrderById() {
            Order order = generator.random();
            ValidatableResponse createResponse = orderActions.createOrder(order);
            orderId = createResponse.extract().path("_id");

            orderActions.getOrderById(orderId);

            given()
                    .when()
                    .get("/orders/" + orderId)
                    .then()
                    .statusCode(HTTP_OK)
                    .and()
                    .body("_id", equalTo(orderId));
    }

    @DisplayName("Тест: обновление данных о заказе")
    @Test
    public void testUpdatingOrderData() {
        Order order = generator.generic();
        ValidatableResponse createResponse = orderActions.createOrder(order);
        String orderId = createResponse.extract().path("_id");

        ValidatableResponse updateResponse = orderActions.updateOrderData(orderId, TestData.updatedOrder);

        updateResponse.assertThat()
                .statusCode(equalTo(HTTP_OK))
                .body("modifiedCount", equalTo(TestData.modifiedCount));
    }
}
