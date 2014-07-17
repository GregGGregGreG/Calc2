package com.greg.calculator.view.desktop.history.storage.impl;

import com.greg.calculator.entyty.History;
import com.greg.calculator.view.desktop.history.storage.HistoryStorageService;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(value = "addIntoFile")
public class AddIntoFile implements HistoryStorageService {
    private final String filename = "file.txt";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    private String data = dateFormat.format(new Date());
    private History history;


    @Override
    public void addHistory(History history) {
        if (this.history == null) {
            writeFile("----   new session -----" + " " + data + " -------");
        }
        this.history = history;
        writeFile(history.toString());
    }

    @Override
    public List<History> findAll() {
        return null;
    }

    private void writeFile(String str) {
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "rw");
            file.skipBytes((int) file.length());
            file.writeBytes(str + "\n");
            file.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
