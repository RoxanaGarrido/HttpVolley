package com.example.httpvolley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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

        // RequestQueue initialized
        val queue: RequestQueue = Volley.newRequestQueue(this)

        //jsonObject con los datos a enviar
        val jsonObject = CreateMap("Id", "OpPhone", "ClientPhone", "Type", "00:00:05", "Date")

        //Request: Url, objeto y listeners de respuesta de exito o error
        val request = JsonObjectRequest(Request.Method.POST, URL, jsonObject, Response.Listener { response -> Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show() }, { error -> error.printStackTrace()}) //null es el objeto json a enviar, respuesta y error

        queue.add(request)
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