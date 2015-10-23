package app.appsbyrgr.fifa16ultimateteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ImageButton nextButton, previousButton, searchButton;
    InterstitialAd interstitialAd;

    int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interstitialAd = new InterstitialAd(this);

        interstitialAd.setAdUnitId("ca-app-pub-4551160061410779/8238746443");
        AdRequest adRequest = new AdRequest.Builder().build();

        interstitialAd.loadAd(adRequest);

        AdView mAdView = (AdView) findViewById(R.id.adView1);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setFocusable(false);
        webView.setClickable(false);
        webView.setEnabled(false);

        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);

        previousButton = (ImageButton) findViewById(R.id.previousButton);


        InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        String url = "http://www.futhead.com/16/players/?page=" + 1;

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int inici2 = response.indexOf("<table class=\"table table-striped");
                        int fin2 = response.indexOf(" </a>\n" +
                                "                    </td>\n" +
                                "                    \n" +
                                "                    \n" +
                                "                </tr>\n" +
                                "                \n" +
                                "            </tbody>\n" +
                                "        </table>");
                        String urltotsjugadors = "\n" +
                                "\n" +
                                "<!DOCTYPE html>\n" +
                                "\n" +
                                "\t\n" +
                                "\t\t\n" +
                                "\t\t\n" +
                                "\n" +
                                "\t\t\n" +
                                "\t\t\n" +
                                "\t\t\n" +
                                "<link rel=\"canonical\" href=\"/16/players/\"/>\n" +
                                "\n" +
                                "\t\t<link rel=\"shortcut icon\" href=\"http://futhead.cursecdn.com/static/img/favicon.png\">\n" +
                                "\t\t<link rel=\"apple-touch-icon-precomposed\" href=\"http://futhead.cursecdn.com/static/img/apple-touch-icon.png\" />\n" +
                                "\t\t\n" +
                                "\t\t<link href='https://fonts.googleapis.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'>\n" +
                                "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/4c7c10ba9745.css\" />\n" + response.substring(inici2, fin2);
                        webView.loadDataWithBaseURL(null, urltotsjugadors, "text/html", "utf-8", null);
                        Log.i("HTML -->", response.substring(inici2, fin2));
                        webView.getSettings().setDisplayZoomControls(false
                        );


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

        final ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                contador = contador + 1;

                previousButton.setVisibility(View.VISIBLE);
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();

                }


                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


                String url = "http://www.futhead.com/16/players/?page=" + (1 + contador);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int inici2 = response.indexOf("<table class=\"table table-striped");
                                int fin2 = response.indexOf(" </a>\n" +
                                        "                    </td>\n" +
                                        "                    \n" +
                                        "                    \n" +
                                        "                </tr>\n" +
                                        "                \n" +
                                        "            </tbody>\n" +
                                        "        </table>");
                                String urltotsjugadors = "\n" +
                                        "\n" +
                                        "<!DOCTYPE html>\n" +
                                        "\n" +
                                        "\t\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "<link rel=\"canonical\" href=\"/16/players/\"/>\n" +
                                        "\n" +
                                        "\t\t<link rel=\"shortcut icon\" href=\"http://futhead.cursecdn.com/static/img/favicon.png\">\n" +
                                        "\t\t<link rel=\"apple-touch-icon-precomposed\" href=\"http://futhead.cursecdn.com/static/img/apple-touch-icon.png\" />\n" +
                                        "\t\t\n" +
                                        "\t\t<link href='https://fonts.googleapis.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'>\n" +
                                        "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/4c7c10ba9745.css\" />\n" + response.substring(inici2, fin2);
                                webView.loadDataWithBaseURL(null, urltotsjugadors, "text/html", "utf-8", null);
                                Log.i("HTML -->", response.substring(inici2, fin2));
                                webView.getSettings().setDisplayZoomControls(false);


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);

                Context context = getApplicationContext();
                CharSequence text = "Loading page " + (contador + 1);
                int duration = Toast.LENGTH_LONG;
                final Toast tost = Toast.makeText(context, text, duration);
                tost.show();
                AdView mAdView = (AdView) findViewById(R.id.adView1);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);


            }
        });

        final ImageButton backpageButton = (ImageButton) findViewById(R.id.previousButton);
        backpageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {


                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                if (contador == 1) {
                    backpageButton.setVisibility(View.INVISIBLE);

                }
                if (contador > 0) {
                    contador = contador - 1;

                    String url = "http://www.futhead.com/16/players/?page=" + (1 + contador);

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    int inici2 = response.indexOf("<table class=\"table table-striped");
                                    int fin2 = response.indexOf(" </a>\n" +
                                            "                    </td>\n" +
                                            "                    \n" +
                                            "                    \n" +
                                            "                </tr>\n" +
                                            "                \n" +
                                            "            </tbody>\n" +
                                            "        </table>");
                                    String urltotsjugadors = "\n" +
                                            "\n" +
                                            "<!DOCTYPE html>\n" +
                                            "\n" +
                                            "\t\n" +
                                            "\t\t\n" +
                                            "\t\t\n" +
                                            "\n" +
                                            "\t\t\n" +
                                            "\t\t\n" +
                                            "\t\t\n" +
                                            "<link rel=\"canonical\" href=\"/16/players/\"/>\n" +
                                            "\n" +
                                            "\t\t<link rel=\"shortcut icon\" href=\"http://futhead.cursecdn.com/static/img/favicon.png\">\n" +
                                            "\t\t<link rel=\"apple-touch-icon-precomposed\" href=\"http://futhead.cursecdn.com/static/img/apple-touch-icon.png\" />\n" +
                                            "\t\t\n" +
                                            "\t\t<link href='https://fonts.googleapis.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'>\n" +
                                            "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/4c7c10ba9745.css\" />\n" + response.substring(inici2, fin2);
                                    webView.loadDataWithBaseURL(null, urltotsjugadors, "text/html", "utf-8", null);
                                    Log.i("HTML -->", response.substring(inici2, fin2));
                                    webView.getSettings().setDisplayZoomControls(false);


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);

                }


                Context context = getApplicationContext();
                CharSequence text = "Loading page " + (contador + 1);
                int duration = Toast.LENGTH_LONG;
                final Toast tost = Toast.makeText(context, text, duration);
                tost.show();

            }
        });
        final ImageButton searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));



            }
        });



    }


}
