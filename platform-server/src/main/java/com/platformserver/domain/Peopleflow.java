package com.platformserver.domain;

import lombok.Data;

@Data
public class Peopleflow {
    private Integer id;
    private String deviceIp;
    private String sendTime;
    private Integer enterNum;
    private Integer exitNum;
}
