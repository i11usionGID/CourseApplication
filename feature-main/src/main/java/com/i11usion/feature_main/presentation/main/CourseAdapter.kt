package com.i11usion.feature_main.presentation.main

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.i11usion.core.domain.model.Course
import com.i11usion.core.utils.formatter.toRuDate
import com.i11usion.feature_main.R

class CoursesAdapter(
    private val onFavoriteClick: (Course) -> Unit
) : ListAdapter<Course, CoursesAdapter.CourseViewHolder>(DiffCallback()) {

    private val previews = listOf(
        R.drawable.card_background_1,
        R.drawable.card_background_2,
        R.drawable.card_background_3
    )

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).hasLike) {
            VIEW_TYPE_FAVORITE
        } else {
            VIEW_TYPE_NOT_FAVORITE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val layoutRes = when (viewType) {
            VIEW_TYPE_FAVORITE -> R.layout.item_course_favorite
            VIEW_TYPE_NOT_FAVORITE -> R.layout.item_course_not_favorite
            else -> error("Unknown viewType: $viewType")
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(layoutRes, parent, false)

        return CourseViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private const val VIEW_TYPE_FAVORITE = 1
        private const val VIEW_TYPE_NOT_FAVORITE = 0
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val previewImage: ImageView = itemView.findViewById(R.id.imagePreview)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val description: TextView = itemView.findViewById(R.id.tvDescription)
        private val price: TextView = itemView.findViewById(R.id.tvPrice)
        private val rating: TextView = itemView.findViewById(R.id.tvRating)
        private val date: TextView = itemView.findViewById(R.id.tvDate)
        private val favorite: ImageView = itemView.findViewById(R.id.buttonBookmark)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(course: Course) {
            val imageRes = previews[course.id % previews.size]
            previewImage.setImageResource(imageRes)

            title.text = course.title
            description.text = course.description
            price.text = "${course.price} ₽"
            rating.text = course.rate.toString()
            date.text = course.publishDate.toRuDate()

            favorite.setOnClickListener {
                onFavoriteClick(course)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Course>() {

        override fun areItemsTheSame(old: Course, new: Course): Boolean {
            return old.id == new.id
        }

        override fun areContentsTheSame(old: Course, new: Course): Boolean {
            return old.hasLike == new.hasLike
        }
    }
}

