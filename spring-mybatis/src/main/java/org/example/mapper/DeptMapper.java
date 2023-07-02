package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Dept;

@Mapper
public interface DeptMapper {
    @Select("select * from dept where deptno=#{dept_no}")
    Dept getDept(int dept_no);
}
