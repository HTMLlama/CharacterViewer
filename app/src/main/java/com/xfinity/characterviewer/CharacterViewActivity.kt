package com.xfinity.characterviewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_character_view.*

class CharacterViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_view)

        val imageUrl = intent.getStringExtra(getString(R.string.extra_image_url))
        tvCharacterViewName.text = intent.getStringExtra(getString(R.string.extra_name))
        tvCharacterViewMain.text = intent.getStringExtra(getString(R.string.extra_desc))
        if (imageUrl.isNotBlank()) {
            Glide.with(this).load(imageUrl).into(ivCharacterViewMain)
        }
    }
}
