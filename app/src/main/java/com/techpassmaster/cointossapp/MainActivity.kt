package com.techpassmaster.cointossapp

import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.italic
import com.techpassmaster.cointossapp.databinding.ActivityMainBinding
import java.util.*


/**
 * Created by Techpass Master on 7-April-21.
 * Website - https://techpassmaster.com/
 * Email id - hello@techpassmaster.com
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rotateAnimation: RotateAnimation
    private lateinit var bounceAnimation: Animation
    private lateinit var animationSet: AnimationSet
    private lateinit var random: Random
    private var slideCoin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
        random = Random()

        binding.imgCoin.setOnClickListener {

            slideCoin = random.nextInt(2)

            if (slideCoin == 0) {

                binding.imgCoin.setImageResource(R.drawable.heads)

                val spannableStringBuilder = SpannableStringBuilder()
                    .append("Toss Result: ")
                    .bold { italic {append("Heads")  } }

                binding.tvTossResult.text = (spannableStringBuilder)

            } else if (slideCoin == 1) {

                binding.imgCoin.setImageResource(R.drawable.tails)

                val spannableStringBuilder = SpannableStringBuilder()
                    .append("Toss Result: ")
                    .bold { italic {append("Tails")  } }

                binding.tvTossResult.text = (spannableStringBuilder)
            }

            // set rotation animation
            rotateAnimation = RotateAnimation(
                0f, 360f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
            )
            rotateAnimation.duration = 60
            rotateAnimation.repeatCount = 12

            // set start and end animation listener
            rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    Toast.makeText(this@MainActivity, "Rotation started...", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAnimationEnd(animation: Animation) {
                    Toast.makeText(this@MainActivity, "Rotation ended...", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })

            // set multiple animationSet on coin
            animationSet = AnimationSet(true)
            animationSet.addAnimation(rotateAnimation)
            animationSet.addAnimation(bounceAnimation)
            binding.imgCoin.startAnimation(animationSet)
        }

    }
}
