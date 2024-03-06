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
                setOopsDialogue()
            }

        }
        erase.setOnClickListener{
            setDeleteDialogue()

        }
        copy.setOnClickListener{
            setCopyDialogue()
        }

    }
    private fun setCameraDialogue() {
        val cameraDialogue = CameraDialogue(this@MainActivity)
        cameraDialogue.startCameraDialogue()
        val handler = Handler()
        val TRANSITION_SCREEN_TIME = 1500
        handler.postDelayed({
            cameraDialogue.dismissDialogue()
        }, TRANSITION_SCREEN_TIME.toLong())
    }
    private fun setOopsDialogue() {
        val oopsDialogue = OopsDialogue(this@MainActivity)
        oopsDialogue.startOopsDialogue()
        val handler = Handler()
        val TRANSITION_SCREEN_TIME = 1500
        handler.postDelayed({
            oopsDialogue.dismissDialogue()
        }, TRANSITION_SCREEN_TIME.toLong())
    }
    private fun setDeleteDialogue() {
        val deleteDialogue = DeleteDialogue(this@MainActivity)
        deleteDialogue.startDeleteDialogue()
        val handler = Handler()
        val TRANSITION_SCREEN_TIME = 1500
        handler.postDelayed({
            deleteDialogue.dismissDialogue()
        }, TRANSITION_SCREEN_TIME.toLong())
    }
    private fun setCopyDialogue() {
        val copyDialogue = CopyDialogue(this@MainActivity)
        copyDialogue.startCopyDialogue()
        val handler = Handler()
        val TRANSITION_SCREEN_TIME = 1500
        handler.postDelayed({
            copyDialogue.dismissDialogue()
        }, TRANSITION_SCREEN_TIME.toLong())
    }

}