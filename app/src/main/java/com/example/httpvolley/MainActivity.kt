package com.example.httpvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.*
import com.example.httpvolley.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val URL: String = "https://localhost:44334/api/calllogs" //Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }
//        // RequestQueue initialized para enviar una solicitud simple
//        val queue: RequestQueue = Volley.newRequestQueue(this)

        //jsonObject con los datos a enviar
        val jsonObject = CreateMap("Id", "OpPhone", "ClientPhone", "Type", "00:00:05", "Date")

        //Request: Url, objeto y listeners de respuesta de exito o error
        val request = JsonObjectRequest(Request.Method.POST, URL, jsonObject, Response.Listener { response -> binding.tv1.setText(response.toString()) }, { error -> error.printStackTrace()}) //null es el objeto json a enviar, respuesta y error

        requestQueue.add(request)
    }
    //Método para crear Hash map con los parámetros a enviar
    private fun CreateMap(id: String, opPhone: String, clientPhone: String, type: String,
                          duration: String, date: String): JSONObject {

        val map = HashMap<String, String>()
        map.put("IDAndroid",id)
        map.put("OperatorPhone",opPhone)
        map.put("ClientPhone", clientPhone)
        map.put("Type", type)
        map.put("Duration", duration)
        map.put("Date", date)

        val json = JSONObject(map as Map<*, *>)
        return json
    }
}