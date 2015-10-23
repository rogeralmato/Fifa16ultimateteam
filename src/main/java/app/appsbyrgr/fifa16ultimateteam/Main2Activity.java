package app.appsbyrgr.fifa16ultimateteam;

import android.support.v7.app.AppCompatActivity;
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
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;

public class Main2Activity extends AppCompatActivity {

    Button button1, backButton,nextButton, allButton, backpageButton, advancedButton;
    EditText playerName, name, editText;
    TextView mTextView;
    WebView webView;

    int contador = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = (Button) findViewById(R.id.buttonSearch);
        backButton = (Button) findViewById(R.id.backbutton);
        playerName = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.text);
        name = (EditText) findViewById(R.id.editText);
        editText = (EditText) findViewById(R.id.editText);
        nextButton = (Button) findViewById(R.id.nextbutton);
        allButton = (Button) findViewById(R.id.buttonallplayers);
        backpageButton = (Button) findViewById(R.id.paganteriorbutton);
        advancedButton = (Button) findViewById(R.id.advancedsearchbutton);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //pb.setVisibility(View.VISIBLE);
                //pb.setProgress(progress);
                //if (progress == 100) {
                //pb.setVisibility(View.GONE);
                //}
            }
        });


        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String newString = name.getText().toString();
                webView.setVisibility(View.VISIBLE);
                button1.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);
                playerName.setVisibility(View.INVISIBLE);
                allButton.setVisibility(View.INVISIBLE);
                advancedButton.setVisibility(View.INVISIBLE);
                Toast.makeText(Main2Activity.this, "Loading Results", Toast.LENGTH_SHORT).show();


                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                String url = "http://www.futhead.com/16/players/?name=" + newString;

                RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,


                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int inici = response.indexOf("<table class=\"table table-striped");
                                int fin = response.indexOf("<div class=\"subnav margin-10 bordered\"");
                                String taularesultats = "<head>\n" +
                                        "\t\t\n" +
                                        "\n" +
                                        "\t\t\n" +
                                        "\t\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\n" +
                                        "\n" +
                                        "\t\t<link rel=\"shortcut icon\" href=\"http://futhead.cursecdn.com/static/img/favicon.png\">\n" +
                                        "\t\t<link rel=\"apple-touch-icon-precomposed\" href=\"http://futhead.cursecdn.com/static/img/apple-touch-icon.png\" />\n" +
                                        "\t\t\n" +
                                        "\t\t<link href='https://fonts.googleapis.com/css?family=Titillium+Web' rel='stylesheet' type='text/css'>\n" +
                                        "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/aa8bc7277373.css\" />\n" +
                                        "\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\n" +
                                        "\t\t\n" +
                                        "\t\t\n" +
                                        "\t</head>" + response.substring(inici, fin);
                                webView.loadDataWithBaseURL(null, taularesultats, "text/html", "utf-8", null);
                                Log.i("HTML -->", response.substring(inici, fin));
                                webView.getSettings().setDisplayZoomControls(false);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);
            }
        });

        final Button backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("");
                webView.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.INVISIBLE);
                playerName.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                allButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
                backpageButton.setVisibility(View.INVISIBLE);
                advancedButton.setVisibility(View.INVISIBLE);

            }
        });

        final Button allplayers = (Button) findViewById(R.id.buttonallplayers);
        allplayers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String newString = name.getText().toString();
                webView.setVisibility(View.VISIBLE);
                button1.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.INVISIBLE);
                backpageButton.setVisibility(View.INVISIBLE);
                playerName.setVisibility(View.INVISIBLE);
                allplayers.setVisibility(View.INVISIBLE);
                advancedButton.setVisibility(View.INVISIBLE);


                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                String url = "http://www.futhead.com/16/players/?page=" + 1;

                RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
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
                                        "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/aa8bc7277373.css\" />\n" + response.substring(inici2, fin2);
                                webView.loadDataWithBaseURL(null, urltotsjugadors, "text/html", "utf-8", null);
                                Log.i("HTML -->", response.substring(inici2, fin2));
                                webView.getSettings().setDisplayZoomControls(false
                                );


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);


            }
        });




        final Button backpageButton = (Button) findViewById(R.id.paganteriorbutton);
        backpageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {


                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if (contador == 0) {
                    backpageButton.setVisibility(View.INVISIBLE);

                }
                if (contador > 0) {
                    contador = contador - 1;

                    String url = "http://www.futhead.com/16/players/?page=" + (1 + contador);

                    RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
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
                                            "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/aa8bc7277373.css\" />\n" + response.substring(inici2, fin2);
                                    webView.loadDataWithBaseURL(null, urltotsjugadors, "text/html", "utf-8", null);
                                    Log.i("HTML -->", response.substring(inici2, fin2));
                                    webView.getSettings().setDisplayZoomControls(false);


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);

                }

            }
        });


        final Button nextButton = (Button) findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                contador = contador + 1;

                backpageButton.setVisibility(View.VISIBLE);


                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);


                String url = "http://www.futhead.com/16/players/?page=" + (1 + contador);

                RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);
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
                                        "\t\t<link rel=\"stylesheet\" href=\"http://futhead.cursecdn.com/static/__CACHE/css/aa8bc7277373.css\" />\n" + response.substring(inici2, fin2);
                                webView.loadDataWithBaseURL(null, urltotsjugadors, "text/html", "utf-8", null);
                                Log.i("HTML -->", response.substring(inici2, fin2));
                                webView.getSettings().setDisplayZoomControls(false);


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);


            }
        });

        final Button advancedButton = (Button) findViewById(R.id.advancedsearchbutton);
        advancedButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this,AdActivity.class));


            }
        });




    }
}
