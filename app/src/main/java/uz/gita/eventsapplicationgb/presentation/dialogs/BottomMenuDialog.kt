package uz.gita.eventsapplicationgb.presentation.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.eventsapplicationgb.BuildConfig
import uz.gita.eventsapplicationgb.MainActivity
import uz.gita.eventsapplicationgb.R
import uz.gita.eventsapplicationgb.databinding.DialogMenuBottomBinding
import uz.gita.eventsapplicationgb.presentation.viewmodels.BottomMenuViewModel
import uz.gita.eventsapplicationgb.presentation.viewmodels.impl.BottomMenuViewModelImpl
import uz.gita.eventsapplicationgb.utils.Utils


class BottomMenuDialog : BottomSheetDialogFragment() {


    private lateinit var binding: DialogMenuBottomBinding

    private val viewModel: BottomMenuViewModel by viewModels<BottomMenuViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogMenuBottomBinding.inflate(inflater, container, false)
        viewModel.aboutUsLiveData.observe(this, aboutObserver)
        viewModel.shareAppLiveData.observe(this, shareAppObserver)
       // viewModel.supportUsLiveData.observe(this, supportObserver)

        binding.apply {
            tvAboutUs.setOnClickListener { viewModel.aboutClick() }
//            tvSupportUs.setOnClickListener { viewModel.supportClick() }
            tvShareApp.setOnClickListener { viewModel.shareClick() }
        }
        return binding.root
    }

    private val supportObserver = Observer<Unit> {
        dismiss()
        Utils.goToPlayMarket(activity as MainActivity)
    }

    private val shareAppObserver = Observer<Unit> {
        dismiss()
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var shareMessage = "\nLet me recommend you this application\n\n"
            shareMessage =
                """
                ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                
                
                """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }
    }

    private val aboutObserver = Observer<Unit> {
        dismiss()
        findNavController().navigate(R.id.aboutScreen)
    }

}