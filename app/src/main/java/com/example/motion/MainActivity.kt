package com.example.motion

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scene: MotionLayout = findViewById(R.id.scene)
        if (progress > 0) {
            scene.progress = progress
        }

        scene.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                Log.d(
                    "motion_v",
                    "onTransitionStarted() called with: startId = ${resName(startId)}, " +
                            "endId = ${resName(endId)}"
                )
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                Log.d(
                    "motion_v",
                    "onTransitionCompleted() called with: currentId = ${resName(currentId)}; " +
                            "progress = ${motionLayout.progress}"
                )
                state = currentId
                progress = motionLayout.progress
            }
        })
    }

    fun resName(id: Int): String = "R.id.${resources.getResourceEntryName(id)}"

    companion object {
        var state = -1
        var progress = 0f
    }
}
