package ru.devteam.resume.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.devteam.resume.dtos.EducationDto;
import ru.devteam.resume.dtos.ResumeFullDto;
import ru.devteam.resume.dtos.WorkDto;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfFileService {
    @Value("${storage.path}")
    private String path;

    @SneakyThrows
    public String generate(ResumeFullDto resume) {

        BaseFont bf_russian = BaseFont.createFont("./resources/ENIGMAU.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //BaseFont bf_russian = BaseFont.createFont("./resources/calibrib.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontH1o = new Font(bf_russian, 44, Font.BOLD, new BaseColor(195, 88, 49));
        Font fontH1b = new Font(bf_russian, 44, Font.BOLD, BaseColor.BLACK);
        Font fontH2 = new Font(bf_russian, 22, Font.BOLD, new BaseColor(195, 88, 49));
        Font fontH3 = new Font(bf_russian, 14, Font.BOLD, new BaseColor(195, 88, 49));
        Font fontH3dg = new Font(bf_russian, 18, Font.NORMAL, BaseColor.DARK_GRAY);
        Font fontH3lg = new Font(bf_russian, 18, Font.NORMAL, new BaseColor(120, 120, 120));
        Font fontH4lg = new Font(bf_russian, 12, Font.NORMAL, new BaseColor(120, 120, 120));

        Document document = new Document();
        File file = new File(path +"\\resume" + resume.getId() + ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        Chunk BR = new Chunk(" ");

        Paragraph pUser = new Paragraph();
        pUser.add(new Chunk(resume.getFirstName(), fontH1b));
        pUser.add(new Chunk(" " + resume.getLastName(), fontH1o));
        pUser.setSpacingAfter(5F);
        document.add(pUser);
        document.add(myParagraph(resume.getEmail(), fontH3, 25F));

        //document.add(new Paragraph(new Chunk("ЖЕЛАЕМАЯ ДОЛЖНОСТЬ", fontH2)));
        //document.add(new Paragraph(new Chunk(" ".repeat(95), fontH2).setUnderline(1F, 28F)));
        document.add(myParagraph(resume.getPost().toUpperCase(), fontH3dg, 5F));
        document.add(myParagraph(resume.getAboutMyself(), fontH4lg, 5F));
        document.add(BR);

        document.add(new Paragraph(new Chunk("ОПЫТ РАБОТЫ", fontH2)));
        document.add(new Paragraph(new Chunk(" ".repeat(95), fontH2).setUnderline(1F, 28F)));

        for (WorkDto work : resume.getWorks()) {

            document.add(myParagraph(work.getPost().toUpperCase(), fontH3dg, 0F));
            Paragraph pDate = new Paragraph();
            pDate.add(new Chunk(work.getStartWork().getMonthValue() + "." + work.getStartWork().getYear() + " - " + work.getEndWork().getMonthValue() + "." + work.getEndWork().getYear(), fontH3));
            pDate.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(pDate);

            document.add(myParagraph(work.getOrganization(), fontH3lg, 5F));
            document.add(myParagraph(work.getProgress(), fontH4lg, 25F));
        }
        document.add(new Paragraph(new Chunk("ОБРАЗОВАНИЕ", fontH2)));
        document.add(new Paragraph(new Chunk(" ".repeat(95), fontH2).setUnderline(1F, 28F)));

        for (EducationDto education : resume.getEducations()) {
            document.add(myParagraph(education.getOrganization().toUpperCase(), fontH3dg, 0F));
            Paragraph pDate = new Paragraph();
            pDate.add(new Chunk(education.getYearStart().getYear() + " - " +  education.getYearEnd().getYear(), fontH3));
            pDate.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(pDate);
            document.add(myParagraph(education.getSpeciality(), fontH3lg, 25F));
        }

        document.close();

        return (file.exists() ? file.getName() : "");
    }

    private Paragraph myParagraph(String str, Font font, Float spafter) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(str, font));
        p.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p.setSpacingAfter(spafter);
        return p;
    }


}
