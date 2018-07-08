package com.xfinity.characterviewer

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
            val intent = Intent(context, CharacterViewActivity::class.java)
            intent.putExtra(context.getString(R.string.extra_name), characters[index].name)
            intent.putExtra(context.getString(R.string.extra_desc), characters[index].description)
            intent.putExtra(context.getString(R.string.extra_image_url), characters[index].imageUrl)
            context.startActivity(intent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.index = position
        holder.tvName.text = character.name
        holder.tvMain.text = character.description
        if (character.imageUrl.isNotEmpty()) {
            Glide.with(context).load(character.imageUrl).into(holder.ivProfile)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = RVAdapterList.ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rv_list, p0, false),
        context,
        characters
    )

    override fun getItemCount(): Int = characters.size

}