package com.example.imagetotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val camera = findViewById<ImageView>(R.id.ivCamera)
        val erase = findViewById<ImageView>(R.id.ivEraser)
        val copy = findViewById<ImageView>(R.id.ivCopy)

        camera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager)!= null){
                setCameraDialogue()


            }else{
                Toast.makeText(this,"Something went Wrong!",Toast.LENGTH_SHORT).show()
            }

        }
        erase.setOnClickListener{

        }
        copy.setOnClickListener{

        }

    }
    private fun setCameraDialogue() {
        val cameraDialogue = CameraDialogue(this@MainActivity)
        cameraDialogue.startExpensiveDialogue()
        val handler = Handler()
        val TRANSITION_SCREEN_TIME = 1500
        handler.postDelayed({
            cameraDialogue.dismissDialogue()
        }, TRANSITION_SCREEN_TIME.toLong())
    }

}