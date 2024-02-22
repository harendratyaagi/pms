package com.ecomerce.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.*;
import java.io.IOException;

public class InvoicePdfTableGeneratorUtil {

    PDDocument document;
    PDPageContentStream contentStream;
    private int[] colWidth;
    private int cellHeight;
    private int xPosition;

    private int yPosition;
    private int colPosition;

    private int xInitialPosition;

    private float fontSize;
    private PDFont font;
    private Color fontColor;

    public InvoicePdfTableGeneratorUtil(PDDocument document, PDPageContentStream contentStream, int[] colWidth, int cellHeight, int xPosition, int yPosition, int colPosition, int xInitialPosition, float fontSize, PDFont font, Color fontColor) {
        this.document = document;
        this.contentStream = contentStream;
        this.colWidth = colWidth;
        this.cellHeight = cellHeight;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.colPosition = colPosition;
        this.xInitialPosition = xInitialPosition;
        this.fontSize = fontSize;
        this.font = font;
        this.fontColor = fontColor;
    }

    public InvoicePdfTableGeneratorUtil(PDDocument document, PDPageContentStream contentStream) {
        this.document = document;
        this.contentStream = contentStream;
    }

    public void setTableFont(PDFont font, float fontSize, Color fontColor) {
        this.font = font;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
    }

    public void setTable(int[] colWidth, int cellHeight, int xPosition, int yPosition) {
        this.colWidth = colWidth;
        this.cellHeight = cellHeight;
        this.xInitialPosition = this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void addCell(String text, Color fillColor) throws IOException {
        contentStream.setStrokingColor(0f);
        if (fillColor != null) {
            contentStream.setNonStrokingColor(fillColor);
        }
        contentStream.addRect(xPosition, yPosition, colWidth[colPosition], cellHeight);
        if (fillColor == null) contentStream.stroke();
        else contentStream.fillAndStroke();

        contentStream.beginText();
        contentStream.setNonStrokingColor(fontColor);
//        if (colPosition == 4 || colPosition == 2) {
//            float fontWidth = font.getStringWidth(text) / 1000 * fontSize;
//            contentStream.newLineAtOffset(xPosition + colWidth[colPosition] - 20 - fontWidth, yPosition + 10);
//        } else {
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(xPosition + 5, yPosition + 10);
//        }
        contentStream.showText(text);
        contentStream.endText();
        xPosition = xPosition + colWidth[colPosition];
        colPosition++;
        if (colPosition == colWidth.length) {
            colPosition = 0;
            xPosition = xInitialPosition;
            yPosition = yPosition-cellHeight;
        }
    }

    public PDDocument getDocument() {
        return document;
    }

    public void setDocument(PDDocument document) {
        this.document = document;
    }

    public PDPageContentStream getContentStream() {
        return contentStream;
    }

    public void setContentStream(PDPageContentStream contentStream) {
        this.contentStream = contentStream;
    }

    public int[] getColWidth() {
        return colWidth;
    }

    public void setColWidth(int[] colWidth) {
        this.colWidth = colWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public int getxInitialPosition() {
        return xInitialPosition;
    }

    public void setxInitialPosition(int xInitialPosition) {
        this.xInitialPosition = xInitialPosition;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public PDFont getFont() {
        return font;
    }

    public void setFont(PDFont font) {
        this.font = font;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
}
