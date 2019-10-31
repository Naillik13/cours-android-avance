package iimdemo.killiangalea.com.gameapp.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import iimdemo.killiangalea.com.gameapp.R

class LoadingDialog(context: Context) : Dialog(context, R.style.Theme_AppCompat) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        setContentView(R.layout.dialog_loader)
    }
}
