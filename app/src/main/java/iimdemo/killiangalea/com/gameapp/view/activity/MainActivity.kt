package iimdemo.killiangalea.com.gameapp.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import iimdemo.killiangalea.com.gameapp.R
import iimdemo.killiangalea.com.gameapp.util.SharedPreferencesUtil
import iimdemo.killiangalea.com.gameapp.view.dialog.LoadingDialog
import iimdemo.killiangalea.com.gameapp.view.fragment.LoaderManager
import iimdemo.killiangalea.com.gameapp.view.fragment.GameFragment
import iimdemo.killiangalea.com.gameapp.view.fragment.GameListFragment

class MainActivity : AppCompatActivity(), GameListFragment.OnGameSelected, GameFragment.OpenLink,
    LoaderManager {

    lateinit var loadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingDialog = LoadingDialog(this)
        loadingDialog.setOnCancelListener { this.finish() }

        //Init sharedPreferences
        SharedPreferencesUtil.initSharedPreferences(this)

        changeFragment(GameListFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    private fun changeFragment(frag: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, frag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDialog() {
        loadingDialog.show()
    }

    override fun dismissDialog() {
        loadingDialog.dismiss()
    }

    override fun openLink(link: String) {
        SharedPreferencesUtil.getCounter()?.let{
            if (it % 2 == 0) {
                val intent = Intent(this, WebActivity::class.java)
                intent.putExtra("link", link)
                startActivity(intent)
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(browserIntent)
            }
            SharedPreferencesUtil.setCounter(it + 1)
        }
    }

    override fun onGameSelected(id: Int) {
        changeFragment(GameFragment(id))
    }

}
