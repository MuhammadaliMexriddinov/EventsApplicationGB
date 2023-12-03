package uz.gita.eventsapplicationgb.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.eventsapplicationgb.R
import uz.gita.eventsapplicationgb.data.room.entity.EventEntity
import uz.gita.eventsapplicationgb.databinding.ListItemEventBinding
import uz.gita.eventsapplicationgb.utils.Constants

class EventAdapter : ListAdapter<EventEntity, EventAdapter.ViewHolder>(itemEventCallback) {

    private var switchChangedListener: ((EventEntity) -> Unit)? = null

    fun setSwitchChangedListener(block: (EventEntity) -> Unit) {
        switchChangedListener = block
    }

    inner class ViewHolder(private val binding: ListItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.eventIsActive.setOnClickListener {
                val data = getItem(absoluteAdapterPosition)
                switchChangedListener?.invoke(data.copy(status = 1 - data.status))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvEventName.text = data.name
                tvEventSpeakName.text = getSpeakName(data.name)
                eventIsActive.isChecked = data.status == 1
                shapeImageEvent.setImageResource(Constants.images[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemEventBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_event, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()


    private companion object {
        fun getSpeakName(name: String) = "You have turned $name"
    }
}

private val itemEventCallback = object : DiffUtil.ItemCallback<EventEntity>() {
    override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity) =
        oldItem.name == newItem.name &&
                oldItem.status == newItem.status

}