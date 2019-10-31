package iimdemo.killiangalea.com.gameapp.view.fragment

import android.content.Context
import androidx.fragment.app.Fragment

open class DialogFragment : Fragment() {

    open var dialogCallback: DialogManager? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dialogCallback = context as? DialogManager
        if (dialogCallback == null)
            throw RuntimeException("ManageDialog not implemented by activity")
    }

    override fun onDetach() {
        super.onDetach()
        dialogCallback = null

    }

}