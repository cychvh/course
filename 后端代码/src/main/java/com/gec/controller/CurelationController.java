package com.gec.controller;

import com.gec.entity.Curelation;
import com.gec.service.CurelationService;
import com.gec.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurelationController {
    @Autowired
    CurelationService curelationService;
    @GetMapping("/queryCurelation")
    public Result queryCurelation(){
        List<Curelation> list = curelationService.list();
        return Result.success(list);
    }
    @PostMapping("/listening")
    public Result listening(@RequestBody Curelation curelation){
        System.out.println(curelation);
         curelationService.listen(curelation);
        return Result.success();
    }

    @DeleteMapping( "/delCurelation/{id}")
    public  Result delCurelation(@PathVariable String id){
        boolean b = curelationService.removeById(id);
        if (b){
            return Result.success();
        }else{
            return Result.error( "4008", "删除听课记录失败");
        }
    }
}


