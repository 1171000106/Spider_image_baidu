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
    public static int Analysis(String buf){
        String reg = "\"objURL\":\"[a-zA-z]+://[^\\s]*\"";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(buf);
        int cnt=0;
        while(matcher.find()){
            ++cnt;
            System.out.println(matcher.group());
        }
        return cnt;
    }
    public static void OPEN_URL(String Target) throws IOException{
        int delta = 1;
        for(int pn = 1 ; true ; pn += delta){
            System.out.print("Stop ? (Y / N)");
            String Status = cin.nextLine();
            if(Status.equals("Y"))break;
            try {
                URL url = new URL(Gen+Target+"&pn="+String.valueOf(pn)); 
                InputStream in_url = url.openStream();
                InputStreamReader in_reader = new InputStreamReader(in_url);
                BufferedReader Buf_reader = new BufferedReader(in_reader);
                String temp ;
                delta = 0;
                while((temp = Buf_reader.readLine())!=null){
                    delta += Analysis(temp);
                }
                Buf_reader.close();
                in_reader.close();
                in_url.close();
                
            } catch (MalformedURLException ex ) {
                Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
            } catch ( IOException ex ){
                Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(delta);
            if(delta == 0)delta = 1;
        }
    }
    public static void main(String[] args){
        String Target = null;
        try {
            BufferedReader Buf_reader = new BufferedReader(new InputStreamReader(new FileInputStream("Need.txt"),"GBK"));
            /*
                控制台输入中文略麻烦，我就直接用文件读取中文了，这里要将读取的文件化为GBK编码
            */
            Target = Buf_reader.readLine();
            Buf_reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            OPEN_URL(Target);
        } catch (IOException ex) {
            Logger.getLogger(Spider_image_baidu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
