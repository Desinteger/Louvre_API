package pro.kiriushkin.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Позитивные тесты: заказ")
public class PositiveOrderTest extends BasicTest {
        OrderActions orderActions = new OrderActions();
        OrderGenerator generator = new OrderGenerator();
        String orderId;

        @Before
            public void createOrderAndGetOrderId() {
               Order order = generator.random();
               ValidatableResponse response = orderActions.createOrder(order);
               orderId = response.extract().path("_id");
        }


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

        @DisplayName("Тест: Получение заказа по id")
        @Test
        public void testGetOrderById() {
            orderActions.getOrderById(orderId);

            given()
                    .get("/orders/" + orderId)
                    .then()
                    .statusCode(HTTP_OK)
                    .body("_id", equalTo(orderId));
    }

    @DisplayName("Тест: обновление данных о заказе")
    @Test
    public void testUpdatingOrderData() {
        ValidatableResponse updateResponse = orderActions.updateOrderData(orderId, TestData.updatedOrder);

        updateResponse.assertThat()
                .statusCode(equalTo(HTTP_OK))
                .body("modifiedCount", equalTo(TestData.MODIFIED_COUNT));
    }

    @DisplayName("Тест: удаление заказа по id")
    @Test
    public void testDeletingOrderById(){
            ValidatableResponse deleteResponse = orderActions.deleteOrderById(orderId);
            deleteResponse.assertThat()
                .statusCode(equalTo(HTTP_OK))
                .body("deletedCount", equalTo(TestData.DELETED_COUNT));
    }
}
