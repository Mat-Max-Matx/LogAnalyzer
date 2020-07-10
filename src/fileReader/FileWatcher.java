package fileReader;


import java.io.File;

public abstract class FileWatcher{
    private long timeStamp;
    private File file;

    public FileWatcher(File file){
        this.file=file;
        this.timeStamp=file.lastModified();
    }

    public final boolean esegui(){ // controlla se un file è stato modificato, il metodo viene chiamato dalla business
        long timeStamp = file.lastModified();

        if(this.timeStamp!=timeStamp){
            this.timeStamp=timeStamp;
            onChange(file);
            return true;
        }else{
            return false;
        }
    }
    protected abstract void onChange(File file);
}