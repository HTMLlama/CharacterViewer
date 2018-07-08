package com.xfinity.characterviewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val GRID_SPAN = 3
    private val characters = ArrayList<VideoCharacter>()

    private var isMultiGridLayout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.view_swap_toolbar))

        // ToDo:: Remove debugging list
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())
        characters.add(VideoCharacter())

        buildRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        recyclerViewSwap()
        return super.onOptionsItemSelected(item)
    }

    private fun recyclerViewSwap() {
        if (!isMultiGridLayout) {
            rvMain.layoutManager = GridLayoutManager(this, GRID_SPAN)
            isMultiGridLayout = true
        } else {
            rvMain.layoutManager = GridLayoutManager(this, 1)
            isMultiGridLayout = false
        }
    }

    private fun buildRecyclerView() {
        rvMain.setHasFixedSize(true)
        rvMain.layoutManager = GridLayoutManager(this, 1)
        rvMain.adapter = RVAdapterList(this, characters)
    }
}
