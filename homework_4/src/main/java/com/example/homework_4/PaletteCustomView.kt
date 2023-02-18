package com.example.homework_4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class PaletteCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {



    private val squarePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    private val paletteColors: List<Int>

    private val borderColor: Int

    private var selectedColor: Int? = null

    val paletteSelectedColor: Int?
        get() = selectedColor

    init {

        isClickable = true

        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.PaletteCustomViewView)

        paletteColors = listOf(
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor1, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor2, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor3, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor4, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor5, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor6, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor7, 0),
            typedArray.getColor(R.styleable.PaletteCustomViewView_paletteColor8, 0)
        )
        borderColor = typedArray.getColor(
            R.styleable.PaletteCustomViewView_selectedBorderColor, 0)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var currentPos = 0f
        val squareWidth = width.toFloat() / paletteColors.size

        paletteColors.forEach {

            // Draw each color of palette
            squarePaint.color = it
            canvas.drawRect(
                currentPos,
                height.toFloat(),
                currentPos + squareWidth,
                0f,
                squarePaint)

            // Draw border around selected color
            if(selectedColor == it) {
                borderPaint.color = borderColor
                val strokeWidth = 10f
                borderPaint.strokeWidth = strokeWidth
                canvas.drawRect(currentPos + strokeWidth/2,
                    height.toFloat() - strokeWidth/2,
                    currentPos + squareWidth - strokeWidth/2,
                    0f + strokeWidth/2,
                    borderPaint)
            }
            currentPos += squareWidth
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = resolveSize(widthMeasureSpec, widthMeasureSpec)
        val measuredHeight = measuredWidth / paletteColors.size
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun performClick(): Boolean {
        selectedColor = paletteColors[Random.nextInt(0, paletteColors.size)]
        invalidate()

        super.performClick()
        return true
    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        setCurrentColor(x, y)
//        performClick()
//            return super.onTouchEvent(event)
//    }
//
//    private fun setCurrentColor(x: Float, y: Float) {
//
//    }
}