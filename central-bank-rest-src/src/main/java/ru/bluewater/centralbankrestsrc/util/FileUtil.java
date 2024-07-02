package ru.bluewater.centralbankrestsrc.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class FileUtil {
    public MultipartFile extractXMLMultipartFileFromZIPByteArray(byte[] zipBytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zipBytes);
        ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
        ZipEntry entry;
        ByteArrayOutputStream xmlOutputStream = new ByteArrayOutputStream();
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                    xmlOutputStream.write(buffer, 0, bytesRead);
                }
                break;
            }
        }
        zipInputStream.close();

        return createMultipartFileFromXMLContent(xmlOutputStream, entry);
    }

    public MultipartFile createMultipartFileFromXMLContent(ByteArrayOutputStream xmlOutputStream, ZipEntry entry) {

        return new MultipartFile() {
            @Override
            public String getName() {
                return entry.getName();
            }

            @Override
            public String getOriginalFilename() {
                return entry.getName();
            }

            @Override
            public String getContentType() {
                // You can set the appropriate content type here, if known
                return "application/xml";
            }

            @Override
            public boolean isEmpty() {
                return xmlOutputStream.size() == 0;
            }

            @Override
            public long getSize() {
                return xmlOutputStream.size();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return xmlOutputStream.toByteArray();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(xmlOutputStream.toByteArray());
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                try (FileOutputStream fos = new FileOutputStream(dest)) {
                    fos.write(xmlOutputStream.toByteArray());
                }
            }
        };
    }
}
