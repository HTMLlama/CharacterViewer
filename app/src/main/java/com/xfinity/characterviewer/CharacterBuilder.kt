package com.xfinity.characterviewer

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object CharacterBuilder {

    fun buildCharacters(jsonBody: String): ArrayList<VideoCharacter> {
        val characters = ArrayList<VideoCharacter>()
        if (!jsonBody.isBlank()) {
            val jsonObject = JSONObject(jsonBody)
            val jsonArray = jsonObject.getJSONArray("RelatedTopics")

            var i = 0
            while (i < jsonArray.length()) {
                val jsonCharacter = jsonArray.getJSONObject(i)
                val info = jsonCharacter.getString("Text").split(" - ")
                val icon = jsonCharacter.getJSONObject("Icon")
                val name = info[0]
                val desc = if (info.size > 1) info[1] else ""
                var imageUrl = ""

                if (!icon.isNull("URL")) {
                    imageUrl = icon.getString("URL")
                }

                characters.add(VideoCharacter(name, desc, imageUrl))
                i++
            }
        }

        return characters
    }

    fun callCharacterApi(context: Context, callback: (result: String) -> Any) {
        if (isNetworkAvailable(context)) {
            AsyncTask.execute {
                val response = OkHttpClient().newCall(
                        Request.Builder().url(BuildConfig.api_url).build()
                ).execute()
                response.body()?.string().let {
                    it?.let { body -> callback(body) }
                }
            }
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetworkInfo.isConnected
    }

}