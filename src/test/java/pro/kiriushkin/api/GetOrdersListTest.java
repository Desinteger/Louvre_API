package pro.kiriushkin.api;

import org.junit.After;
import org.junit.Test;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.junit4.DisplayName;
import static java.net.HttpURLConnection.HTTP_OK;

@DisplayName("Тесты: получение списка заказов")
public class GetOrdersListTest extends BasicTest {
    OrderActions orderActions = new OrderActions();

      @DisplayName("Тест: получение списка заказов")
      @Test
      public void testGettingOrdersList() {
          ValidatableResponse response = orderActions.getOrders();

          response.assertThat()
                  .statusCode(equalTo(HTTP_OK))
                  .body("orders", hasSize(greaterThan(0)));
        }
}