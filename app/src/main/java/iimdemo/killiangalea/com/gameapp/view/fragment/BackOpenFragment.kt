package iimdemo.killiangalea.com.gameapp.view.fragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

open class BackOpenFragment : LoaderOpenFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDetach() {
        super.onDetach()
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }
}