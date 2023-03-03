package com.platformserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platformserver.dao.DeviceDao;
import com.platformserver.dao.PeopleDao;
import com.platformserver.domain.Device;
import com.platformserver.domain.Peopleflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class RestTemplateController {

        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private PeopleDao peopleDao;
        @Autowired
        private DeviceDao deviceDao;

        /**********HTTP POST method**************/
        @PostMapping(value = "/testPost")
        public Object postJson(@RequestBody JSONObject param) {
            System.out.println(param.toJSONString());
            param.put("action", "post");
            param.put("username", "tester");
            param.put("pwd", "123456748");
            return param;
        }

        @PostMapping(value = "/testPostApi")
        public Object testPost() {
            String url = "http://localhost:80/services/PeopleInfoByTime";
            JSONObject postData = new JSONObject();
            postData.put("sendTime","2023-02-28 9:18:00");
            JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
            JSONArray datebase = json.getJSONArray("date");
            for (int i = 0; i <datebase.size(); i++) {
                Peopleflow demo = new Peopleflow();

                JSONObject jsonObject1 = datebase.getJSONObject(i);
                System.out.println(jsonObject1);

                Integer id = jsonObject1.getInteger("id");
                demo.setId(id);

                String device_ip = jsonObject1.getString("device_type");
                demo.setDeviceIp(device_ip);

                String send_time = jsonObject1.getString("send_time");
                demo.setSendTime(send_time);

                Integer enter_num = jsonObject1.getInteger("enter_num");
                demo.setEnterNum(enter_num);

                Integer exit_num = jsonObject1.getInteger("exit_num");
                demo.setExitNum(exit_num);
                //将数据插入数据库
                peopleDao.insert(demo);
            }
            return json;
        }

    @PostMapping(value = "/testDevice")
    public Object testDevice() {
        String url = "http://localhost:80/services/SelectDeviceInfo";
        JSONObject postData = new JSONObject();
        postData.put("","");
        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
        JSONArray datebase = json.getJSONArray("date");
        for (int i = 0; i <datebase.size(); i++) {
            Device device = new Device();

            JSONObject jsonObject1 = datebase.getJSONObject(i);
            System.out.println(jsonObject1);

            String ServiceName = jsonObject1.getString("serviceName");
            device.setServiceName(ServiceName);

            String Location = jsonObject1.getString("location");
            device.setLocation(Location);

            String Orientation = jsonObject1.getString("orientation");
            device.setOrientation(Orientation);

            String Ip = jsonObject1.getString("ip");
            device.setIp(Ip);

            String DeviceType = jsonObject1.getString("deviceType");
            device.setDeviceType(DeviceType);

            String Admin = jsonObject1.getString("admin");
            device.setAdmin(Admin);

            String Password = jsonObject1.getString("password");
            device.setPassword(Password);

            //将数据插入数据库
            deviceDao.insert(device);
        }
        return json;
    }
}
