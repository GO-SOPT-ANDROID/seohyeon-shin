package org.android.go.sopt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.adapter.GalleryAdapter
import org.android.go.sopt.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) {
            "binding null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pagerHome.adapter = GalleryAdapter().apply {
            setItemList(
                listOf(
                    R.drawable.iv_cat,
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_foreground
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}