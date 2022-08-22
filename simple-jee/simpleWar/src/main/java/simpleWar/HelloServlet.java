package simpleWar;

import static simpleWar.GreatWar.displayHello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(
		urlPatterns = { "/HelloServlet" }, 
		initParams = { 
				@WebInitParam(name = "group", value = "KING", description = "Group where servlet runs")
		})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger=LoggerFactory.getLogger(HelloServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String group=getInitParameter("group");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" for group ").append(group)
		;
		displayHello(group);
		logger.info("Hello from slf4j");
	}

}
