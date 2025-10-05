package com.gec.resp;


import com.gec.entity.User;
import lombok.Data;

@Data
public class UserResp extends User {    private String token; private String power;
}
