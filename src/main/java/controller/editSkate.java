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
 * Servlet implementation class addSkate
 */
@WebServlet({ "/editSkate", "/editskate" })
public class editSkate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateHelper sh = new SkateHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editSkate() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String addOrEdit = "Edit";
		if(request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Skateboard skate = sh.getSkateById(id);
			request.setAttribute("id", skate.getId());
			request.setAttribute("deck", skate.getDeckBrand());
			request.setAttribute("wheel", skate.getWheelBrand());
			request.setAttribute("truck", skate.getTruckBrand());
		}
		else {
			addOrEdit = "Add";
		}
		
		request.setAttribute("addOrEdit", addOrEdit);
		
		getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deck = request.getParameter("deck");
		String wheel = request.getParameter("wheel");
		String truck = request.getParameter("truck");
		Skateboard result = new Skateboard(deck, wheel, truck);
		
		var reqId = request.getParameter("id");
		if(reqId != null && !reqId.equals("")) {
			int id = Integer.parseInt(reqId);
			Skateboard existingSkate = sh.getSkateById(id);
			if(!result.equals(existingSkate)) {
				existingSkate.setDeckBrand(deck);
				existingSkate.setWheelBrand(wheel);
				existingSkate.setTruckBrand(truck);
				result = sh.updateSkate(existingSkate);
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
