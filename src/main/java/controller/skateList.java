package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.SkateHelper;
import models.Skateboard;

/**
 * Servlet implementation class skateList
 */
@WebServlet({ "/skateList", "/skatelist" })
public class skateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SkateHelper sh = new SkateHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public skateList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Skateboard> skates = sh.listAllSkates();
		request.setAttribute("skateboards", skates);
		boolean noSkates = false;
		if(skates.size() == 0) { noSkates = true;}
		request.setAttribute("noSkates", noSkates);
		getServletContext().getRequestDispatcher("/list.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deck = "";
		String wheel = "";
		String truck = "";
		if(request.getParameter("searchdeck") != null) {
			deck = (String)request.getParameter("searchdeck");
		}
		if(request.getParameter("searchwheel") != null) {
			wheel = (String)request.getParameter("searchwheel");
		}
		if(request.getParameter("searchtruck") != null) {
			truck = (String)request.getParameter("searchtruck");
		}
		if(deck == "" && wheel == "" && truck == "") {
			doGet(request, response);
		} else {
			List<Skateboard> skates = sh.searchByPartBrand(deck, wheel, truck);
			request.setAttribute("skateboards", skates);
			boolean noSkates = false;
			if(skates.size() == 0) { noSkates = true;}
			request.setAttribute("noSkates", noSkates);
			getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
		}
	}

}
