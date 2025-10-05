package com.gec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String coursename;

    /**
     * 
     */
    private String coursenum;

    /**
     * 
     */
    private String courseteacher;

    /**
     * 
     */
    private String coursedate;

    /**
     * 
     */
    private String coursedesc;

    /**
     * 
     */
    private String courseroom;

    /**
     * 
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCoursename() == null ? other.getCoursename() == null : this.getCoursename().equals(other.getCoursename()))
            && (this.getCoursenum() == null ? other.getCoursenum() == null : this.getCoursenum().equals(other.getCoursenum()))
            && (this.getCourseteacher() == null ? other.getCourseteacher() == null : this.getCourseteacher().equals(other.getCourseteacher()))
            && (this.getCoursedate() == null ? other.getCoursedate() == null : this.getCoursedate().equals(other.getCoursedate()))
            && (this.getCoursedesc() == null ? other.getCoursedesc() == null : this.getCoursedesc().equals(other.getCoursedesc()))
            && (this.getCourseroom() == null ? other.getCourseroom() == null : this.getCourseroom().equals(other.getCourseroom()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCoursename() == null) ? 0 : getCoursename().hashCode());
        result = prime * result + ((getCoursenum() == null) ? 0 : getCoursenum().hashCode());
        result = prime * result + ((getCourseteacher() == null) ? 0 : getCourseteacher().hashCode());
        result = prime * result + ((getCoursedate() == null) ? 0 : getCoursedate().hashCode());
        result = prime * result + ((getCoursedesc() == null) ? 0 : getCoursedesc().hashCode());
        result = prime * result + ((getCourseroom() == null) ? 0 : getCourseroom().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coursename=").append(coursename);
        sb.append(", coursenum=").append(coursenum);
        sb.append(", courseteacher=").append(courseteacher);
        sb.append(", coursedate=").append(coursedate);
        sb.append(", coursedesc=").append(coursedesc);
        sb.append(", courseroom=").append(courseroom);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}