package iimdemo.killiangalea.com.gameapp.view.fragment

import android.content.Context
import androidx.fragment.app.Fragment

open class LoaderOpenFragment : Fragment() {

    open var loaderCallback: LoaderManager? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loaderCallback = context as? LoaderManager
        if (loaderCallback == null)
            throw RuntimeException("ManageDialog not implemented by activity")
    }

    override fun onDetach() {
        super.onDetach()
        loaderCallback = null

    }

}