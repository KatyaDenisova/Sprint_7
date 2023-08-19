package model.order;

import model.request.Order;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class OrderGenerator {
    public Order random(){return new Order("Дарья","Букина","ул.Академика Королева 12","Владыкино", RandomStringUtils.randomNumeric(7,13),5,"2023.07.11","комент", List.of("BLACK,GREY"));
    }
}

