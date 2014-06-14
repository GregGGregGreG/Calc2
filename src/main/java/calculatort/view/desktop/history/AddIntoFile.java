package calculatort.view.desktop.history;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddIntoFile implements CalculatorHistory {
    public final String filename = "C:\\folder\\file.txt";
    private SimpleDateFormat dateFormat = new SimpleDateFormat();
    private String data = dateFormat.format(new Date());

    public AddIntoFile() {
        writeFile("----   new session -----" + " " + data + " -------");
    }

    @Override
    public void addHistory(String expression) {
        writeFile(expression);
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
