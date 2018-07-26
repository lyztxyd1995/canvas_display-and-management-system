package com.canvas.mapper;

import com.canvas.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface CategoryMapper {
    @Select("SELECT id, name, createName, updateTime, description FROM category")
    List<Category> getCategories();

    @Insert("INSERT INTO category (name, createName, createTime,updateTime,description) VALUES (#{name}, #{createName}, #{createTime}, #{updateTime}, #{description})")
    void addCategory(Category category);

    @Delete("DELETE FROM category WHERE id=#{id}")
    void deleteCategory(Long id);

    @Select("SELECT id, name, createName, updateTime, description FROM category WHERE id=#{id}")
    Category getCategory(Long id);

    @Update("UPDATE category SET name=#{name}, description=#{description}, updateTime = #{now} WHERE id=#{id}")
    void updateCategory(@Param("id") Long id, @Param("description") String description, @Param("name") String name, @Param("now") Date now);



}
