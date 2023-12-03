package uz.gita.eventsapplicationgb.presentation.screens.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.eventsapplicationgb.R
import uz.gita.eventsapplicationgb.databinding.ScreenAboutBinding

@AndroidEntryPoint
class AboutScreen : Fragment(R.layout.screen_about) {


    private val binding: ScreenAboutBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imgClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}