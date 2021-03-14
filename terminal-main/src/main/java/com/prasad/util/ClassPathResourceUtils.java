package com.prasad.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassPathResourceUtils {
    /**
     * Get the list of "source files" in the specified "resource path"
     * resource files in the path. The resources are sorted alphabetically and
     * reference the full resource path (i.e. it includes the starting prefix
     * of directory.
     *
     * @param path directory in which resources exist.
     * @param returnFullPaths if true then returned paths include the direcotry prefix.
     * @param clazz class requesting the load
     * @return a collection of sorted child paths (with or without directory prefix)
     * @throws IOException
     */
    public static List<String> getResourceFilesInPath(String path, boolean returnFullPaths, Class clazz) throws IOException {
        List<String> fileNames = new ArrayList<>();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1); // remove trailing slash
        }

        try (
            InputStream in = getResourceAsStream(path, clazz);
            BufferedReader br = new BufferedReader(new InputStreamReader(in))
        ) {
            String resource;

            while ((resource = br.readLine()) != null) {
                String pathToReturn = returnFullPaths ? path + "/" + resource : resource;
                fileNames.add(pathToReturn);
            }
            Collections.sort(fileNames);
        }
        return fileNames;
    }

    /**
     * InputStream to read the fully qualified resource path.
     *
     * @param resource path to the resource
     * @param clazz class requesting the load
     * @return
     */
    public static InputStream getResourceAsStream(String resource, Class clazz) {
        final InputStream in = clazz.getResourceAsStream(resource);
        return in == null ? ClassLoader.getSystemClassLoader().getResourceAsStream(resource) : in;
    }

    /**
     * Read the contents of the fully qualified resource path.
     *
     * @param resource path to the resource
     * @param clazz class requesting the load
     * @return a string of the resource contents.
     * @throws Exception
     */
    public static String getResourceContent(String resource, Class clazz) {
        try {
            byte[] bytes = getResourceAsBytes(resource, clazz);
            return new String(bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Cannot read contents of resource " + resource);
            return null;
        }
    }

    /**
     * Read the contents of the fully qualified resource path.
     *
     * @param resource path to the resource
     * @param clazz class requesting the load
     * @return a byte array of the resource contents.
     * @throws Exception
     */
    public static byte[] getResourceAsBytes(String resource, Class clazz) throws Exception {
        InputStream in = getResourceAsStream(resource, clazz);
        if (in == null) {
            return null;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            while (in.available() > 0) {
                out.write(in.read());
            }
            return out.toByteArray();
        }
    }

    //    public static ClassLoader getContextClassLoader() {
    //        return Thread.currentThread().getContextClassLoader();
    //    }
}
