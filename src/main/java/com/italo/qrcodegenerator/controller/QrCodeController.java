package com.italo.qrcodegenerator.controller;


import com.italo.qrcodegenerator.dtos.QrCodeRequestDTO;
import com.italo.qrcodegenerator.dtos.QrCodeResponseDTO;
import com.italo.qrcodegenerator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeResponseDTO> generateQRCode(@RequestBody @Validated QrCodeRequestDTO qrCodeRequestDTO) {
        try {
            String url = qrCodeRequestDTO.url();
            QrCodeResponseDTO response = qrCodeGeneratorService.generateQrCode(url);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
