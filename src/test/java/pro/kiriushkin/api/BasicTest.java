package pro.kiriushkin.api;
import io.restassured.RestAssured;
import org.junit.Before;

public class BasicTest {

    @Before
    public void setUp() {
        RestAssured.baseURI= "https://api.kiriushkin.pro/louvre-api";
    }
}
