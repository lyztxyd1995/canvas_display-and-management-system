package com.canvas.service;

import com.canvas.entity.Category;
import com.canvas.mapper.CategoryMapper;
import com.canvas.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class CategoryService {
    public List<Category> getCategories(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        List<Category>result = null;
        try{
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            result = mapper.getCategories();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public void addCategory(Category category){
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        SqlSession sqlSession = MyBatisUtils.openSession();
        try{
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            mapper.addCategory(category);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteCategory(Long id){
        SqlSession sqlSession = MyBatisUtils.openSession();
        try{
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            mapper.deleteCategory(id);
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public Category getCategory(Long id){
        SqlSession sqlSession = MyBatisUtils.openSession();
        Category category = null;
        try{
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            category = mapper.getCategory(id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return category;
    }

    public void updateCategory(Long id, String description, String name){
        SqlSession sqlSession = MyBatisUtils.openSession();
        try{
            CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
            Date now = new Date();
            mapper.updateCategory(id, description, name, now);
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
