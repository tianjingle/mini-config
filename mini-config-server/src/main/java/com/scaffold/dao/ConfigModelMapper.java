package com.scaffold.dao;

import com.scaffold.dao.model.ConfigModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jingletian
 * @date 2022/3/29 14:55
 */
public interface ConfigModelMapper {

    /**
     * 按名称查询
     * @param name
     * @return
     */
    List<ConfigModel> findByName(@Param("env") String env,@Param("name") String name);

    /**
     * 新增
     * @param configModel
     */
    void insert(@Param("configModel") ConfigModel configModel);

    /**
     * modify
     * @param id
     * @param txt
     */
    void modify(@Param("id")String id, @Param("txt")String txt);
}
