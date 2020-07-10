package fileReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadingOnTime extends FileWatcher {

    private ArrayList<String> arrayList;
    private BufferedReader br;
    private String sCurrentLine;

    public FileReadingOnTime(File file, ArrayList<String> arrayList) { //viene passato il file di lettura e l'arraylist dove inserire il log
        super(file);
        this.arrayList=arrayList;
    }

    protected void onChange(File file) { // questo metodo legge il file e viene chiamato dalla classe abstract
        try {
        	String lastLine="";
            br = new BufferedReader(new FileReader(file));

            while ((sCurrentLine = br.readLine()) != null) {
                lastLine=sCurrentLine;
            }
            arrayList.add(lastLine);
        } catch (IOException e) { //gestione eccezioni
            e.printStackTrace(); //volendo si può fare una gestione degli errori
        } finally {
            try {

                if (br != null)
                    br.close();     //chiusura stream
            } catch (IOException ex) {
                ex.printStackTrace(); //volendo si può fare una gestione degli errori
            }

        }
    }

}