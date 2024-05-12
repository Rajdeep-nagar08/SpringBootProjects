package com.example.apachePOI.modelEntity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@Table(name="sales")
@NoArgsConstructor
@Getter
@Setter
@Data
public class SalesModel {

    @Column(name="order_no")
    private Double orderNo;
    @Column(name="item_id")
    private Double itemId;
    @Column(name="sales_date")
    private Date salesDate;
    @Column(name="customer_id")
    private Double customerId;
    @Column(name="city_id")
    private Double cityId;
    private Double qty;
    private Double price;
    private Double cogs;
    @Column(name="discount_percent")
    private Double discountPercent;

}
