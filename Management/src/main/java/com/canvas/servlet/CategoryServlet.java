package com.canvas.servlet;

import com.canvas.entity.Category;
import com.canvas.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet{
    CategoryService categoryService;
    public void init() throws ServletException {
        super.init();
        categoryService = new CategoryService();
    }
    @Override
    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("/category/list.do".equals(req.getServletPath())){
            List<Category> categories = categoryService.getCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/category_list.jsp").forward(req,resp);
        }
        else if("/category/addPrompt.do".equals(req.getServletPath())){
            req.getRequestDispatcher("/WEB-INF/views/biz/add_category.jsp").forward(req,resp);
        }
        else if("/category/add.do".equals(req.getServletPath())){
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String createName = (String)req.getSession().getAttribute("username");
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            category.setCreateName(createName);
            categoryService.addCategory(category);
            req.getRequestDispatcher("/category/list.do").forward(req,resp);
        }
        else if("/category/delete.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            categoryService.deleteCategory(id);
            req.getRequestDispatcher("/category/list.do").forward(req,resp);
        }
        else if("/category/updatePrompt.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            Category category = categoryService.getCategory(id);
            req.setAttribute("category", category);
            req.getRequestDispatcher("/WEB-INF/views/biz/update_category.jsp").forward(req,resp);
        }
        else if("/category/update.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            String description = req.getParameter("description");
            String name = req.getParameter("name");
            categoryService.updateCategory(id, description, name);
            req.getRequestDispatcher("/category/list.do").forward(req,resp);
        }
    }

    public void destroy(){
        categoryService = null;
        super.destroy();
    }

}

