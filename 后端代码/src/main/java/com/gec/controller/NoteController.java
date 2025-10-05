package com.gec.controller;

import com.gec.entity.Notice;
import com.gec.service.NoticeService;
import com.gec.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    NoticeService noticeService;
    @GetMapping("/queryNotice")
    public Result queryNotice() {
        List<Notice> notices = noticeService.queryNote();
        return Result.success(notices);
    }
    @PostMapping ("/saveNotice")
    public Result saveNotice(@RequestBody Notice notice) {
        System.out.println(notice);
        Result result = noticeService.saveOrUpdateNote(notice);
        return Result.success();
    }
    @DeleteMapping("/delNotice/{id}")
    public Result delNotice(@PathVariable String id) {
        if(noticeService.deleteNote(id)){
            return Result.success();
        }else{
            return Result.error("40009","删除失败");
        }
    }
}
