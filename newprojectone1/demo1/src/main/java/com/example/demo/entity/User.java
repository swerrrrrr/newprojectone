package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.PrivateKey;
import java.util.Date;

@TableName("plan")

@Data
public class User {
    @TableId(value = "PlannedOrder_number")
    private Integer PlannedOrder_number;
    private String Produce_name;
    private Integer Produce_number;
    private String Material_name;
    private Integer Material_number;
    private Integer Work_number;
    private String Device_number;
    private Date Planned_time;

}
