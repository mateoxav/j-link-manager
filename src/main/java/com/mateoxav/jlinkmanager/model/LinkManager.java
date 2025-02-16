package com.mateoxav.jlinkmanager.model;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkManager {
    private static final String JSON_FILE = System.getProperty("user.home") + "/links_manager.json";
    private List<Link> links;
    private ObjectMapper objectMapper;

    public LinkManager() {
        objectMapper = new ObjectMapper();
        links = loadLinks();
    }

    private List<Link> loadLinks() {
        File file = new File(JSON_FILE);

        if (!file.exists()) {
            System.out.println("⚠️ File not found, a new one will be created.");
            return new ArrayList<>();
        }

        try {
            System.out.println("📂 Loading links from: " + JSON_FILE);
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            System.err.println("❌ Error reading JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveLinks() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_FILE), links);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLink(Link enlace) {
        links.add(enlace);
        saveLinks();
    }

    public void editLink(int index, Link nuevoEnlace) {
        if (index >= 0 && index < links.size()) {
            links.set(index, nuevoEnlace);
            saveLinks();
        }
    }

    public void removeLink(int index) {
        if (index >= 0 && index < links.size()) {
            links.remove(index);
            saveLinks();
        }
    }

    public List<Link> getLinks() {
        return links;
    }
}