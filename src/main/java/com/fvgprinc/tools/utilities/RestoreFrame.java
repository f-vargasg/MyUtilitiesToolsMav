/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities;

import com.fvgprinc.tools.common.string.MyCommonString;
import java.awt.Frame;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author fvargas
 */
public class RestoreFrame {

    Frame frame;
    String appName;
    String appProfilePath;
    String filename;

    public RestoreFrame(Frame frame, String appName, String filename) {
        this.frame = frame;
        this.appName = appName;
        this.filename = filename;
        this.appProfilePath = SystemUtilities.getDataDirectory(this.appName, MyCommonString.EMPTYSTR);
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public String getAppName() {
        return appName;
    }

    public String getFilename() {
        return filename;
    }

    public String getAppProfilePath() {
        return appProfilePath;
    }

    public String getFullFileName() {
        return this.appProfilePath + File.separator + this.filename;
    }

    public void storeOptions() throws Exception {
        File file = new File(this.getFullFileName());
        Properties p = new Properties();
        // restore the frame from 'full screen' first!
        //f.setExtendedState(Frame.NORMAL);
        Rectangle r = this.frame.getBounds();
        int x = (int) r.getX();
        int y = (int) r.getY();
        int w = (int) r.getWidth();
        int h = (int) r.getHeight();

        p.setProperty("x", "" + x);
        p.setProperty("y", "" + y);
        p.setProperty("w", "" + w);
        p.setProperty("h", "" + h);

        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        p.store(br, "Properties of the user frame");
    }

    /**
     * Restore location & size of UI
     *
     * @throws java.io.IOException
     */
    public void restoreOptions() throws IOException {
        File file = new File(this.getFullFileName());
        Properties p = new Properties();
        BufferedReader br = new BufferedReader(new FileReader(file));
        p.load(br);

        int x = Integer.parseInt(p.getProperty("x"));
        int y = Integer.parseInt(p.getProperty("y"));
        int w = Integer.parseInt(p.getProperty("w"));
        int h = Integer.parseInt(p.getProperty("h"));

        Rectangle r = new Rectangle(x, y, w, h);

        this.frame.setBounds(r);
    }

}
