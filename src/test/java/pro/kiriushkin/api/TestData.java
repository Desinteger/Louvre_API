package pro.kiriushkin.api;

public class TestData {
    protected final static Integer MODIFIED_COUNT = 1;
    protected final static Integer DELETED_COUNT = 1;
    protected final static String MESSAGE_INVALID_EMAIL = "email must be an email";
    protected final static String MESSAGE_INVALID_PHONE = "phone must be a valid phone number";
    protected final static String MESSAGE_SERVER_ERROR = "Internal server error";
    protected final static String MESSAGE_INVALID_TYPE = "type must be one of the following values: undefined, undefined, undefined";
    protected final static String MESSAGE_INVALID_DATE = "date must be a valid ISO 8601 date string";
    protected static Order updatedOrder = new Order("Permanent exhibition", new Amount(0, 0),
            "2023-08-12T11:41:04.703Z", "test1@test.com", "test1@test.com", "+79915678990");
}
