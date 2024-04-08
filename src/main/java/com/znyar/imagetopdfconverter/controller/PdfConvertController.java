package com.znyar.imagetopdfconverter.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PdfConvertController {

    private final File dir = new File("upload-dir");

    @GetMapping("/export")
    public void exportPdf(HttpServletResponse response) throws IOException, DocumentException {

        response.setContentType("application/pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            Image image = Image.getInstance(dir + "/" + file.getName());
            image.scaleToFit(document.getPageSize().getWidth(), document.getPageSize().getHeight());
            image.setAbsolutePosition((document.getPageSize().getWidth() - image.getScaledWidth()) / 2,
                    (document.getPageSize().getHeight() - image.getScaledHeight()) / 2);
            document.newPage();
            document.add(image);
        }

        response.flushBuffer();
        document.close();

    }

}
