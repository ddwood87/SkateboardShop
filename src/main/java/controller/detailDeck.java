package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.SkateDeckHelper;
import models.SkateDeck;

/**
 * Servlet implementation class detail
 */
@WebServlet("/detaildeck")
public class detailDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateDeckHelper sdh = new SkateDeckHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailDeck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var id = request.getParameter("id");
		SkateDeck sd = sdh.getSkateDeckById(Integer.parseInt(id));
		request.setAttribute("skate", sd);
		getServletContext().getRequestDispatcher("/detailDeck.jsp").forward(request, response);
	}

}
