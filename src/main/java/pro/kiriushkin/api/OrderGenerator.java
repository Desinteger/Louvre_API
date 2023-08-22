package pro.kiriushkin.api;

import org.apache.commons.lang3.RandomStringUtils;

public class OrderGenerator {
    Amount amount = new Amount(0, 0);
    public Order generic() {
        return new Order("Permanent exhibition", amount, "2023-08-17T18:12:23.072Z", "test", "test@test.com", "+33299311199");
    }
    public static String generateRandomEmail(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return RandomStringUtils.random(length, allowedChars) + "@example.com";
    }

    public static String generateRandomPhoneNumber() {
        String digits = "123456789";
        return "+33" + RandomStringUtils.random(9, digits);
    }

    public Order random() {
        String randomEmail = generateRandomEmail(10);
        Amount amount = new Amount(0, 0);
        String randomName = RandomStringUtils.randomAlphanumeric(5, 10);
        String randomPhone = generateRandomPhoneNumber();
        return new Order("Combined Admission", amount, "2023-08-17T18:12:23.072Z", randomName, randomEmail, randomPhone);
    }
}
