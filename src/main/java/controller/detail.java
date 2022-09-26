package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.SkateHelper;
import models.Skateboard;

/**
 * Servlet implementation class detail
 */
@WebServlet("/detail")
public class detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateHelper sh = new SkateHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var id = request.getParameter("id");
		Skateboard s = sh.getSkateById(Integer.parseInt(id));
		request.setAttribute("skate", s);
		getServletContext().getRequestDispatcher("/detail.jsp").forward(request, response);
	}

}
