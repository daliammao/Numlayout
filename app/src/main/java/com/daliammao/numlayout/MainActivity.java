package com.daliammao.numlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daliammao.numlayoutlib.NumLayout;
import com.daliammao.numlayoutlib.config.ViewConfig;
import com.daliammao.numlayoutlib.listener.OnIntegerErrorListener;

public class MainActivity extends AppCompatActivity {

    private NumLayout numLayout;
    private RelativeLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        numLayout = (NumLayout) findViewById(R.id.numlayout);

        numLayout.setConfig(NumFactory.getDefaultSingel(this, 0, 2, 0));

        numLayout.setOnItemClickListener(new NumLayout.OnItemClickListener() {
            @Override
            public void onItemClick(NumLayout parent, View view, ViewConfig.ViewType type, String tag) {
                if (tag.equals("Les")) {
                    parent.lesNumWithTag("Text");
                } else if (tag.equals("Add")) {
                    parent.addNumWithTag("Text");
                }
            }
        });

        numLayout.setOnIntegerErrorListenerWithTag("Text", new OnIntegerErrorListener() {
            @Override
            public Integer onIntegerError(ErrorType errorType, int newData, int min, int max, int tolerance) {
                switch (errorType) {
                    case LT_MIN:
                        Toast.makeText(MainActivity.this,"LT_MIN",Toast.LENGTH_SHORT).show();
                        break;
                    case GT_MAX:
                        Toast.makeText(MainActivity.this,"GT_MAX",Toast.LENGTH_SHORT).show();
                        break;
                    case LT_ZERO:
                        Toast.makeText(MainActivity.this,"LT_ZERO",Toast.LENGTH_SHORT).show();
                        break;
                    case NOT_SATISFY_TOLERANCE:
                        Toast.makeText(MainActivity.this,"NOT_SATISFY_TOLERANCE",Toast.LENGTH_SHORT).show();
                        break;
                }
                return null;
            }
        });

        numLayout.setOnInputListener(new NumLayout.OnInputListener() {
            @Override
            public void onInputComplete(NumLayout parent, String tag, String text, float min, float max, float tolerance) {
                System.out.println(text);
            }
        });

        content = (RelativeLayout) findViewById(R.id.content);
        content.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(bottom < oldBottom){

                }else if(bottom > oldBottom){
                    numLayout.confirmEditDataWithTag("Text");
                }
            }
        });
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
