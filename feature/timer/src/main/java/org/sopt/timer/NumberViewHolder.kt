package org.sopt.timer

import androidx.recyclerview.widget.RecyclerView
import org.sopt.mainfeature.R
import org.sopt.timer.databinding.ItemNumberPickerBinding
import org.sopt.timer.dummymodel.PickerItem

class NumberViewHolder(
  val binding: ItemNumberPickerBinding,
) : RecyclerView.ViewHolder(binding.root) {
  fun onBind(data: PickerItem?) {
    if (data == null) return
    with(binding) {
      if (data.isSelected) {
        tvText.setTextAppearance(R.style.Typography_suit_bold_18)
      } else {
        tvText.setTextAppearance(R.style.Typography_suit_regular_16)
      }
      tvText.text = data.text
    }
  }
}