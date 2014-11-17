package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Booking;
import model.Flight;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class PrintCtrl
 */
public class PrintCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Font header1 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
	private static Font header2 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		Booking booking = (Booking)session.getAttribute("booking");
		@SuppressWarnings("unchecked")
		TreeMap<Integer, Flight> flights = (TreeMap<Integer, Flight>)session.getAttribute("flights");
		Flight flight = flights.get(booking.getFlight_id());

		OutputStream out = response.getOutputStream();
		try {
            Document document = new Document();
            Paragraph paragraph = null;
            PdfWriter.getInstance(document, out);
            document.open();
            paragraph = new Paragraph("Here is the summary of your airplane e-ticket", header1);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(new Paragraph(" "));
            paragraph = new Paragraph("Passenger Information", header2);
            document.add(paragraph);
            document.add(new Paragraph("Full Name: " + booking.getPassenger()));
            document.add(new Paragraph("Day of Birth: " + booking.getDob()));
            document.add(new Paragraph("Gender: " + booking.getGender()));
            document.add(new Paragraph(" "));
            paragraph = new Paragraph("Flight Information", header2);
            document.add(paragraph);
            document.add(new Paragraph("Flight: " + flight.getOperatorName() + " " + flight.getOperator().name() + flight.getFlight_id()));
            document.add(new Paragraph("Departing from " + flight.getSourceCity() + " at " + flight.getDepartureTime()));
            document.add(new Paragraph("Arriving to " + flight.getDestinCity() + " at " + flight.getArrivalTime()));
            document.add(new Paragraph("Cabin: " + booking.getCabinName()));
            document.add(new Paragraph("Reseverd seat/s: " + booking.getSeats()));
            document.add(new Paragraph("Plane model: " + flight.getPlaneModel()));
            document.add(new Paragraph(" "));
            paragraph = new Paragraph("Payment Information", header2);
            document.add(paragraph);
            document.add(new Paragraph("Holder name: " + account.getHolder_id()));
            document.add(new Paragraph("Account number: " + account.getAccount_id()));
            document.add(new Paragraph("Routing number: " + account.getRouting_number()));
            document.add(new Paragraph("Total charged: " + booking.getTotal()));
            document.close();
        }catch (DocumentException exc){
        	throw new IOException(exc.getMessage());
        }finally {            
            out.close();
        }
	}
}
