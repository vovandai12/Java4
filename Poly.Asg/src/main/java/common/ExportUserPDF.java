package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import model.User;

public class ExportUserPDF {

	private List<User> users;

	public ExportUserPDF(List<User> users) {
		this.users = users;
	}

	public void writeHeaderLine(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph1 = new Paragraph("List of the users", fontTiltle);
		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph1);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(80);
		table.setWidths(new int[] { 3, 3, 3, 3 });
		table.setSpacingBefore(5);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);
		cell.setPhrase(new Phrase("Username", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fullname", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Admin", font));
		table.addCell(cell);

		for (User user : users) {
			table.addCell(user.getUsername());
			table.addCell(user.getFullname());
			table.addCell(user.getEmail());
			table.addCell(String.valueOf(user.getAdmin()));
		}

		document.add(table);
		document.close();
	}
}