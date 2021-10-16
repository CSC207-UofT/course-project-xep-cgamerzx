package com.xepicgamerzx.hotelier;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;

import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FileReadWriteTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.xepicgamerzx.hotelier", appContext.getPackageName());
    }

    @Test
    public void readWriteTest() {
        ArrayList<String> testData = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            testData.add("Elm_" + i);
        }
        FileReadWrite<ArrayList<String>> frw = new FileReadWrite<>();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        frw.writeData(testData, "file.dat", appContext);
        assertEquals(testData, frw.readData("file.dat", appContext));
    }
}