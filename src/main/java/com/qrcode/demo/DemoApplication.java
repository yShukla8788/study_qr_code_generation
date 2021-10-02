package com.qrcode.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.Hashtable;

@SpringBootApplication
public class DemoApplication
{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
		String fileName ="qrCode_"+new Date().getTime() +".png";
		RequestBody requestBody = RequestBody.builder().QrCodeText("Dummy QR Code text").build();
		String pathOfQrImage = generateQRCode(requestBody.getQrCodeText(), 350, 350, fileName);
		System.out.print("pathOfQrImage : "+ pathOfQrImage);
	}

	private static String generateQRCode(String text, int width, int height, String filePath) throws WriterException, IOException
	{
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return path.toAbsolutePath().toString();
	}
}
