package org.android.go.sopt.presentation.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.presentation.viewmodel.GalleryViewModel
import org.android.go.sopt.util.ContentUriRequestBody

class GalleryFragment : Fragment() {
    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private lateinit var imgUri: Uri
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) {
            "binding null"
        }

    val launcher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            with(binding) {
                imgUri = it!!
                ivGalleryFirst.load(it)
            }
        }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(
                requireContext(),
                "허가",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                "불허",
                Toast.LENGTH_SHORT
            ).show()
        }

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
        binding.root.setOnClickListener {
            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }
        binding.btnGalleryPickImage.setOnClickListener {
            launcher.launch("image/*")
        }
        uploadMusic()
    }

    private fun uploadMusic() {
        with(binding) {
            btnComplete.setOnClickListener {
                if (!etId.text.isNullOrBlank() && !etSinger.text.isNullOrBlank()) {
                    viewModel.setRequestBody(
                        ContentUriRequestBody(requireContext(),imgUri),etId.text.toString(),
                            etSinger.text.toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}