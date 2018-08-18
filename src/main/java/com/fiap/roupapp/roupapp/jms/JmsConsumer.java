package com.fiap.roupapp.roupapp.jms;

import com.fiap.roupapp.roupapp.entity.Cliente;
import com.fiap.roupapp.roupapp.entity.Pedido;
import com.fiap.roupapp.roupapp.entity.Produto;
import com.fiap.roupapp.roupapp.repository.ClienteRepository;
import com.fiap.roupapp.roupapp.repository.ItemRepository;
import com.fiap.roupapp.roupapp.repository.PedidoRepository;
import com.fiap.roupapp.roupapp.repository.ProdutoRepository;
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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class JmsConsumer {

    private JmsTemplate jmsTemplate;
    private String queueName;
    private ItextUtils itextUtils;
    private PedidoRepository pedidoRepository;
    private ItemRepository itemRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;


    @Autowired
    public JmsConsumer(JmsTemplate jmsTemplate, @Value("${mq.queue}") String queueName,
                       PedidoRepository pedidoRepository,
                       ItextUtils itextUtils,
                       ClienteRepository clienteRepository,
                       ProdutoRepository produtoRepository,
                       ItemRepository itemRepository
                       ) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
        this.itextUtils = itextUtils;
        this.pedidoRepository = pedidoRepository;
        this.itemRepository = itemRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;

    }


    @Transactional
    public Pedido buscarPedido(Integer pedidoId){

        return pedidoRepository.findById(pedidoId).get();

    }


    @Transactional
    public Cliente buscarCliente(Pedido pedido){

        return clienteRepository.findById(pedido.getCliente().getId()).get();

    }



    @JmsListener(destination = "${mq.queue}", containerFactory = "jsaFactory")
    public void receiveMessage(Integer pedidoId) throws ExecutionException, InterruptedException {


        System.out.println("RECEBENDO " + pedidoId + ".....");

        Pedido pedido = buscarPedido(pedidoId);
       Cliente cliente = buscarCliente(pedido);

        Document cupom = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(cupom, new FileOutputStream("D:\\PDFS\\cupom "+pedidoId+"-"+Calendar.getInstance().getTimeInMillis() + ".pdf"));

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
