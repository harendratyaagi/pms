package com.ecomerce.services;


import com.ecomerce.util.InvoicePdfTableGeneratorUtil;
import com.ecomerce.util.InvoicePdfTextGeneratorUtil;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class PdfGenerator {

    @Value("${pdf.generator.generated.path}")
    String filePath;

    public void generatePDF() throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstpage = new PDPage(PDRectangle.A4);
        document.addPage(firstpage);
        String name = "Kavita bora";
        String callNo = "+919458338038";
        Format d_format = new SimpleDateFormat("dd/MM/yyyy");
        Format t_format = new SimpleDateFormat("HH:mm");
        int pageWidth = 600;
        int pageHeight = 900;
        if (firstpage.getTrimBox() != null) {
            pageWidth = (int) firstpage.getTrimBox().getWidth();
            pageHeight = (int) firstpage.getTrimBox().getHeight();
        }
        PDPageContentStream contentStream = new PDPageContentStream(document, firstpage);
        InvoicePdfTextGeneratorUtil invoicePdfTextGeneratorUtil = new InvoicePdfTextGeneratorUtil(document, contentStream);
        PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        PDFont italicFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_OBLIQUE);
        PDImageXObject headImage = PDImageXObject.createFromFile("src/main/resources/img/images.jpeg", document);
        contentStream.drawImage(headImage, 0, pageHeight - 235, pageWidth, 230);
        String[] contectDetails = new String[]{"123456789", "9999999999"};
        invoicePdfTextGeneratorUtil.addMultilineText(contectDetails, 18, (int) (500), 800, font, 15, Color.RED);
        invoicePdfTextGeneratorUtil.addSingleLineText("Smart Health Center", 25, pageHeight - 150, font, 40, Color.BLACK);

        invoicePdfTextGeneratorUtil.addSingleLineText("Customer Name :" + name, 25, pageHeight - 250, font, 16, Color.BLACK);
        invoicePdfTextGeneratorUtil.addSingleLineText("MO. NO. :" + callNo, 25, pageHeight - 274, font, 16, Color.BLACK);
        String invoiceNo = "Invoice # " + (int) Math.random() * 3;
        float textwidth = invoicePdfTextGeneratorUtil.getTextWidth(invoiceNo, font, 16);
        invoicePdfTextGeneratorUtil.addSingleLineText(invoiceNo, (int) (pageWidth - 25 - textwidth), pageHeight - 250, font, 16, Color.BLACK);

        float dateTextWidth = invoicePdfTextGeneratorUtil.getTextWidth(d_format.format(new Date()), font, 16);
        invoicePdfTextGeneratorUtil.addSingleLineText(d_format.format(new Date()), (int) (pageWidth - 25 - dateTextWidth), pageHeight - 274, font, 16, Color.BLACK);

        String time = t_format.format(new Date());
        float timeTextWidth = invoicePdfTextGeneratorUtil.getTextWidth("Time :" + time, font, 16);
        invoicePdfTextGeneratorUtil.addSingleLineText("Time :" + time, (int) (pageWidth - 25 - timeTextWidth), pageHeight - 298, font, 16, Color.BLACK);

        InvoicePdfTableGeneratorUtil invoicePdfTableGeneratorUtil = new InvoicePdfTableGeneratorUtil(document, contentStream);
        int[] cellWidth = new int[]{50, 280, 90, 60, 60};
        invoicePdfTableGeneratorUtil.setTable(cellWidth, 23, 15, pageHeight - 350);
        invoicePdfTableGeneratorUtil.setTableFont(font, 8, Color.BLACK);
        invoicePdfTableGeneratorUtil.addCell("SL No", Color.WHITE);
        invoicePdfTableGeneratorUtil.addCell("Product Details", Color.WHITE);
        invoicePdfTableGeneratorUtil.addCell("HSN Code", Color.WHITE);
        invoicePdfTableGeneratorUtil.addCell("GST %", Color.WHITE);
        invoicePdfTableGeneratorUtil.addCell("PRICE", Color.WHITE);

        for (int i = 1; i <= 49; i++) {
            if (invoicePdfTableGeneratorUtil.getyPosition() <= 20) {
                contentStream.close();
                PDPage page2 = new PDPage(PDRectangle.A4);
                document.addPage(page2);
                contentStream = new PDPageContentStream(document, page2);
                invoicePdfTableGeneratorUtil.setContentStream(contentStream);
                invoicePdfTableGeneratorUtil.setTable(cellWidth, 23, 15, pageHeight - 150);
            }
            invoicePdfTableGeneratorUtil.addCell("" + i, null);
            invoicePdfTableGeneratorUtil.addCell("Glass - CRHC- CRHC kkk k kdjgk kjkgdgjk jkdnkjg", null);
            invoicePdfTableGeneratorUtil.addCell("9001", null);
            invoicePdfTableGeneratorUtil.addCell("12", null);
            invoicePdfTableGeneratorUtil.addCell("130.00", null);
        }

        String text = "This is computer generated invoice.";
        float textWidth = invoicePdfTextGeneratorUtil.getTextWidth(text, font, 16);
        invoicePdfTextGeneratorUtil.addSingleLineText(text, 25, (int) (pageHeight - 450 - textWidth), font, 16, Color.BLACK);

        contentStream.close();
//        HashMap<Integer, String> overlayGuide = new HashMap<Integer, String>();
//        overlayGuide.put(1, "/home/kavita/Downloads/watermarkimage.pdf");
//        Overlay overlay = new Overlay();
//        overlay.setInputPDF(document);
//        overlay.setOverlayPosition(Overlay.Position.BACKGROUND);
//        overlay.overlay(overlayGuide).save();
        document.save(filePath);
        document.close();

    }

}
