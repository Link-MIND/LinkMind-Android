package designsystem.components.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import org.sopt.mainfeature.R
import org.sopt.mainfeature.databinding.EditTextBoxLinkmindBinding
import org.sopt.ui.view.onThrottleClick

@SuppressLint("CustomViewStyleable")
class LinkMindEditTextBox @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
  private val binding: EditTextBoxLinkmindBinding

  val editText
    get() = binding.editText

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    binding = EditTextBoxLinkmindBinding.inflate(inflater, this, true)

    binding.editText.setOnFocusChangeListener { _, hasFocus ->
      if (isPreventLosingFocus) return@setOnFocusChangeListener
      if (hasFocus) {
        preventFocusClearedByAdjustResize()
      }
    }

    binding.ivCancel.onThrottleClick {
      binding.editText.text.clear()
    }

    binding.editText.doAfterTextChanged { text ->
      binding.ivCancel.visibility =
        if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    val typedArray =
      context.obtainStyledAttributes(attrs, R.styleable.EditTextSearch, defStyleAttr, 0)

    val hint = typedArray.getText(R.styleable.EditTextSearch_editTextSearchHint)

    binding.editText.hint = hint

    typedArray.recycle()
  }

  private var isPreventLosingFocus = false

  private fun preventFocusClearedByAdjustResize() {
    isPreventLosingFocus = true
    val losingFocusDelay = 250L

    binding.editText.postDelayed(
      {
        binding.editText.run {
          if (isFocused.not()) {
            requestFocus()
          }
        }
        isPreventLosingFocus = false
      },
      losingFocusDelay,
    )
  }
}