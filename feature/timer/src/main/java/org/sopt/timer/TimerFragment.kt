package org.sopt.timer

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.sopt.mainfeature.R
import org.sopt.timer.databinding.FragmentTimerBinding
import org.sopt.timer.dummymodel.Timer
import org.sopt.timer.modifytimer.ModifyTimerBottomSheetFragment
import org.sopt.ui.fragment.colorOf
import org.sopt.ui.fragment.snackBar

class TimerFragment : Fragment() {
  private var _binding: FragmentTimerBinding? = null
  private val binding
    get() = requireNotNull(_binding) {
    }
  private lateinit var completeTimerAdapter: CompleteTimerAdapter
  private lateinit var waitTimerAdapter: WaitTimerAdapter
  var timerExist = true
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    _binding = FragmentTimerBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.tvTimerTitle.setOnClickListener {
      if (timerExist) {
        binding.svTimerExist.isVisible = true
        binding.llTimerNotExist.isGone = true
        timerExist = false
      } else {
        binding.svTimerExist.isGone = true
        binding.llTimerNotExist.isVisible = true
        timerExist = true
      }
    }

    completeTimerAdapter = CompleteTimerAdapter({ snackBar(binding.root, { "안녕" }) })
    waitTimerAdapter = WaitTimerAdapter({}, { ModifyTimerBottomSheetFragment.newInstance(it.id).show(parentFragmentManager, this.tag) })

    val list = listOf(
      Timer(1, "네이버", "일요일", true, 8, 37),
      Timer(1, "네이버", "일요일", true, 8, 37),
    )
    // val list = emptyList<Timer>()
    completeTimerAdapter.submitList(list)
    waitTimerAdapter.submitList(list)
    binding.tvTimerCompleteCount.text = list.count().toString()
    if (list.count() != 0) {
      val color = colorOf(R.color.primary)
      val textColor = colorOf(R.color.white)
      val colorStateList = ColorStateList.valueOf(color)
      binding.flTimerCompleteCount.backgroundTintList = colorStateList
      binding.tvTimerCompleteCount.setTextColor(textColor)
      binding.tvTimerNotComplete.isGone = true
    } else {
      binding.tvTimerNotComplete.isVisible = true
    }
    binding.rvTimerComplete.adapter = completeTimerAdapter
    binding.rvTimerWait.adapter = waitTimerAdapter

    binding.ivTimerPlus.setOnClickListener {
      findNavController().navigate(org.sopt.timer.R.id.action_navigation_timer_to_navigation_timer_clip_select)
    }
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
