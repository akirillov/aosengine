package ru.agiledev.ysearch;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Anton Kirillov
 * Date: 6/28/11
 */
public class YSearch {

    private String user;
    private String key;

    public YSearch(){};

    public YSearch(String user, String key){
        this.user = user;
        this.key = key;
    }

    public String getSearchResults(String query) throws IOException {

        //TODO: check for user and key nullness

        URL url = new URL("http://xmlsearch.yandex.ru/xmlsearch?user="+user+"&key="+key);

        URLConnection con = url.openConnection();

        // specify that we will send output and accept input
        con.setDoInput(true);
        con.setDoOutput(true);

        //TODO: возможно, это скоро не будет прокатывать и надо будет подставить в УРЛ
        con.addRequestProperty("user",user);
        con.addRequestProperty("key", key);

        con.setConnectTimeout( 20000 );  // long timeout, but not infinite
        con.setReadTimeout( 20000 );

        con.setUseCaches (false);
        con.setDefaultUseCaches (false);

        // tell the web server what we are sending
        con.setRequestProperty ("Content-Type", "text/xml");

        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());

        //TODO: ! make settings manageable
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<request>" +
                "<query>"+query+"</query>" +
                "<groupings>" +
                "<groupby attr=\"d\" mode=\"deep\" groups-on-page=\"10\"  docs-in-group=\"1\" />" +
                "</groupings>" +
                "</request>");

        writer.flush();
        writer.close();

        // reading the response
        InputStreamReader reader = new InputStreamReader( con.getInputStream() );

        StringBuilder buf = new StringBuilder();
        char[] cbuf = new char[ 2048 ];
        int num;

        while ( -1 != (num=reader.read( cbuf )))
        {
            buf.append( cbuf, 0, num );
        }

        return buf.toString();
    }

    public static void main(String ... args) throws IOException {
       YSearch y = new YSearch("a-kirillov-a-search-in", "03.75627595:9689e3ecc186de751d138bc36a5a0069");
        System.out.println(y.getSearchResults("жопа"));
    }


}
