package com.fiap.roupapp.roupapp.controller;

import com.fiap.roupapp.roupapp.database.CarregarDatabase;
import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.jms.JmsProducer;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.utils.ItextUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CarregarDatabase carregarDatabase;
    private JmsProducer jmsProducer;
    private ItextUtils itextUtils;

    public ClienteController(ClienteRepository clienteRepository, CarregarDatabase carregarDatabase, JmsProducer jmsProducer, ItextUtils itextUtils) {
        this.clienteRepository = clienteRepository;
        this.carregarDatabase = carregarDatabase;
        this.jmsProducer = jmsProducer;
        this.itextUtils = itextUtils;
    }




    @PostMapping
    public ResponseEntity carregarBase() {

        carregarDatabase.carregarBase();

        return ResponseEntity.ok().body("");


    }

    @PostMapping("/{id}")
    public ResponseEntity gerarPdf(@PathVariable Integer id) {

        Cliente cliente = clienteRepository.findById(id).get();

        Document cupom = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(cupom, new FileOutputStream("C:\\Users\\Zup\\Desktop\\cupom.pdf"));

            cupom.open();
            cupom.add(new Paragraph("Gerando PDF"));
            cupom.add(new Paragraph(cliente.getCpf()));
            PdfContentByte pdfContentByte = writer.getDirectContent();
             Image barCode128 = itextUtils.createBarCode(pdfContentByte);
             Image qrCode = itextUtils.createQrCode();

             cupom.add(barCode128);
             cupom.add(qrCode);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        cupom.close();

        return ResponseEntity.ok().body("");

    }

    @RequestMapping("/jms/{message}")
    public ResponseEntity testeMq(@PathVariable("message") String message) {

        jmsProducer.processMessaging(message);


        return ResponseEntity.ok().body("");
    }
}
