import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.File;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class parseFile {
  public static void main(String[] args) throws IOException {
    Boolean firstLine = true;
    List<String> input = FilesUtil.readTextFileByLines("communities_data_cleaned_sel_cols.csv");

    File fout = new File("communities_data_cleaned_sel_cols_nb.csv");
    FileOutputStream fos = new FileOutputStream(fout);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

    for (String line : input) {
       String[] lineArray = line.split(",");

       if(firstLine){
        firstLine = false;
        bw.write(line);
        bw.newLine();
        continue;
       }

       if(Float.parseFloat(lineArray[13]) > 0.5){
         lineArray[13] = "1";
       } else {
         lineArray[13] = "0";
       }

      String editedLine = "";
       for(String newLine: lineArray){
         if(editedLine.equalsIgnoreCase(""))
           editedLine = newLine;
         else
           editedLine = editedLine + "," + newLine;
       }

       bw.write(editedLine);
       bw.newLine();

    }

      bw.close();
  }
}
