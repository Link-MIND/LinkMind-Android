package org.sopt.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.home.databinding.ItemWeekLinkBinding
import org.sopt.ui.view.ItemDiffCallback

class HomeWeekLinkAdapter(
  private val onClickItem: () -> Unit,
) : ListAdapter<WeekLinkDummy, HomeWeekLinkViewHolder>(DiffUtil) {
  override fun onBindViewHolder(holder: HomeWeekLinkViewHolder, position: Int) {
    holder.onBind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWeekLinkViewHolder {
    return HomeWeekLinkViewHolder(
      ItemWeekLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onClickItem,
    )
  }

  companion object {
    private val DiffUtil = ItemDiffCallback<WeekLinkDummy>(
      onItemsTheSame = { old, new -> old.title == new.title },
      onContentsTheSame = { old, new -> old == new },
    )
  }
}
