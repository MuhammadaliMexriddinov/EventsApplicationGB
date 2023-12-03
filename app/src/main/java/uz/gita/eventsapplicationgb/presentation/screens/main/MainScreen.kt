package uz.gita.eventsapplicationgb.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.eventsapplicationgb.R
import uz.gita.eventsapplicationgb.databinding.ScreenMainBinding
import uz.gita.eventsapplicationgb.presentation.adapters.EventAdapter
import uz.gita.eventsapplicationgb.presentation.dialogs.BottomMenuDialog
import uz.gita.eventsapplicationgb.presentation.viewmodels.MainViewModel
import uz.gita.eventsapplicationgb.presentation.viewmodels.impl.MainViewModelImpl
import uz.gita.eventsapplicationgb.service.EventService

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewBinding: ScreenMainBinding by viewBinding()

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    private val adapter: EventAdapter by lazy { EventAdapter() }

    //private  val  broadcast=EventBroadcast()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        viewBinding.listEvents.adapter = adapter
        viewModel.allEventData.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        val intent = Intent(requireContext(), EventService::class.java)
        requireActivity().startService(intent)



            adapter.setSwitchChangedListener {
                viewModel.itemClick(it)
            }


        viewModel.getAllEvents()


        viewBinding.imageSettings.setOnClickListener {
            val dialog = BottomMenuDialog()
            dialog.show(childFragmentManager, "dia")
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        requireActivity().unregisterReceiver(broadcast)
//    }



}