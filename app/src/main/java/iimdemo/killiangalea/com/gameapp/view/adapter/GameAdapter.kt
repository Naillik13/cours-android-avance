package iimdemo.killiangalea.com.gameapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iimdemo.killiangalea.com.gameapp.R
import iimdemo.killiangalea.com.gameapp.model.GamePreview
import kotlinx.android.synthetic.main.item_gamepreview.view.*

class GameAdapter(private val gameList: ArrayList<GamePreview>, val listener: (Int) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_gamepreview, parent, false)
        return GameViewHolder(viewHolder, listener)
    }

    override fun getItemCount(): Int = gameList.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) = holder.bind(gameList[position])

    class GameViewHolder(val gameView: View, val listener: (Int) -> Unit) : RecyclerView.ViewHolder(gameView) {

        fun bind(game: GamePreview) {
            gameView.textView.text = game.name
            Picasso.get().load(game.imageUrl).into(gameView.imageView)
            gameView.item.setOnClickListener {
                listener(game.id)
            }
        }

    }
}