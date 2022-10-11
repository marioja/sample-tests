package simpleExternalJar;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExtraServlet
 */
@WebServlet("/ExternalServlet")
public class ExternalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExternalServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class<?> cl=Class.forName("dev.simplejar.Display");
			Method m = cl.getMethod("info", String.class);
			m.invoke(null, request.getContextPath());
			response.getWriter().append(this.getClass().getCanonicalName()).append(" Served at: ").append(request.getContextPath()).append(" ").append((String)m.invoke(null, request.getContextPath()));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

}
