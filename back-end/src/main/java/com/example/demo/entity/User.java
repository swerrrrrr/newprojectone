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
    @TableId(value = "plannedOrder_number")
    private int plannedOrder_number;
    private String produce_name;
    private int produce_number;
    private String material_name;
    private int material_number;
    private int work_number;
    private String device_number;
    private Date planned_time;

}
