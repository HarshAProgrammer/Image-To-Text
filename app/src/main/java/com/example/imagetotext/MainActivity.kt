package com.example.imagetotext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class MainActivity : AppCompatActivity() {
    lateinit var result: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val camera = findViewById<ImageView>(R.id.ivCamera)
        val erase = findViewById<ImageView>(R.id.ivEraser)
        val copy = findViewById<ImageView>(R.id.ivCopy)
        result = findViewById<EditText>(R.id.tvResult)



        camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                setCameraDialogue()
                startActivityForResult(intent, 123)
            } else {
                setOopsDialogue()
            }

        }
        erase.setOnClickListener {
            setDeleteDialogue()
            result?.setText("")

        }
        copy.setOnClickListener {
            setCopyDialogue()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", result?.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show()

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap
            if(bitmap != null){

            detectTextUsingML(bitmap)
            }
        }
    }

    private fun detectTextUsingML(bitmap: Bitmap) {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromBitmap(bitmap, 0)

        val resultText = recognizer.process(image).addOnSuccessListener { visionText ->
            result?.setText(visionText.text.toString())

        }.addOnFailureListener { e ->
            Toast.makeText(this, "Oops something went Wrong", Toast.LENGTH_SHORT).show()

        }

    }

}