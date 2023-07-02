package org.example.mapper;

import org.example.pojo.DeptCamel;

import java.util.List;

public interface resultMapMapper {
    //查询所有
    public List<DeptCamel> selectAll();
}
