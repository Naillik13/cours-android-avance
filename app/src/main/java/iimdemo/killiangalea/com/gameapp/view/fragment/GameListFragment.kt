package iimdemo.killiangalea.com.gameapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import iimdemo.killiangalea.com.gameapp.BuildConfig
import iimdemo.killiangalea.com.gameapp.R
import iimdemo.killiangalea.com.gameapp.model.GamePreview
import iimdemo.killiangalea.com.gameapp.view.adapter.GameAdapter
import kotlinx.android.synthetic.main.fragment_gamelist.*
import org.json.JSONObject

class GameListFragment : DialogFragment() {

    private var gameList: ArrayList<GamePreview> = ArrayList()
    private var gameCallback : OnGameSelected? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gamelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshLayout.setOnRefreshListener {
            fetchGames()
        }

        fetchGames()

        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = GameAdapter(gameList){ id: Int ->
            dialogCallback?.showDialog()
            gameCallback?.onGameSelected(id)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        gameCallback = context as? OnGameSelected
        if (gameCallback == null)
            throw RuntimeException("OnGameSelected not implemented by activity")

    }

    override fun onDetach() {
        gameCallback = null
        super.onDetach()
    }

    private fun fetchGames() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this.context)

        //Reset game list
        gameList.clear()
        recyclerView.adapter?.notifyDataSetChanged()

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, BuildConfig.GAME_API_URL, null,
            Response.Listener { response ->
                //Add game to list
                for (i in 0 until response.length()) {
                    val jsonInner: JSONObject = response.getJSONObject(i)
                    gameList.add(
                        GamePreview(
                            jsonInner.getInt("id"),
                            jsonInner.getString("name"),
                            jsonInner.getString("img")
                        )
                    )
                }

                //Stop refreshing and update adapter
                refreshLayout.isRefreshing = false
                recyclerView.adapter?.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Log.e("test", error.localizedMessage)
                refreshLayout.isRefreshing = false
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    interface OnGameSelected {
        fun onGameSelected(id: Int)
    }

}