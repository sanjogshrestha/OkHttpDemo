package cnblabs.okhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private OkHttpClient client;
    private Button postButton;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        client = new OkHttpClient();
    }

    private void initUI() {
        postButton = (Button)findViewById(R.id.postButton);
        text=(TextView)findViewById(R.id.text);
        postButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.postButton:
                asyncTask();
              break;

            default:
                break;
        }
    }

    private void asyncTask() {
        new AsyncTask<String, Integer, String>(){

            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub
                try {
                    client = new OkHttpClient();
                    Request s =  new Request.Builder()
                            .url("https://raw.github.com/square/okhttp/master/README.md")
                            .build();
                    Response response = client.newCall(s).execute();
                    return response.toString();

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                System.out.println("REQUEST_GET=" + result);
                text.setText(result);
            }
        }.execute();
    }
}
