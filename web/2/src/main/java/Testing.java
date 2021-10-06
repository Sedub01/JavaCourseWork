import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//@WebServlet("/TeamList")
/**
 * Servlet implementation class Testing
 */
public class Testing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object[][] team;
	private String[] roles;
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public Testing() {
		super();
		team = new Object[][] {
			{"����� ����������", 0, "���������", 15000},
			{"����� �����", 1, "�����-���������", 30000},
			{"���� �������", 2, "������������", 25000},
			{"������ ������", 3, "�������", 19000}
		};
		roles = new String[] {"�������", "����������", "������������", "��������"};
	}
	/**
	* Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	methods.
	*
	* @param request servlet request
	* @param response servlet response
	* @throws ServletException if a servlet-specific error occurs
	* @throws IOException if an I/O error occurs
	*/
	protected void processRequest(HttpServletRequest request, HttpServletResponse
	response)
	throws ServletException, IOException {
		// ������� ���� ��������� ��� ���������� �������
		request.setCharacterEncoding("utf-8");
		// ������ ��������� name �� �������
		String salary = request.getParameter("salary");
		// ������� ���� ����������� ��� ������ (� ��� ����� ���������)
		response.setContentType("text/html;charset=UTF-8");
		// ��������� ������ ��� ������ ������
		PrintWriter out = response.getWriter();
		try {
			// �������� HTML-��������
			out.println("<html>");
			out.println("<head><title>������ ���� �����������</title></head>");
			out.println("<body>");
			out.println("<h1>��� ��� ����������" + ((salary == null)? " ": " � ��������� >= " 
					+ salary + "$") + "</h1>");
			out.println("<table border='1'>");
			out.println("<tr><td><b>��� �������</b></td>"
					+ "<td><b>�������������</b></td>"
					+ "<td><b>�����</b></td>"
					+ "<td><b>��������</b></td></tr>");
			
			for (Object[] temp : team)
				if (salary == null || (int)temp[3] >= Integer.parseInt(salary))
					out.println("<tr><td>" + temp[0] + "</td><td>" + roles[(int)temp[1]] + "</td><td>"
					+ temp[2] + "</td><td>" + Integer.toString((int)temp[3]) + "</td></tr>");
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			// �������� ������ ������
			out.close();
		}
	}
	/**
	* Handles the HTTP
	* <code>GET</code> method.
	*
	* @param request servlet request
	* @param response servlet response
	* @throws ServletException if a servlet-specific error occurs
	* @throws IOException if an I/O error occurs
	*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
	/**
	* Handles the HTTP
	* <code>POST</code> method.
	* @param request servlet request
	* @param response servlet response
	* @throws ServletException if a servlet-specific error occurs
	* @throws IOException if an I/O error occurs
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
}