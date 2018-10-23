package com.company.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {

    public static void copiarImagen(String rutaOrigen, String nombreDestino) throws IOException {
        Path origen = FileSystems.getDefault().getPath(rutaOrigen);

        FileOutputStream destino = new FileOutputStream(
                new File(System.getProperty("user.dir") +
                        File.separator + "imagenes" + File.separator + nombreDestino));

        Files.copy(origen, destino);
    }
}
