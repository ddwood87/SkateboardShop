package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.SkateDeckHelper;
import data.SkateHelper;
import models.SkateDeck;

/**
 * Servlet implementation class addSkate
 */
@WebServlet({ "/editSkateDeck", "/editskatedeck" })
public class editSkateDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateHelper sh = new SkateHelper();
    SkateDeckHelper sdh = new SkateDeckHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editSkateDeck() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String addOrEdit = "Edit";
		String reqId = request.getParameter("id");
		if(reqId != null && !reqId.isEmpty()) {
			int id = Integer.parseInt(request.getParameter("id"));
			SkateDeck skateDeck = sdh.getSkateDeckById(id);
			request.setAttribute("id", skateDeck.getId());
			request.setAttribute("brand", skateDeck.getBrand());
			request.setAttribute("width", skateDeck.getWidth());
		}
		else {
			addOrEdit = "Add";
		}
		
		request.setAttribute("addOrEdit", addOrEdit);
		
		getServletContext().getRequestDispatcher("/editDeck.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int deckId = Integer.parseInt(request.getParameter("deck"));
		//SkateDeck deck = sdh.getSkateDeckById(deckId);
		
		String brand = request.getParameter("brand");
		double width = Double.parseDouble(request.getParameter("width"));
		
		SkateDeck result = new SkateDeck(brand, width);
		
		var reqId = request.getParameter("id");
		if(reqId != null && !reqId.equals("")) {
			int id = Integer.parseInt(reqId);
			SkateDeck existingSkateDeck = sdh.getSkateDeckById(id);
			if(!result.equals(existingSkateDeck)) {
				existingSkateDeck.setBrand(brand);
				existingSkateDeck.setWidth(width);
				if(sdh.updateSkateDeck(existingSkateDeck)) {
					result = existingSkateDeck;
				}
				//if update fails, get editDeck and send message
				else {
					request.setAttribute("message", "That deck already exists.");
					doGet(request, response);
				}
			} else {
				result = existingSkateDeck;
			}
		}
		else {
			if(sdh.skateDeckExists(result)) {
				request.setAttribute("message", "A skate deck like that already exists.");
				doGet(request, response);
			}else {
				SkateDeck addSkateDeck = new SkateDeck(brand, width);
				sdh.addSkateDeck(addSkateDeck);
				result = addSkateDeck;				
			}
		}
		
		request.setAttribute("skateDeck", result);
		getServletContext().getRequestDispatcher("/detailDeck.jsp").forward(request, response);
	}

}
