package com.example.homework_4

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.homework_4.databinding.FragmentCustomBinding

class CustomFragment : Fragment() {
    private var _binding: FragmentCustomBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCustomBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {

            // Setting random colors with a given amount
//            val colorsQuantity = 5
//            paletteCustomView.setRandomColors(colorsQuantity)

            // Setting a custom list of colors
//            val rainbow = listOf(
//                Color.parseColor("#FF0000"),
//                Color.parseColor("#FF7F00"),
//                Color.parseColor("#FFFF00"),
//                Color.parseColor("#00FF00"),
//                Color.parseColor("#0000FF"),
//                Color.parseColor("#2E2B5F"),
//                Color.parseColor("#8B00FF"),
//
//            )
//            paletteCustomView.setColors(rainbow)

            // Changing color of the icon
            paletteCustomView.setOnClickListener {
                val imageColor = paletteCustomView.paletteSelectedColor
                if(imageColor != null) {
                    adbIcon300.drawable.setTint(imageColor)
                } else {
                    val imageDefaultColor: Int = paletteCustomView.defaultIconColor ?:
                    ResourcesCompat.getColor(resources, R.color.defaultIconColor, null)
                    adbIcon300.drawable.setTint(imageDefaultColor)
                }
                adbIcon300.invalidate()
            }

            paletteCustomView.setOnTouchListener { view, motionEvent ->
                val x = motionEvent.x
                val y = motionEvent.y
                when(motionEvent.action) {
                    MotionEvent.ACTION_UP -> { // Send coordinates of ending of touch (finger up)
                        Log.i("Click", "x: $x, y: $y")
                        paletteCustomView.selectColor(x, y)
                        view.performClick()
                    }
                    else -> { false}
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}