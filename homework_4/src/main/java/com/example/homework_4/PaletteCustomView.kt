package com.example.homework_4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class PaletteCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val clickPosition: PointF = PointF(-1.0f, -1.0f)

    private val squarePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    private val colorStartPos = mutableMapOf<Int, Float>()

    private val paletteColors: List<Int>

    private val borderColor: Int

    private var selectedColor: Int? = null

    val paletteSelectedColor: Int?
        get() = selectedColor

    init {

        isClickable = true

        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.PaletteCustomView)

        paletteColors = listOf(
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor1, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor2, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor3, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor4, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor5, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor6, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor7, 0),
            typedArray.getColor(R.styleable.PaletteCustomView_paletteColor8, 0)
        )
        borderColor = typedArray.getColor(
            R.styleable.PaletteCustomView_selectedBorderColor, 0)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var currentPos = 0f
        val squareWidth = width.toFloat() / paletteColors.size

        paletteColors.forEach {
             if(currentPos <= clickPosition.x && clickPosition.x < currentPos + squareWidth) {
                 selectedColor = it
             }

            colorStartPos[it] = currentPos

            // Draw each color of palette
            squarePaint.color = it
            canvas.drawRect(
                currentPos,
                height.toFloat(),
                currentPos + squareWidth,
                0f,
                squarePaint)

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
        super.performClick()

        invalidate()
        return true
    }

    fun selectColor(x: Float, y: Float) {
        colorStartPos.forEach { color, posX ->
            if(posX <= x && x < (posX + width.toFloat() / paletteColors.size)) {
                selectedColor = color
            }
        }
    }
}