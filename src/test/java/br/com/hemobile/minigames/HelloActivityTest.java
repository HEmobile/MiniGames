package br.com.hemobile.minigames;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
public class HelloActivityTest {

    @Test
    public void shouldDisplayHello() throws Exception {
        HelloActivity_ activity =  new HelloActivity_();
        activity.onCreate(null);
        String hello = activity.getResources().getString(R.string.hello);
        TextView helloView = (TextView) activity.findViewById(R.id.hello_view);
        assertEquals(helloView.getText(), hello);
    }
}