package model.response;

import java.util.List;

public class OrdersRes {
    private List<OrdersList> orders;
    private PageInfo pageInfo;
    private List<AvailableStations> availableStations;


    public List<OrdersList> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersList> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<AvailableStations> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<AvailableStations> availableStations) {
        this.availableStations = availableStations;
    }
}
