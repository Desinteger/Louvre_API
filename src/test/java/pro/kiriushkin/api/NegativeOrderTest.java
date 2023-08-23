package pro.kiriushkin.api;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Негативные тесты: заказ")
public class NegativeOrderTest extends BasicTest {
    OrderActions orderActions = new OrderActions();
    OrderGenerator generator = new OrderGenerator();

    @DisplayName("Тест: создание заказа с невалидным email")
    @Test
    public void testCreatingOrderWithInvalidEmail(){
        Order order = generator.generic();
        order.setEmail("test@test.");
        ValidatableResponse response = orderActions.createOrder(order);

        response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message[0]", equalTo(TestData.MESSAGE_INVALID_EMAIL));
    }

    @DisplayName("Тест: создание заказа с невалидным номером телефона")
    @Test
    public void testCreatingOrderWithInvalidPhone(){
        Order order = generator.generic();
        order.setPhone("+332352test");
        ValidatableResponse response = orderActions.createOrder(order);

        response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message[0]", equalTo(TestData.MESSAGE_INVALID_PHONE));
    }

    @DisplayName("Тест: создание заказа с пустым полем имени")
    @Test
    public void testCreatingOrderWithInvalidName(){
        Order order = generator.generic();
        order.setName("");
        ValidatableResponse response = orderActions.createOrder(order);

        response.assertThat()
                .statusCode(HTTP_INTERNAL_ERROR)
                .body("message", equalTo(TestData.MESSAGE_SERVER_ERROR));
    }

    @DisplayName("Тест: создание заказа с невалидным полем type")
    @Test
    public void testCreatingOrderWithInvalidType(){
        Order order = generator.generic();
        order.setType("Test type");
        ValidatableResponse response = orderActions.createOrder(order);

        response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message[0]", equalTo(TestData.MESSAGE_INVALID_TYPE));
    }

    @DisplayName("Тест: создание заказа с невалидным полем date")
    @Test
    public void testCreatingOrderWithInvalidDate(){
        Order order = generator.generic();
        order.setDate("1990-09-17-18:12:23");
        ValidatableResponse response = orderActions.createOrder(order);

        response.assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message[0]", equalTo(TestData.MESSAGE_INVALID_DATE));
    }
}
