package rsv;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Serebatos
 * Date: 19.07.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class MailServlet extends HttpServlet {
    String pathToSave;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);    //To change body of overridden methods use File | Settings | File Templates.
//        берес из web.xml путь, куда писать полученные статусы\данные
        pathToSave = config.getInitParameter("path");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        пока хардкод, получаем важную информацию
        String data = req.getParameter("hm");

        if (data != null) {
//            Получаем POST запрос и пишем в файл
            writeFile(data);
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>");
            out.println("OK");
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

//    заглушка, чтобы можно было браузером проверить, что все работает норм
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>");
//        writeFile();
        out.println("OK");
        out.println("</h1>");
        out.println("</body>");
        out.println("</html>");
    }



    private void writeFile(String text) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(pathToSave + "test.txt",true));
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}
