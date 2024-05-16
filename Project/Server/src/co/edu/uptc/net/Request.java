package co.edu.uptc.net;

public class Request {
    private String action;
    private String data;

    public Request(String action, String data) {
        this.action = action;
        this.data = data;
    }

    // Getters and Setters
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "action='" + action + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
