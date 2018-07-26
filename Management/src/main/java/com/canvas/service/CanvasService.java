package com.canvas.service;

import com.canvas.entity.Canvas;
import com.canvas.mapper.CanvasMapper;
import com.canvas.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class CanvasService {

    public List<Canvas> getCanvases(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        List<Canvas>result = null;
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            result = mapper.getCanvases();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public List<Canvas> getCanvasesByCategoryId(Long categoryId){
        SqlSession sqlSession = MyBatisUtils.openSession();
        List<Canvas>result = null;
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            result = mapper.getCanvasesByCategoryId(categoryId);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public void addCanvas(Canvas canvas){
        SqlSession sqlSession = MyBatisUtils.openSession();
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            mapper.addCanvas(canvas);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteCanvas(Long id){
        SqlSession sqlSession = MyBatisUtils.openSession();
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            mapper.deleteCanvas(id);
            sqlSession.commit();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public Canvas getCanvas(Long id){
        SqlSession sqlSession = MyBatisUtils.openSession();
        Canvas canvas = null;
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            canvas = mapper.getCanvas(id);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return canvas;
    }

    public void updateCanvas(Canvas canvas){
        SqlSession sqlSession = MyBatisUtils.openSession();
        try {
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            if (canvas.getSmallImg() == null || canvas.getSmallImg().length == 0) {
                mapper.updateCanvasWithoutImage(canvas);
            } else {
                mapper.updateCanvas(canvas);
            }
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public List<Canvas>getCanvasesByPagesize(int page, int size){
        SqlSession sqlSession = MyBatisUtils.openSession();
        List<Canvas> result = null;
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            result = mapper.getCanvasesByPagesize((page - 1) * size, size);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public int countCanvas(){
        SqlSession sqlSession = MyBatisUtils.openSession();
        Integer result = null;
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            result = mapper.countCanvas();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    public Canvas getImg(Long id){
        SqlSession sqlSession = MyBatisUtils.openSession();
        Canvas canvas = null;
        try{
            CanvasMapper mapper = sqlSession.getMapper(CanvasMapper.class);
            canvas = mapper.getImg(id);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return canvas;

    }
}
