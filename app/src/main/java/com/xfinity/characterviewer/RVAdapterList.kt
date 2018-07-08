package com.xfinity.characterviewer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

internal class RVAdapterList(private val context: Context, private val characters: ArrayList<VideoCharacter>):
        RecyclerView.Adapter<RVAdapterList.ViewHolder>() {

    internal class ViewHolder(view: View, context: Context, characters: ArrayList<VideoCharacter>): RecyclerView.ViewHolder(view) {
        val ivProfile = view.findViewById<View>(R.id.ivRvListMain) as ImageView
        val tvName = view.findViewById<View>(R.id.tvRvListName) as TextView
        val tvMain = view.findViewById<View>(R.id.tvRvListMain) as TextView
        var index = 0

        init {
            view.setOnClickListener { viewClicked(context, characters) }
        }

        private fun viewClicked(context: Context, characters: ArrayList<VideoCharacter>) {
            Toast.makeText(context, "Clicked the List view", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.index = position
        holder.tvName.text = character.name
        holder.tvMain.text = character.description
        Glide.with(context).load(character.imageUrl).into(holder.ivProfile)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = RVAdapterList.ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rv_list, p0, false),
        context,
        characters
    )

    override fun getItemCount(): Int = characters.size

}