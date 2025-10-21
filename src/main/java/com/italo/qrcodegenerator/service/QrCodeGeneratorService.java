package com.italo.qrcodegenerator.service;


import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.italo.qrcodegenerator.dtos.QrCodeResponseDTO;
import com.italo.qrcodegenerator.ports.StoragePorts;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGeneratorService {

    private final StoragePorts storagePorts;

    public QrCodeGeneratorService(StoragePorts storagePorts) {
        this.storagePorts = storagePorts;
    }

    public QrCodeResponseDTO generateQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, 250, 250);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        byte[] pngQrCode = outputStream.toByteArray();

        String url = uploadQrCode(pngQrCode);

        return new QrCodeResponseDTO(url);
    }

    public String uploadQrCode(byte[] filedata) throws WriterException, IOException {
        return storagePorts.uploadFile(filedata, UUID.randomUUID().toString(), "image/png");
    }
}
