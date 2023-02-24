package servlet.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ExportFavoriteExcel;
import common.ExportFavoritePDF;
import common.ExportShareExcel;
import common.ExportSharePDF;
import common.PageInfo;
import common.PageType;
import dao.FavoriteDao;
import dao.ShareDao;
import dto.FavoriteUser;
import dto.ShareUser;

@WebServlet({ "/ReportPage", "/ReportPage/Favorites/excel", "/ReportPage/Favorites/PDF", "/ReportPage/Shares/excel",
		"/ReportPage/Shares/PDF" })
public class ReportPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/ReportPage/Favorites/excel":
			favoriteExcel(request, response);
			break;
		case "/ReportPage/Favorites/PDF":
			favoritePDF(request, response);
			break;
		case "/ReportPage/Shares/excel":
			shareExcel(request, response);
			break;
		case "/ReportPage/Shares/PDF":
			sharePDF(request, response);
			break;
		default:
			favoriteAll(request, response);
			shareAll(request, response);
			PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMRNT_PAGE);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void favoriteAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();
			List<FavoriteUser> listFavoriteUserAll = dao.findFavoriteUser();
			request.setAttribute("listFavoriteUserAll", listFavoriteUserAll);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	private void shareAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ShareDao dao = new ShareDao();
			List<ShareUser> listShareUserAll = dao.findShareUser();
			request.setAttribute("listShareUserAll", listShareUserAll);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	private void favoriteExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=favoriteExcel_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		FavoriteDao dao = new FavoriteDao();
		List<FavoriteUser> list = dao.findFavoriteUser();
		ExportFavoriteExcel export = new ExportFavoriteExcel(list);
		export.export(response);

		request.setAttribute("message",
				"Exported excel file successfully, check the file in the mail you downloaded !");

		favoriteAll(request, response);
		shareAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMRNT_PAGE);
	}

	private void favoritePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=favoritePDF_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		FavoriteDao dao = new FavoriteDao();
		List<FavoriteUser> list = dao.findFavoriteUser();
		ExportFavoritePDF export = new ExportFavoritePDF(list);
		export.writeHeaderLine(response);

		request.setAttribute("message", "Exported PDF file successfully, check the file in the mail you downloaded !");

		favoriteAll(request, response);
		shareAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMRNT_PAGE);
	}

	private void shareExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=shareExcel_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		ShareDao dao = new ShareDao();
		List<ShareUser> list = dao.findShareUser();
		ExportShareExcel export = new ExportShareExcel(list);
		export.export(response);

		request.setAttribute("message",
				"Exported excel file successfully, check the file in the mail you downloaded !");

		favoriteAll(request, response);
		shareAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMRNT_PAGE);
	}

	private void sharePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=sharePDF_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		ShareDao dao = new ShareDao();
		List<ShareUser> list = dao.findShareUser();
		ExportSharePDF export = new ExportSharePDF(list);
		export.writeHeaderLine(response);

		request.setAttribute("message", "Exported PDF file successfully, check the file in the mail you downloaded !");

		favoriteAll(request, response);
		shareAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMRNT_PAGE);
	}
}
