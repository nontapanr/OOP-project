package cricketgame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
//PatNum


public abstract class InOutStream{
    protected String file;//byte file
    protected String file_dev;//file for developer
    protected String file_input;
    protected ObjectOutputStream stream_out;
    protected ObjectInputStream stream_in;
    protected DataOutputStream stream_out_data;
    protected DataInputStream stream_in_data;
    protected PrintWriter writer;
    //Phol edited
    public InOutStream() {
     
    }
    //Phol edited
    public InOutStream(String file,String file_dev,String file_input) {
        this.file = file;
        this.file_dev=file_dev;
        this.file_input=file_input;
    }
     public abstract void write_file();
     public abstract void read_file();
}