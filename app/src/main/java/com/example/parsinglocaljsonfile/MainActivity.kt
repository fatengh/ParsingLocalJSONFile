package com.example.parsinglocaljsonfile
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class  MainActivity : AppCompatActivity() {
    val images=ArrayList<String>()
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv)
        try {
            val inputStream:InputStream=assets.open("data.json")
            var jsonData = inputStream.bufferedReader().use { it.readText() }
            val jArray=JSONArray(jsonData)

            for (image in 0..jArray.length()-1){
                var ob=jArray.getJSONObject(image)
                Log.d("imgURL",ob.getString("url"))
                val url=ob.getString("url")
                images.add(url)
            }

            Log.d("imgURL",images.toString())
            rv.adapter=MyAdap(images)
            rv.layoutManager= LinearLayoutManager(this)

        }catch (e:IOException){
            print(e)
        }    }

}