package com.canvas.servlet;

import com.canvas.entity.Canvas;
import com.canvas.service.CanvasService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CanvasServlet extends HttpServlet {
    CanvasService canvasService;
    public void init() throws ServletException{
        super.init();
        canvasService = new CanvasService();
    }
    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if("/canvas/list.do".equals(req.getServletPath())){
           String pageStr = req.getParameter("page");
           int page = 1;
           if(pageStr!=null && "" != pageStr.trim()){
               page = Integer.parseInt(pageStr);
           }
            List<Canvas>canvases = canvasService.getCanvasesByPagesize(page, 8);
            int count = canvasService.countCanvas();
            int last = count % 8 == 0 ? (count / 8) : ((count / 8) + 1);
            req.setAttribute("page", page);
            req.setAttribute("last",last);
            req.setAttribute("canvases", canvases);
            req.getRequestDispatcher("/WEB-INF/canvas_list.jsp").forward(req, resp);
        }
        else if("/canvas/getImg.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            Canvas canvas = canvasService.getImg(id);
            try {
                resp.setContentType("multipart/form-data");
                if (null != canvas && null != canvas.getSmallImg()) {
                    InputStream in = new ByteArrayInputStream(canvas.getSmallImg());
                    ServletOutputStream out = resp.getOutputStream();
                    byte[] b = new byte[1024];
                    int length = in.read(b);
                    while (length != -1) {
                        out.write(b);
                        length = in.read(b);
                    }
                    out.flush();
                    out.close();
                    in.close();resp.flushBuffer();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("/canvas/getDetail.do".equals(req.getServletPath())){
            Long id = Long.parseLong(req.getParameter("id"));
            Canvas canvas = canvasService.getCanvas(id);
            req.setAttribute("canvas", canvas);
            req.getRequestDispatcher("/WEB-INF/detail.jsp").forward(req,resp);
        }
    }
    public void destroy(){
        canvasService = null;
        super.destroy();
    }
}
