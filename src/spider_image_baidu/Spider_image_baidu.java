/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spider_image_baidu;

/**
 *
 * @author Lenovo
 */
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider_image_baidu {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    /*
    * FileWriter(FileName ,boolean append );
        FileWriter("XXXX",true)表示追加输入，否则表示覆盖输入
     */
    static String Gen = "https://image.baidu.com/search/index?tn=baiduimage&utf-8&word=";
    static Scanner cin = new Scanner(System.in);

    public static List<String> Analysis(String buf) {
        List<String> RecList = new ArrayList<>();
        String reg = "\"objURL\":\"[a-zA-z]+://[^\\s]*\"";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(buf);
        int cnt = 0;
        /*
            开头 ：  <-->"objURL":"<-->     
            结尾 ：  <-->"<-->
         */
        int Front_cnt = 10, Back_cnt = 1;
        while (matcher.find()) {
            ++cnt;
            String Now = matcher.group();
            RecList.add(Now.substring(Front_cnt, Now.length() - Back_cnt));
        }
        return RecList;
    }

    public static List<String> OPEN_URL_and_getList(String Target) throws IOException {
        int delta = 1;
        List<String> Image_UrlList = new ArrayList<>();
        for (int pn = 1; true; pn += delta) {
            System.out.print("Stop ? (Y / N)");
            String Status = cin.nextLine();
            if (Status.equals("Y")) {
                break;
            }
            try {
                URL url = new URL(Gen + Target + "&pn=" + String.valueOf(pn));
                BufferedReader Buf_reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String temp;
                delta = 0;
                while ((temp = Buf_reader.readLine()) != null) {
                    List<String> newImage_Url = Analysis(temp);
                    delta += newImage_Url.size();
                    Image_UrlList.addAll(newImage_Url);
                }
                Buf_reader.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(delta);
            if (delta == 0) {
                delta = 1;
            }
        }
        return Image_UrlList;
    }

    public static void main(String[] args) {
        String Target = null;
        try {
            BufferedReader Buf_reader = new BufferedReader(new InputStreamReader(new FileInputStream("Need.txt"), "GBK"));
            /*
                控制台输入中文略麻烦，我就直接用文件读取中文了，这里要将读取的文件化为GBK编码
             */
            Target = Buf_reader.readLine();
            Buf_reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            List<String> Url_message = OPEN_URL_and_getList(Target);
            BufferedWriter Buf_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Target + ".txt")));
            for (String Image_Url : Url_message) {
                Buf_writer.write(Image_Url);
                Buf_writer.write("\r\n");
            }
            Buf_writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
