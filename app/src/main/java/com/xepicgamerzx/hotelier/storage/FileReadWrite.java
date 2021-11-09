package com.xepicgamerzx.hotelier.storage;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Deprecated
public class FileReadWrite<T> implements Serializable {
    /**
     * Generic object write data into file.
     *
     * @param save      <T> to be saved.
     * @param file_name String describing the name of the file to create and save to.
     * @param context   Context of the app.
     */
    public void writeData(T save, String file_name, Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(file_name, Context.MODE_PRIVATE);
            ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
            writer.writeObject(save);
            writer.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AARSS", "Problem saving file.", e);
        }
    }

    /**
     * Generic object read data into file.
     *
     * @param file_name String describing the name of the file to read from.
     * @param context   Context of the app.
     * @return <T> object read from the file.
     */
    @SuppressWarnings("unchecked")
    public T readData(String file_name, Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput(file_name);
            ObjectInputStream reader = new ObjectInputStream(fileInputStream);
            T data = (T) reader.readObject();
            reader.close();
            fileInputStream.close();
            return data;
        } catch (IOException e) {
            Log.e("AARSS", "Problem reading file. Wrong file name?", e);
            return null;
        } catch (ClassNotFoundException e) {
            Log.e("AARSS", "Incompatible file class. Possibly corrupt files?", e);
            return null;
        }
    }
}
