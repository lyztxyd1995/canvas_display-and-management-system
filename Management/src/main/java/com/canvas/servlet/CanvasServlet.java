package com.canvas.servlet;

import com.canvas.entity.Canvas;
import com.canvas.entity.Category;
import com.canvas.service.CanvasService;
import com.canvas.service.CategoryService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CanvasServlet extends  HttpServlet{
    CanvasService canvasService;
    CategoryService categoryService;

    public void init() throws ServletException {
        super.init();
        canvasService = new CanvasService();
        categoryService = new CategoryService();
    }

    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("/canvas/list.do".equals(req.getServletPath())){
            String categoryId = req.getParameter("categoryId");
            List<Category> categories = categoryService.getCategories();
            List<Canvas>canvases = null;
            if(categoryId == null || "".equals(categoryId.trim())){
                canvases = canvasService.getCanvases();
            } else {
                canvases = canvasService.getCanvasesByCategoryId(Long.parseLong(categoryId));
            }
            req.setAttribute("canvases", canvases);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/canvas_list.jsp").forward(req,resp);
        }
        else if("/canvas/addPrompt.do".equals(req.getServletPath())){
            List<Category> categories = categoryService.getCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/add_canvas.jsp").forward(req,resp);
        }
        else if("/canvas/add.do".equals(req.getServletPath())){
            req.setCharacterEncoding("utf-8");
            if(ServletFileUpload.isMultipartContent(req)){
                try{
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List<FileItem>items = upload.parseRequest(req);
                    Iterator<FileItem> ite = items.iterator();
                    Canvas canvas = new Canvas();
                    while(ite.hasNext()){
                        FileItem item = ite.next();
                        if(item.isFormField()){
                            String fieldName = item.getFieldName();
                            if("name".equals(fieldName)){
                                canvas.setName(new String(item.getString().getBytes("iso8859-1"), "utf-8"));
                            }
                            else if("categoryId".equals(fieldName)){
                                canvas.setCategoryId(Long.parseLong(item.getString()));
                            }
                            else if("price".equals(fieldName)) {
                                canvas.setPrice(Integer.parseInt(item.getString()));
                            }
                            else if("description".equals(fieldName)){
                                canvas.setDescription(new String(item.getString().getBytes("iso8859-1"), "utf-8"));
                            }
                        } else {
                            canvas.setSmallImg(item.get());
                        }
                    }
                    Date now = new Date();
                    canvas.setUpdateTime(now);
                    canvas.setCreateTime(now);
                    canvasService.addCanvas(canvas);
                    req.getRequestDispatcher("/canvas/list.do").forward(req,resp);
                } catch(FileUploadException e){
                    e.printStackTrace();
                }
            } else {
                Canvas canvas = new Canvas();
                String name = req.getParameter("name");
                Long categoryId = Long.parseLong(req.getParameter("categoryId"));
                Integer price = Integer.parseInt(req.getParameter("price"));
                String description = req.getParameter("description");
                canvas.setName(name);
                canvas.setCategoryId(categoryId);
                canvas.setPrice(price);
                canvas.setDescription(description);
                Date now = new Date();
                canvas.setUpdateTime(now);
                canvas.setCreateTime(now);
                canvasService.addCanvas(canvas);
                req.getRequestDispatcher("/canvas/list.do").forward(req,resp);
            }
        }
        else if("/canvas/delete.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            canvasService.deleteCanvas(id);
            req.getRequestDispatcher("/canvas/list.do").forward(req,resp);
        }
        else if("/canvas/updatePrompt.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            Canvas canvas = canvasService.getCanvas(id);
            List<Category>categories = categoryService.getCategories();
            req.setAttribute("canvas", canvas);
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/udpdate_canvas.jsp").forward(req,resp);
        }
        else if("/canvas/update.do".equals(req.getServletPath())){
            req.setCharacterEncoding("utf-8");
            if(ServletFileUpload.isMultipartContent(req)){
                try{
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List<FileItem>items = upload.parseRequest(req);
                    Iterator<FileItem> ite = items.iterator();
                    Canvas canvas = new Canvas();
                    while(ite.hasNext()){
                        FileItem item = ite.next();
                        if(item.isFormField()){
                            String fieldName = item.getFieldName();
                            if("name".equals(fieldName)){
                                canvas.setName(new String(item.getString().getBytes("iso8859-1"), "utf-8"));
                            }
                            else if("categoryId".equals(fieldName)){
                                canvas.setCategoryId(Long.parseLong(item.getString()));
                            }
                            else if("price".equals(fieldName)) {
                                canvas.setPrice(Integer.parseInt(item.getString()));
                            }
                            else if("description".equals(fieldName)){
                                canvas.setDescription(new String(item.getString().getBytes("iso8859-1"), "utf-8"));
                            }
                            else if("id".equals(fieldName)){
                                canvas.setId(Long.parseLong(item.getString()));
                            }
                        } else {
                            canvas.setSmallImg(item.get());
                        }
                    }
                    Date now = new Date();
                    canvas.setUpdateTime(now);
                    canvasService.updateCanvas(canvas);
                    req.getRequestDispatcher("/canvas/list.do").forward(req,resp);
                } catch(FileUploadException e){
                    e.printStackTrace();
                }
            } else {
                Canvas canvas = new Canvas();
                String name = req.getParameter("name");
                Long categoryId = Long.parseLong(req.getParameter("categoryId"));
                Integer price = Integer.parseInt(req.getParameter("price"));
                String description = req.getParameter("description");
                Long id = Long.parseLong("id");
                canvas.setName(name);
                canvas.setCategoryId(categoryId);
                canvas.setPrice(price);
                canvas.setDescription(description);
                canvas.setId(id);
                Date now = new Date();
                canvas.setUpdateTime(now);
                canvasService.updateCanvas(canvas);
                req.getRequestDispatcher("/canvas/list.do").forward(req,resp);
            }
        }
    }

    public void destroy(){
        canvasService = null;
        categoryService = null;
        super.destroy();
    }

}
