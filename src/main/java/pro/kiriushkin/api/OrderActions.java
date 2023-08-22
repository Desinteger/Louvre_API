package ru.yandex.praktikum;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderActions {
    private static final String ORDER_PATH = "/orders";

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение списка всех заказов")
    public ValidatableResponse getOrders() {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение заказа по id")
    public void getOrderById(int id) {
        given()
                .log().all()
                .header("Content-type", "application/json")
                .queryParam("id", id).
                when().
                get(ORDER_PATH).
                then().log().all();
    }

    @Step("Удаление заказа по id")
    public void deleteOrderById(int id){
        given()
                .log().all()
                .header("Content-type", "application/json")
                .queryParam("id", id)
                .when()
                .delete(ORDER_PATH)
                .then().log().all();
    }
}
