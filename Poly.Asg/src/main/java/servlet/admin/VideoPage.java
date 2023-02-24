package servlet.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import common.ExportVideoExcel;
import common.ExportVideoPDF;
import common.PageInfo;
import common.PageType;
import common.Validation;
import dao.VideoDao;
import model.Video;
import utils.UploadUtils;

@WebServlet({ "/VideoPage", "/VideoPage/new", "/VideoPage/insert", "/VideoPage/update", "/VideoPage/delete",
		"/VideoPage/list", "/VideoPage/edit", "/VideoPage/page", "/VideoPage/excel", "/VideoPage/PDF" })
@MultipartConfig
public class VideoPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VideoPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/VideoPage/new":
			reset(request, response);
			break;
		case "/VideoPage/delete":
			delete(request, response);
			break;
		case "/VideoPage/list":
			reset(request, response);
			break;
		case "/VideoPage/edit":
			edit(request, response);
			break;
		case "/VideoPage/page":
			pagination(request, response);
			break;
		case "/VideoPage":
			pagination(request, response);
			break;
		case "/VideoPage/excel":
			excel(request, response);
			break;
		case "/VideoPage/PDF":
			PDF(request, response);
			break;
		default:
			pagination(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/VideoPage/insert":
			insert(request, response);
			break;
		case "/VideoPage/update":
			update(request, response);
			break;
		case "/VideoPage/delete":
			delete(request, response);
			break;
		default:
			pagination(request, response);
			break;
		}
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/No_Image_Available.jpg");
		request.setAttribute("video", video);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		if (videoId == null) {

			request.setAttribute("error", "Error: Video id is required !");
		}
		try {

			VideoDao dao = new VideoDao();
			dao.delete(videoId);

			request.setAttribute("message", "Video is deleted !");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		pagination(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Video video = new Video();
			BeanUtils.populate(video, request.getParameterMap());

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(video.getVideoId(), sb, "<br>Video ID is required");
			Validation.validateEmpty(video.getTitle(), sb, "<br>Video title is required");
			Validation.validateEmpty(video.getDescription(), sb, "<br>Video description is required");
			Validation.validateMinLenght(video.getVideoId(), 5, sb,
					"<br>Video Id must be at least 5 characters long !");
			Validation.validateMinLenght(video.getTitle(), 10, sb,
					"<br>Video title must be at least 10 characters long !");
			Validation.validateMinLenght(video.getDescription(), 10, sb,
					"<br>Video descreiption must be at least 10 characters long !");

			if (sb.length() > 0) {

				request.setAttribute("video", video);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
				return;
			} else {
				VideoDao dao = new VideoDao();
				Video oVideo = dao.findById(video.getVideoId());

				if (oVideo == null) {
					request.setAttribute("video", video);
					request.setAttribute("error", "Error: the video does not exist, please press the create button !");
					PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
					return;
				}

				if (request.getPart("cover").getSize() == 0) {

					video.setPoster(oVideo.getPoster());
				} else {

					video.setPoster("uploads/"
							+ UploadUtils.processUploadField("cover", request, "/uploads", video.getPoster()));
				}

				dao.update(video);
				request.setAttribute("message", "Video is updated !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
		}

		pagination(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Video video = new Video();
			BeanUtils.populate(video, request.getParameterMap());

			video.setPoster(
					"uploads/" + UploadUtils.processUploadField("cover", request, "/uploads", video.getPoster()));

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(video.getVideoId(), sb, "<br>Video ID is required");
			Validation.validateEmpty(video.getTitle(), sb, "<br>Video title is required");
			Validation.validateEmpty(video.getDescription(), sb, "<br>Video description is required");
			Validation.validateMinLenght(video.getVideoId(), 5, sb,
					"<br>Video Id must be at least 5 characters long !");
			Validation.validateMinLenght(video.getTitle(), 10, sb,
					"<br>Video title must be at least 10 characters long !");
			Validation.validateMinLenght(video.getDescription(), 10, sb,
					"<br>Video descreiption must be at least 10 characters long !");

			if (sb.length() > 0) {
				request.setAttribute("video", video);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
				return;
			} else {
				VideoDao dao = new VideoDao();

				if (dao.findById(video.getVideoId()) != null) {
					request.setAttribute("video", video);
					request.setAttribute("error", "Error: the video already exists please press the update button !");
					PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
					return;
				}

				dao.insert(video);
				request.setAttribute("message", "Video is inserted !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
		}

		pagination(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");

		if (videoId == null) {

			request.setAttribute("error", "Error: Video id is required !");
			pagination(request, response);
		}

		try {

			VideoDao dao = new VideoDao();
			Video video = dao.findById(videoId);

			request.setAttribute("video", video);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.VIDEO_FORM_MANAGEMRNT_PAGE);
	}

	private void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long count;
		int endPage;
		int pageSize = 5;
		int pageNumber;

		if (request.getParameter("page") != null) {
			pageNumber = Integer.parseInt(request.getParameter("page")) - 1;
		} else {
			pageNumber = 0;
		}

		try {
			VideoDao dao = new VideoDao();

			count = dao.count();
			endPage = (int) (count / pageSize) + 1;
			int firstResult = pageSize * pageNumber;
			List<Video> videos = dao.findAll(false, firstResult, pageSize);

			request.setAttribute("count", count);
			request.setAttribute("endPage", endPage);
			request.setAttribute("videos", videos);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMRNT_PAGE);
	}

	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=videos_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		VideoDao dao = new VideoDao();
		List<Video> list = dao.findAll();
		ExportVideoExcel export = new ExportVideoExcel(list);
		export.export(response);

		request.setAttribute("message",
				"Exported excel file successfully, check the file in the mail you downloaded !");
		pagination(request, response);
	}

	private void PDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=videos_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		VideoDao dao = new VideoDao();
		List<Video> list = dao.findAll();
		ExportVideoPDF export = new ExportVideoPDF(list);
		export.writeHeaderLine(response);

		request.setAttribute("message", "Exported PDF file successfully, check the file in the mail you downloaded !");
		pagination(request, response);
	}
}
