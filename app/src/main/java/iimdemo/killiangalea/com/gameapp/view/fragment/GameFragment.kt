package iimdemo.killiangalea.com.gameapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import iimdemo.killiangalea.com.gameapp.BuildConfig
import iimdemo.killiangalea.com.gameapp.R
import iimdemo.killiangalea.com.gameapp.model.Game
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment(private val gameId: Int) : BackFragment() {

    private var linkCallback: OpenLink? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        fetchGame()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        linkCallback = context as? OpenLink
        if (linkCallback == null)
            throw RuntimeException("OpenLink not implemented by activity")

    }

    override fun onDetach() {
        linkCallback = null
        super.onDetach()
    }

    private fun fetchGame() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this.context)
        val url = BuildConfig.GAME_API_URL + gameId

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val game = Game(
                    response.getInt("id"),
                    response.getString("name"),
                    response.getString("img"),
                    response.getString("description"),
                    response.getString("link")
                )
                initView(game)
            },
            Response.ErrorListener { error ->
                Log.e("test", error.localizedMessage)
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    private fun initView(game: Game) {
        name.text = game.name
        description.text = game.description
        Picasso.get().load(game.imageUrl).into(image)

        button.setOnClickListener {
            linkCallback?.openLink(game.link)
        }
        loaderCallback?.dismissDialog()
    }

    interface OpenLink {
        fun openLink(link: String)
    }

}