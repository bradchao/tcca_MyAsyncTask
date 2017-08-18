package tw.brad.android.games.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
    }
    public void test1(View view){
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("Brad", "TCCA", "OK", "a", "b", "c");

        Log.i("brad", "OK");
//        for (int i=0; i<10; i++){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
    public void test2(View view){
        if (myAsyncTask!= null && !myAsyncTask.isCancelled()){
            myAsyncTask.cancel(true);
        }
    }

    private class MyAsyncTask extends AsyncTask<String,Integer,String>{
        private int i;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("brad", "onPreExecute");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("brad", "onPostExecute:" + result);
            tv.setText(result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            StringBuffer sb = new StringBuffer();
            for (Integer i : values){
                sb.append(i + "  \n");
            }
            tv.setText(sb);

        }

        @Override
        protected void onCancelled(String error) {
            super.onCancelled(error);
            Log.i("brad", "onCancelled(Void aVoid):" + error);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("brad", "onCancelled");
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "OK";
            for (String name : params) {
                i++;
                Log.i("brad", "doInBackground:" + name);
                publishProgress(i, i + 10, i+ 100);
                if (isCancelled()){
                    result = "NOT OK";
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

}
