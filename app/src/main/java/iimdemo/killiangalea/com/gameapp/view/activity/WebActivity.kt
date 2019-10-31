package iimdemo.killiangalea.com.gameapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import iimdemo.killiangalea.com.gameapp.R
import kotlinx.android.synthetic.main.activity_web.*
import android.webkit.WebViewClient

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        webView.webViewClient = WebViewClient()

        intent.getStringExtra("link")?.let {
            webView.loadUrl(it)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }
}
