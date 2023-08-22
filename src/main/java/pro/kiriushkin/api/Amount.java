package pro.kiriushkin.api;

public class Amount {
    private int basic;
    private int senior;
    public Amount(int basic, int senior){
        this.basic = basic;
        this.senior = senior;
    }

    public int getBasic() {
        return basic;
    }

    public void setBasic(int basic) {
        this.basic = basic;
    }

    public int getSenior() {
        return senior;
    }

    public void setSenior(int senior) {
        this.senior = senior;
    }
}
