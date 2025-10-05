package com.gec.req;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class CourseReq {

    @NotNull(message = "【课程名称】不能为空")
    private String id;
    @NotNull(message = "【日期】不能为空")
    private String[] date1;
    @NotNull(message = "【时间】不能为空")
    private String[] date2;
    @NotNull(message = "【教室】不能为空")
    private String[] courseroom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getDate1() {
        return date1;
    }

    public void setDate1(String[] date1) {
        this.date1 = date1;
    }

    public String[] getDate2() {
        return date2;
    }

    public void setDate2(String[] date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "CourseReq{" +
                "id='" + id + '\'' +
                ", date1=" + Arrays.toString(date1) +
                ", date2=" + Arrays.toString(date2) +
                ", courseroom=" + Arrays.toString(courseroom) +
                '}';
    }

    public String[] getCourseroom() {
        return courseroom;
    }

    public void setCourseroom(String[] courseroom) {
        this.courseroom = courseroom;
    }
}
