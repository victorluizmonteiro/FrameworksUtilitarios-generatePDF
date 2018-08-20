package com.fiap.roupapp.roupapp.jms;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.service.ClienteService;
import com.fiap.roupapp.roupapp.service.PedidoService;
import com.fiap.roupapp.roupapp.utils.ItextUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

@Component
public class JmsConsumerPDF {


    private String queueName;
    private ItextUtils itextUtils;
    private ClienteService clienteService;
    private PedidoService pedidoService;


    @Autowired
    public JmsConsumerPDF(@Value("${mq.queue.pdf}") String queueName,
                          ItextUtils itextUtils,
                          PedidoService pedidoService,
                          ClienteService clienteService
                       ) {
        
        this.queueName = queueName;
        this.itextUtils = itextUtils;
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;

    }

    /*@Transactional
    public Pedido buscarPedido(Integer id) {
        return pedidoRepository.findById(id).get();
    }*/


    @JmsListener(destination = "${mq.queue.pdf}", containerFactory = "jsaFactory")
    public void receiveMessage(Integer pedidoId) throws ExecutionException, InterruptedException {


        System.out.println("RECEBENDO " + pedidoId + ".....");

        Pedido pedido = pedidoService.findPedidoById(pedidoId);
       Cliente cliente = clienteService.findById(pedido.getCliente().getId());

        Document cupom = new Document();


            try{


                //PdfWriter writer = PdfWriter.getInstance(cupom, new FileOutputStream("D:\\PDFS\\cupom "+pedido.getId()+"-"+ Calendar.getInstance().getTimeInMillis() + ".pdf"));
                PdfWriter writer = PdfWriter.getInstance(cupom, new FileOutputStream("/Users/monteiro/Documents/PDF/cupom"+pedido.getId()+"-"+ Calendar.getInstance().getTimeInMillis() + ".pdf"));

                cupom.open();
                cupom.add(new Paragraph("Gerando PDF"));
                cupom.add(new Paragraph(cliente.getCpf()));
                cupom.add(new Paragraph(pedido.getLocalDateTime().getYear() + " - " +  pedido.getLocalDateTime().getMonth() + " - " + pedido.getLocalDateTime().getDayOfMonth()));
                PdfContentByte pdfContentByte = writer.getDirectContent();
                Image barCode128 = itextUtils.createBarCode(pdfContentByte);
                Image qrCode = itextUtils.createQrCode();

                cupom.add(barCode128);
                cupom.add(qrCode);

                System.out.println("Cupom  GERADO COM SUCESSO !");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            cupom.close();


        }
}
