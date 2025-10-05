package com.gec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.entity.Power;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author Administrator
* @description 针对表【power】的数据库操作Mapper
* @createDate 2024-11-12 21:07:04
* @Entity com.gec.entity.Power
*/
@Mapper
public interface PowerMapper extends BaseMapper<Power> {

    @Select("SELECT id, roleid, power FROM power WHERE id = #{id}")
    Power selectByPrimaryKey(@Param("id") Long id);

    /**
     * 根据主键选择性更新 Power 数据
     * @param power Power 对象
     * @return 更新的记录数
     */
    @Update({
            "<script>",
            "UPDATE power",
            "<set>",
            "<if test='roleid != null'>roleid = #{roleid},</if>",
            "<if test='power != null'>power = #{power},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    int updateByPrimaryKeySelective(Power power);
}




