//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package br.com.hemobile.minigames;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import br.com.hemobile.minigames.R.id;
import br.com.hemobile.minigames.R.layout;

public final class MenuActivity_
    extends MenuActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.main);
    }

    private void init_(Bundle savedInstanceState) {
    }

    private void afterSetContentView_() {
        helloView = ((TextView) findViewById(id.hello_view));
        {
            View view = findViewById(id.btnRanking);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MenuActivity_.this.btnRanking();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.btnLevel2);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MenuActivity_.this.btnLevel2();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.btnLevel1);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MenuActivity_.this.btnLevel1();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.btnOptions);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MenuActivity_.this.btnOptions();
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.btnLevel3);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        MenuActivity_.this.btnLevel3();
                    }

                }
                );
            }
        }
        init();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    public static MenuActivity_.IntentBuilder_ intent(Context context) {
        return new MenuActivity_.IntentBuilder_(context);
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, MenuActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public MenuActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (context_ instanceof Activity) {
                ((Activity) context_).startActivityForResult(intent_, requestCode);
            } else {
                context_.startActivity(intent_);
            }
        }

    }

}
