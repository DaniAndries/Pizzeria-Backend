package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Payable {
    private int id;
    private Date orderDate;
    private OrderState state;
    private PaymentMethod paymentMethod;
    private List<OrderLine> orderLines = new ArrayList<>();
    private Client client;

    public Order(int id, Date orderDate, OrderState state, PaymentMethod paymentMethod, List<OrderLine> orderLines, Client client) {
        this.id = id;
        this.orderDate = orderDate;
        this.state = state;
        this.paymentMethod = paymentMethod;
        this.orderLines = orderLines;
        this.client = client;
    }

    public Order(int id, Date orderDate, OrderState state, PaymentMethod paymentMethod, Client client) {
        this.id = id;
        this.orderDate = orderDate;
        this.state = state;
        this.paymentMethod = paymentMethod;
        this.client = client;
    }

    public Order(int id, Date orderDate, OrderLine orderLine, Client client) {
        this.id = id;
        this.orderDate = orderDate;
        this.state = OrderState.PENDING;
        this.orderLines.add(orderLine);
        this.client = client;
    }

    public void finalizar() {
        getTotalPrice();
        pay(getTotalPrice());
    }

    @Override
    public void pay(double amount) {
        setState(OrderState.PAID);
        payByCash(amount);
        payByCard(amount);
    }

    @Override
    public void payByCard(double amount) {
        this.paymentMethod = PaymentMethod.CARD;
    }

    @Override
    public void payByCash(double amount) {
        this.paymentMethod = PaymentMethod.CASH;
    }

    public double getTotalPrice() {
        double auxiliaryPrice = 0;
        for (OrderLine orderLine : orderLines) {
            auxiliaryPrice += orderLine.calculateLinePrice();
        }
        return auxiliaryPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Client getClient() {
        return client;
    }
}
