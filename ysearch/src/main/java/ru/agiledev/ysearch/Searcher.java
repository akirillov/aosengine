package ru.agiledev.ysearch;

import ru.agiledev.ysearch.exception.CredentialsException;
import ru.agiledev.ysearch.exception.URLException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class Searcher {
    private URL url;


    private String user;
    private String key;

    public Searcher(){}

    //todo: почитать Яндекс.XML мануал насчет страниц
    public String search(Query query, int pageNum) throws CredentialsException, URLException, IOException {
        if("".equals(user)||"".equals(key)) throw new CredentialsException();
        if(url == null) throw new URLException();

        URLConnection con = url.openConnection();

        // specify that we will send output and accept input
        con.setDoInput(true);
        con.setDoOutput(true);

        //TODO: возможно, это скоро не будет прокатывать и надо будет подставить в УРЛ





        // todo: (проверить эту ботву!)
        con.addRequestProperty("user",user);
        con.addRequestProperty("key", key);

        con.setConnectTimeout( 20000 );  // long timeout, but not infinite
        con.setReadTimeout( 20000 );

        con.setUseCaches (false);
        con.setDefaultUseCaches (false);

        // tell the web server what we are sending
        con.setRequestProperty ("Content-Type", "text/xml");

        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(query.toString());
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

        String result = buf.toString();

        //todo: analyze result string and AuthenticationException if wrong
        return result;
    }

    public void init(String user, String key){
        this.user = user;
        this.key = key;
    }

    public void init(String user, String key, URL url){
        this.user = user;
        this.key = key;
        this.url = url;
    }

    //getters and setters
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
