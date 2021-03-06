package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Impl.PinImpl;
import dao.PinDao;
import dto.JournalDto;
import dto.PinDto;
import dto.pinCommentDto;

public class PinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	 public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	       
		   req.setCharacterEncoding("utf-8");
		   resp.setContentType("text/html; charset=utf-8");
		   
		   PinImpl dao = PinDao.getInstance();
		   
		   String command = req.getParameter("command");
		   
		   if(command.equals("insert")) {
			   
				String place_kind =req.getParameter("place_kind");
				String slat = req.getParameter("lat");
				double lat = Double.parseDouble(slat);
				String slng = req.getParameter("lng");
				double lng = Double.parseDouble(slng);
				String pin_name = req.getParameter("pin_name");
				String location = req.getParameter("location");
				
				PinDto dto = new PinDto(lat,lng,pin_name,place_kind,location);
				System.out.println(dto.toString());
				dao.Insert(dto);
		   }else if(command.equals("PinSearch")) {
			   String place_kind = req.getParameter("place_kind");
			   String pin_name = req.getParameter("pin_name");
			   
			   List<PinDto> list =dao.PinList(pin_name, place_kind);
			    
			   String result = "[";
			   
			   for(PinDto dto :list) {
				   
				   result+="{";
				   
				   result+="\"lat\":\""+dto.getLat()+"\",\"lng\":\""+dto.getLng()+"\",";
				   result+="\"pin_name\":\""+dto.getPin_name()+"\",\"place_kind\":\""+dto.getKinds()+"\",";
				   result+="\"location\":\""+dto.getLocation()+"\"},";
			   }
			   
			   result = result.substring(0, result.lastIndexOf(","));
			   result +="]";
			   System.out.println(result);
			   PrintWriter pw =resp.getWriter();
			   pw.print((String)result);
			   
		   }else if(command.equals("PinRank")) {
			  List<String[]> list = dao.pinAVG();
			   
			   
			  for(String str[] : list ) {
				  System.out.println(str[0]+ " "+str[1] + " " +str[2]);
			  }
			   
		   }else if(command.equals("pinDetail")) {
			   String pinname = req.getParameter("pinname");
			   
			   PinDto dto = dao.getPin(pinname);
			   List<pinCommentDto> list = dao.getPinCommentList(pinname);
			   req.setAttribute("pin", dto);
			   req.setAttribute("pinCList", list);
			   //&=<%=pinlist.get(i)[2] %>
			   List<String[]> avglist = dao.pinAVG();
			
			   for(String pininfo[] : avglist ) {
				   System.out.println(pininfo[0]+ "  "+pinname );
				   if(pininfo[0].equals(pinname)) {
					   System.out.println("value:"+pininfo[2]);
					   req.setAttribute("grade_AVG", pininfo[2]);
					   break;
				   }
			   }
			   
			   dispatch("Pindetail.jsp", req, resp);
		   }else if(command.equals("goPinspeed")) {
			    int paging = 1;
				int pcount = dao.getAllPinCount();

				List<String[]> pinlist = dao.getAllPinList(paging);
				int pagecount = 0;
				if (pcount != 0) {
					pagecount = pcount / 9;
					if (pcount % 9 > 0) {

						pagecount++;
					}
				}

				req.setAttribute("page", paging);
				req.setAttribute("pagecount", pagecount);
				req.setAttribute("pinlist", pinlist);
				dispatch("Pinspeed.jsp", req, resp);
		   }else if(command.equals("pinPaging")) {
			    int page = Integer.parseInt(req.getParameter("page"));
				int jcount = dao.getAllPinCount();

				List<String[]> pinlist = dao.getAllPinList(page);
				int pagecount = 0;
				if (jcount != 0) {
					pagecount = jcount / 9;
					if (jcount % 9 > 0) {

						pagecount++;
					}
				}

				req.setAttribute("page", page);
				req.setAttribute("pagecount", pagecount);
				req.setAttribute("pinlist", pinlist);
				dispatch("Pinspeed.jsp", req, resp);
		   }else if(command.equals("searchpin")) {
			   String stext = req.getParameter("stext");
			   int paging = 1;
			   if(req.getParameter("page") == null || req.getParameter("page").equals("")) {
				   
			   }else {
				   paging = Integer.parseInt(req.getParameter("page"));
			   }
			   int pcount = dao.getSearchPinCount(stext);

			   List<String[]> pinlist = dao.getSearchPinList(paging, stext);
			   int pagecount = 1;
			   pagecount = pcount/9;
			   if(pcount != 0){
				   if(pcount % 9 >0){

					   pagecount++;
				   }
			   }
			   req.setAttribute("page", paging);
			   req.setAttribute("pagecount", pagecount);
			   req.setAttribute("pinlist", pinlist);
			   req.setAttribute("stext", stext);
			   dispatch("Pinsearch.jsp", req, resp);
			   

		   }else if(command.equals("pinSearchPaging")) {
			   String stext = req.getParameter("stext");
			   int page = Integer.parseInt(req.getParameter("page"));
			   int jcount = dao.getSearchPinCount(stext);

			   List<String[]> pinlist = dao.getSearchPinList(page, stext);
			   int pagecount = 0;
			   if (jcount != 0) {
				   pagecount = jcount / 9;
				   if (jcount%9 > 0) {
					   pagecount++;
				   }
			   }
			   req.setAttribute("stext", stext);
			   req.setAttribute("page", page);
			   req.setAttribute("pagecount", pagecount);
			   req.setAttribute("pinlist", pinlist);
			   dispatch("Pinsearch.jsp", req, resp);
		   }
		   
	 }
	 
	 public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)
	         throws ServletException, IOException {

	      RequestDispatcher dispatch = req.getRequestDispatcher(urls);
	      dispatch.forward(req, resp);
	   }
	
}
