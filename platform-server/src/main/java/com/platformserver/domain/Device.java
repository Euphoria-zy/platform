package com.platformserver.domain;

import lombok.Data;

@Data
public class Device {
    private String ServiceName;
    private String Location;
    private String Orientation;
    private String DeviceType;
    private String Ip;
    private String Admin;
    private String Password;
}
