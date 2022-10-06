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
import models.Skateboard;

/**
 * Servlet implementation class addSkate
 */
@WebServlet({ "/editSkate", "/editskate" })
public class editSkate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateHelper sh = new SkateHelper();
    SkateDeckHelper sdh = new SkateDeckHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editSkate() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String addOrEdit = "Edit";
		String reqId = request.getParameter("id");
		if(reqId != null && !reqId.isEmpty()) {
			int id = Integer.parseInt(request.getParameter("id"));
			Skateboard skate = sh.getSkateById(id);
			request.setAttribute("id", skate.getId());
			request.setAttribute("deck", skate.getDeck());
			request.setAttribute("wheel", skate.getWheelBrand());
			request.setAttribute("truck", skate.getTruckBrand());
		}
		else {
			addOrEdit = "Add";
		}
		
		request.setAttribute("addOrEdit", addOrEdit);
		request.setAttribute("allDecks", sdh.listAllSkateDecks());
		
		getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deckIdInput = request.getParameter("deck");
		int deckId = Integer.parseInt(deckIdInput);
		SkateDeck deck = sdh.getSkateDeckById(deckId);
		
		String wheel = request.getParameter("wheel");
		String truck = request.getParameter("truck");
		
		Skateboard result = new Skateboard(deck, wheel, truck);
		
		var reqId = request.getParameter("id");
		if(reqId != null && !reqId.equals("")) {
			int id = Integer.parseInt(reqId);
			Skateboard existingSkate = sh.getSkateById(id);
			if(!result.equals(existingSkate)) {
				existingSkate.setDeck(deck);
				existingSkate.setWheelBrand(wheel);
				existingSkate.setTruckBrand(truck);
				if(sh.updateSkate(existingSkate)) {
				 result = sh.getSkateById(existingSkate.getId());
				} else {
					request.setAttribute("message", "That board already exists.");
					doGet(request, response);					
				}
			} else {
				result = existingSkate;
			}
		}
		else {
			Skateboard addSkate = new Skateboard(deck, wheel, truck);
			sh.addSkate(addSkate);
			result = addSkate;
		}
		
		request.setAttribute("skate", result);
		getServletContext().getRequestDispatcher("/detail.jsp").forward(request, response);
	}

}
