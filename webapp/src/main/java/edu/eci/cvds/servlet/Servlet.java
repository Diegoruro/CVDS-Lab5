package edu.eci.cvds.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.eci.cvds.servlet.model.Todo;

@WebServlet(
        urlPatterns="/hola"
)

public class Servlet extends HttpServlet{
    static final long serialVersionUID = 35L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        try {
            Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
            resp.setStatus(HttpServletResponse.SC_OK);
            String id = optName.isPresent() ? optName.get() : "";
            resp.sendRedirect("/hola?id=id");
            Todo todo = Service.getTodo(Integer.parseInt(id));
            List<Todo> listTodo = new ArrayList<>();
            listTodo.add(todo);
            String json = Service.todosToHTMLTable(listTodo);
            responseWriter.write(json);
            responseWriter.flush();
        }catch (MalformedURLException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }catch (FileNotFoundException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

}
