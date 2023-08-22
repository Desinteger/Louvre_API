package pro.kiriushkin.api;

public class Order {
    private String type;
    private String date;
    private String name;
    private String email;
    private String phone;
    private Amount amount;

    public Order(String type, Amount amount, String date, String name, String email, String phone){
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Order() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
