package by.radiance.space.picrures.ui.list.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.radiance.space.picrures.R
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Id
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.android.synthetic.main.item_today_picture.view.*

class AstronomyPictureAdapter(
        var data: List<Picture?>,
        private val onClick: (id: Id) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TODAY_PICTURE = 1
        const val PICTURE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position in 0..1) TODAY_PICTURE else PICTURE
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

    class TodayPictureHolder(view: View, private val onClick: ((id: Id) -> Unit)): RecyclerView.ViewHolder(view) {

        fun bind(picture: Picture?) {
            if (picture != null) {
                itemView.iv_today_picture.setOnClickListener {
                    onClick.invoke(picture.id)
                }

                Glide
                        .with(itemView)
                        .load((picture.source as Image).huge)
                        .into(itemView.iv_today_picture)

                itemView.tv_today_title.text = picture.title
            }
        }
    }

    class PictureHolder(view: View, private val onClick: ((id: Id) -> Unit)): RecyclerView.ViewHolder(view) {

        fun bind(picture: Picture?) {
            if (picture != null) {
                itemView.iv_picture.setOnClickListener {
                    onClick.invoke(picture.id)
                }

                Glide
                        .with(itemView)
                        .load((picture.source as Image).huge)
                        .into(itemView.iv_picture)

                itemView.tv_title.text = picture.title
            }
        }
    }
}