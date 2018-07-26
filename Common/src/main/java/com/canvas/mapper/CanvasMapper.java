package com.canvas.mapper;

import com.canvas.entity.Canvas;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CanvasMapper {


    @Select("SELECT id, categoryId, name, price, createTime, updateTime, description FROM canvas ORDER BY createTime desc")
    List<Canvas> getCanvases();

    @Select("SELECT id, categoryId, name,price, createTime, updateTime, description FROM canvas WHERE categoryId = #{categoryId} ORDER BY createTime DESC ")
    List<Canvas>getCanvasesByCategoryId(@Param("categoryId") Long categoryId);

    @Insert("INSERT INTO canvas(categoryId,name, price,smallImg,createTime, updateTime,description) VALUES " +
            "(#{categoryId}, #{name}, #{price},#{smallImg},#{createTime}, #{updateTime},#{description})")
    void addCanvas(Canvas canvas);

    @Delete("DELETE FROM canvas WHERE id=#{id}")
    void deleteCanvas(Long id);

    @Select("SELECT id, creator, categoryId, name, price, description,createTime,updateTime FROM canvas WHERE id=#{id}")
    Canvas getCanvas(Long id);

    @Update("UPDATE canvas SET name = #{name}, categoryId = #{categoryId}, price = #{price}, description=#{description},updateTime = #{updateTime} WHERE id = #{id}")
    void updateCanvasWithoutImage(Canvas canvas);


    @Update("UPDATE canvas SET name = #{name}, categoryId = #{categoryId}, price = #{price}, description=#{description}, smallImg = #{smallImg}," +
            "updateTime = #{updateTime} WHERE id = #{id}")
    void updateCanvas(Canvas canvas);

    @Select("SELECT * FROM canvas ORDER BY createTime DESC LIMIT #{skip}, #{size}")
    List<Canvas> getCanvasesByPagesize(@Param("skip") int skip, @Param("size") int size);

    @Select("SELECT COUNT(*) from canvas")
    Integer countCanvas();

    @Select("SELECT smallImg from canvas WHERE id=#{id}")
    Canvas getImg(Long id);
}
