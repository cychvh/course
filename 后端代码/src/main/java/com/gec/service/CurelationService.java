package com.gec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.entity.Curelation;

/**
* @author Administrator
* @description 针对表【curelation】的数据库操作Service
* @createDate 2024-11-12 21:07:04
*/
public interface CurelationService extends IService<Curelation> {

    void listen(Curelation curelation);
}
