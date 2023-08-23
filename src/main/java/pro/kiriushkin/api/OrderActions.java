package pro.kiriushkin.api;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderActions extends BaseClient {

    private String orderId;
    public void setId(String orderId) {
        this.orderId = orderId;
    }

    private static final String ORDER_PATH = "/orders";
    private String generateOrderIDPath(String id) {
        return ORDER_PATH + "/" + id;
    }

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
        orderId = response.extract().path("_id");
        return response;
    }

    @Step("Получение списка всех заказов")
    public ValidatableResponse getOrders() {
        return given()
                .get(ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение заказа по id")
    public void getOrderById(String orderId) {
        String orderPathWithID = generateOrderIDPath(orderId);
        given()
                .header("Content-type", "application/json")
                .when()
                .get(orderPathWithID)
                .then().log().all();
    }

    @Step("Обновление данных о заказе")
    public ValidatableResponse updateOrderData(String orderId, Order updatedOrder) {
        String orderPathWithID = generateOrderIDPath(orderId);
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .body(updatedOrder)
                .when()
                .patch(orderPathWithID)
                .then().log().all();
        return response;
    }

    @Step("Удаление заказа по id")
    public ValidatableResponse deleteOrderById(String id){
        String orderPathWithID = generateOrderIDPath(id);
        ValidatableResponse response = given()
                .when()
                .delete(orderPathWithID)
                .then().log().all();
        return response;
    }
}
