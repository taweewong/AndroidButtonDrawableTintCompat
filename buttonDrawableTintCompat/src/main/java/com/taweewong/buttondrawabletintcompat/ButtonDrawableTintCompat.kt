package com.taweewong.buttondrawabletintcompat

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class ButtonDrawableTintCompat : AppCompatButton {
    private var drawableTint: ColorStateList? = null
    private var drawableTintResId: Int = 0

    constructor(context: Context) : super(context) {
        setupStyleables(null)
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setupStyleables(attrs)
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupStyleables(attrs, defStyleAttr)
        setup()
    }

    private fun setupStyleables(attrs: AttributeSet?, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonDrawableTintCompat)
        drawableTint = typedArray.getColorStateList(R.styleable.ButtonDrawableTintCompat_buttonDrawableTintCompat_drawable_tint)
        drawableTintResId = typedArray.getResourceId(R.styleable.ButtonDrawableTintCompat_buttonDrawableTintCompat_drawable_tint, 0)
        typedArray.recycle()
    }

    private fun setup() {
        updateDrawableTint()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        updateDrawableTint()
    }

    private fun updateDrawableTint() {
        when {
            drawableTint != null -> {
                applyTintColor(drawableTint)
            }
            drawableTintResId != 0 -> {
                applyTintColor(ContextCompat.getColorStateList(context, drawableTintResId))
            }
        }
    }

    private fun applyTintColor(tintColor: ColorStateList?) {
        tintColor?.let {
            //Left
            compoundDrawables[0]?.setColorFilter(
                tintColor.getColorForState(drawableState, tintColor.defaultColor),
                PorterDuff.Mode.SRC_IN)
            //Top
            compoundDrawables[1]?.setColorFilter(
                tintColor.getColorForState(drawableState, tintColor.defaultColor),
                PorterDuff.Mode.SRC_IN)
            //Right
            compoundDrawables[2]?.setColorFilter(
                tintColor.getColorForState(drawableState, tintColor.defaultColor),
                PorterDuff.Mode.SRC_IN)
            //Bottom
            compoundDrawables[3]?.setColorFilter(
                tintColor.getColorForState(drawableState, tintColor.defaultColor),
                PorterDuff.Mode.SRC_IN)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superSavedState = super.onSaveInstanceState()
        val savedState = SavedState(superSavedState)
        savedState.drawableTint = drawableTint
        savedState.drawableTintResId = drawableTintResId
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        val ss = state as SavedState
        drawableTint = ss.drawableTint
        drawableTintResId = ss.drawableTintResId
    }

    private class SavedState : BaseSavedState {
        var drawableTint: ColorStateList? = null
        var drawableTintResId: Int = 0

        constructor(superState: Parcelable) : super(superState)

        constructor(parcel: Parcel, classLoader: ClassLoader) : super(parcel) {
            this.drawableTint = parcel.readParcelable(classLoader)
            this.drawableTintResId = parcel.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeParcelable(drawableTint, flags)
            out.writeInt(drawableTintResId)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.ClassLoaderCreator<SavedState> =
                object : Parcelable.ClassLoaderCreator<SavedState> {
                    override fun createFromParcel(source: Parcel, loader: ClassLoader): SavedState {
                        return SavedState(source, loader)
                    }

                    override fun createFromParcel(`in`: Parcel): SavedState? {
                        return null
                    }

                    override fun newArray(size: Int): Array<SavedState?> {
                        return arrayOfNulls(size)
                    }
                }
        }
    }
}