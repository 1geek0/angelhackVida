package com.angelhack.vidaloca;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    class Document
    {
        String content;
        int offset;
    }



    private String getJobID(String data) {

        String x ="";
        try {
            JSONObject result = new JSONObject(data);
            x=result.getString("jobID");
        }
        catch(Exception e)
        {

        }
        return  x;
    }
        private ArrayList getDocumentObjects(String data) {

        ArrayList<Document> documents = new ArrayList<>();

        try {
            JSONObject result = new JSONObject(data);

            JSONArray jsonArray = result.getJSONArray("document");

            for(int a=0;a<jsonArray.length();a++)
            {

                Document d = new Document();

                d.offset = jsonArray.getJSONObject(a).getInt("offset");
                d.content = jsonArray.getJSONObject(a).getString("content");

                documents.add(d);

            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return documents;
    }
    AsyncHttpClient asyncHttpClient;
    Button up;
    String api = Init.api_key;
    String video = Init.url;
    TextView responseView;
    String responseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseView = (TextView) findViewById(R.id.textView);
        asyncHttpClient = new AsyncHttpClient();
        video += "?apikey="+api+"&text=genome";
        asyncHttpClient.get(video, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String byteStr = new String(responseBody);
//                String id = getJobID(r);
                responseView.setText(byteStr);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
        /*Client1 client1 = new Client1();
        client1.run();*/
    }
    /*class Client1 {
        String api = Init.api_key;
        String video = Init.url;
        public void run() {


            org.apache. client = HttpClientBuilder.create().build();
            video += "?apikey="+api+"&text=genome";
            HttpGet httpGet = new HttpGet(video);
            CloseableHttpResponse response = null;
            try {
                response = (CloseableHttpResponse) builder.execute(httpGet);
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
    }*/

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
