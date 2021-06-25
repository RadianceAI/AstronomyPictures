package by.radiance.space.picrures.ui.list.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.radiance.space.picrures.R
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.PictureId
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.android.synthetic.main.item_today_picture.view.*

class AstronomyPictureAdapter(
    var data: List<AstronomyPicture>,
    private val onClick: (id: PictureId) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TODAY_PICTURE = 1
        const val PICTURE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TODAY_PICTURE else PICTURE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TODAY_PICTURE -> createTodayPictureViewHolder(parent)
            PICTURE -> createPictureViewHolder(parent)
            else -> createPictureViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodayPictureHolder -> holder.bind(data[position])
            is PictureHolder -> holder.bind(data[position])
        }
    }

    override fun getItemCount() = data.size

    private fun createTodayPictureViewHolder(parent: ViewGroup,): TodayPictureHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_today_picture, parent, false)

        return TodayPictureHolder(view, onClick)
    }

    private fun createPictureViewHolder(parent: ViewGroup): PictureHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_picture, parent, false)

        return PictureHolder(view, onClick)
    }

    class TodayPictureHolder(view: View, private val onClick: ((id: PictureId) -> Unit)): RecyclerView.ViewHolder(view) {

        fun bind(astronomyPicture: AstronomyPicture) {
            itemView.iv_today_picture.setOnClickListener {
                onClick.invoke(astronomyPicture.id)
            }

            Glide
                .with(itemView)
                .load((astronomyPicture.source as Image).huge)
                .into(itemView.iv_today_picture)

            itemView.tv_today_title.text = astronomyPicture.title
        }
    }

    class PictureHolder(view: View, private val onClick: ((id: PictureId) -> Unit)): RecyclerView.ViewHolder(view) {

        fun bind(astronomyPicture: AstronomyPicture) {
            itemView.iv_picture.setOnClickListener {
                onClick.invoke(astronomyPicture.id)
            }

            Glide
                .with(itemView)
                .load((astronomyPicture.source as Image).huge)
                .into(itemView.iv_picture)

            itemView.tv_title.text = astronomyPicture.title
        }
    }
}