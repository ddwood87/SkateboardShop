package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.SkateDeckHelper;
import data.SkateHelper;
import models.SkateDeck;
import models.Skateboard;

/**
 * Servlet implementation class deleteSkate
 */
@WebServlet({"/deleteSkateDeck", "/deleteskatedeck"})
public class deleteSkateDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateDeckHelper sdh = new SkateDeckHelper(); 
    SkateHelper sh = new SkateHelper();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteSkateDeck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		SkateDeck sd = sdh.getSkateDeckById(id);
		List<Skateboard> sl = sh.getSkateByDeck(sd);
		for(Skateboard s : sl) {
			sh.deleteSkate(s);
		}
		sdh.deleteSkateDeck(sd);
		response.sendRedirect("skatedecklist");
	}

}
