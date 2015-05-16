package com.angelhack.vidaloca;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import com.angelhack.vidaloca.Init.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;

public class MainActivity extends ActionBarActivity {

    Button up;
    String api = Init.api_key;
    String video = Init.url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Client1 client1 = new Client1();
        client1.run();
    }

    class Client1 {
        public void run() {

            org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
            video += "?apikey="+api+"&text=genome";
            HttpGet httpGet = new HttpGet(video);
            CloseableHttpResponse response = null;
            try {
                response = (CloseableHttpResponse) httpClient.execute(httpGet);
                org.apache.http.StatusLine statusLine = response.getStatusLine();
                Toast.makeText(MainActivity.this, "StatusLine: " + statusLine, Toast.LENGTH_SHORT).show();
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    long len = entity.getContentLength();
                    if (len != -1 && len < 2048) {
                        String body = EntityUtils.toString(entity);
                        Toast.makeText(MainActivity.this, body, Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
                }catch (ClientProtocolException cpe){
                cpe.printStackTrace();
            } catch (IOException ioe){
                ioe.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
