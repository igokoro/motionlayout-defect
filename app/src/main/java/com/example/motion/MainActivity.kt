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
        val stage: MotionLayout = findViewById(R.id.header)
        if (progress > 0) {
            stage.progress = progress

            Log.d(
                "motion_v",
                "onCreate: constraint set start == end ${stage.getConstraintSet(R.id.start) == stage.getConstraintSet(
                    R.id.end
                )}"
            )
        }

        stage.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                Log.d(
                    "motion_v",
                    "onTransitionStarted() called with: startId = $startId, endId = $endId"
                )
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                Log.d(
                    "motion_v",
                    "onTransitionCompleted() called with: currentId = $currentId; progress = ${motionLayout.progress}"
                )
                state = currentId
                progress = motionLayout.progress
            }
        })
    }

    companion object {
        var state = -1
        var progress = 0f
    }
}
