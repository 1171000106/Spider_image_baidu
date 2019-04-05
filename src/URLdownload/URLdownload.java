/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URLdownload;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import spider_image_baidu.Spider_image_baidu;

/**
 * download the URL list .
 *
 * @author Lenovo
 */
public class URLdownload {

    private final String needname;
    private final List<String> URL_;

    /**
     * a constructor of URL.
     *
     * @param Cab
     */
    public URLdownload(String Cab) {
        this.needname = Cab;
        URL_ = new ArrayList<>();
        try {
            BufferedReader Buf_reader = new BufferedReader(new InputStreamReader(new FileInputStream(Cab + ".txt"), "GBK"));
            String now;
            while ((now = Buf_reader.readLine()) != null) {
                if (now.equals("")) {
                    continue;
                } else {
                    URL_.add(now);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
