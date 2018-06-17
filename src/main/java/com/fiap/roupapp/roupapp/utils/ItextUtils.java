package com.fiap.roupapp.roupapp.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import org.springframework.stereotype.Component;

@Component
public class ItextUtils {


    public Image createQrCode() throws BadElementException {
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("roupapp",1000,1000,null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100,100);

        return codeQrImage;


    }
    public Image createBarCode(PdfContentByte pdfContentByte){
        Barcode128 barcode128 = new Barcode128();
        barcode128.setCode("teste");
        barcode128.setCodeType(Barcode.CODE128);
        Image code128Image = barcode128.createImageWithBarcode(pdfContentByte,null,null);

            return code128Image;
    }


}
