package com.italo.qrcodegenerator.ports;

public interface StoragePorts {

    String uploadFile(byte[] data, String fileName, String contentType);
}
