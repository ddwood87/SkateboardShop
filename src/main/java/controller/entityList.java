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
 * Servlet implementation class entityList
 */
@WebServlet("/entitylist")
public class entityList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SkateHelper sh = new SkateHelper();
	private SkateDeckHelper sdh = new SkateDeckHelper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public entityList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SkateDeck> skateDecks = sdh.listAllSkateDecks();
		request.setAttribute("skateDecks", skateDecks);
		boolean noSkateDecks = false;
		if(skateDecks.size() == 0) { noSkateDecks = true;}
		request.setAttribute("noSkateDecks", noSkateDecks);
		
		List<Skateboard> skates = sh.listAllSkates();
		request.setAttribute("skateboards", skates);
		boolean noSkates = false;
		if(skates.size() == 0) { noSkates = true; }
		request.setAttribute("noSkates", noSkates);
		
		getServletContext().getRequestDispatcher("/entitylist.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
