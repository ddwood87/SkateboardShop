package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.SkateDeckHelper;
import models.SkateDeck;


/**
 * Servlet implementation class skateList
 */
@WebServlet({ "/skateDeckList", "/skatedecklist" })
public class skateDeckList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateDeckHelper sdh = new SkateDeckHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public skateDeckList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<SkateDeck> skateDecks = sdh.listAllSkateDecks();
		request.setAttribute("skateDecks", skateDecks);
		boolean noSkateDecks = false;
		if(skateDecks.size() == 0) { noSkateDecks = true;}
		request.setAttribute("noSkateDecks", noSkateDecks);
		getServletContext().getRequestDispatcher("/decklist.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deckBrand = "";
		double deckWidth = 0.0;
		
		deckBrand = (String)request.getParameter("searchbrand");
		if(!request.getParameter("searchwidth").isEmpty()) {
			deckWidth = Double.parseDouble(request.getParameter("searchwidth"));
		}
		
		List<SkateDeck> skateDecks;
		//No brand
		if(deckBrand.isEmpty()) {
			//No brand, no width
			if(deckWidth == 0.0) {
				doGet(request, response);
				return;
			} 
			//Width only
			else {
				skateDecks = sdh.searchByDeckWidth(deckWidth);
			}
		} 
		// Brand and width
		else if(deckWidth != 0.0){
			skateDecks = sdh.searchForSkateDeck(deckBrand, deckWidth);
		} 
		//Brand only
		else { skateDecks = sdh.searchByDeckBrand(deckBrand); }
		request.setAttribute("skateDecks", skateDecks);
		boolean noSkateDecks = false;
		if(skateDecks.size() == 0) { noSkateDecks = true;}
		request.setAttribute("noSkatesDecks", noSkateDecks);
		getServletContext().getRequestDispatcher("/decklist.jsp").forward(request, response);	
	}

}
