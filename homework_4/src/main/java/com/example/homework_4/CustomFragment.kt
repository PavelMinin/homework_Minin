package com.example.homework_4

import android.graphics.Color
import android.graphics.PointF
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

            // Changing color of the icon
            paletteCustomView.setOnClickListener {
                val imageColor = paletteCustomView.paletteSelectedColor
                if(imageColor != null) {
                    adbIcon300.drawable.setTint(imageColor)
                } else {
                    adbIcon300.drawable.setTint(ResourcesCompat.getColor(
                        resources, R.color.defaultIconColor, null
                    ))
                }
                adbIcon300.invalidate()
            }

            paletteCustomView.setOnTouchListener { view, motionEvent ->
                val x = motionEvent.x
                val y = motionEvent.y
                when(motionEvent.action) {
                    MotionEvent.ACTION_UP -> {
                        paletteCustomView.clickPosition.x = x
                        paletteCustomView.clickPosition.y = y
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