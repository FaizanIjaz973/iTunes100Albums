package com.example.itunes100albums.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.itunes100albums.R
import com.example.itunes100albums.model.recyclerviewentity.Entity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainRecyclerViewAdapter @Inject constructor(
    private val glide : RequestManager
) : RecyclerView.Adapter<MainRecyclerViewAdapter.ArtViewHolder>() {

    class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var albums: List<Entity>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.artRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.artRowArtNameText)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.artRowArtistNameText)
        val yearText = holder.itemView.findViewById<TextView>(R.id.artRowYearText)
        val album = albums[position]
        holder.itemView.apply {
            glide.load(album.imageUrl).into(imageView)
            nameText.text = "${album.name}"
            artistNameText.text = "${album.artistName}"
            yearText.text = "${album.year}"

            imageView.setOnClickListener(View.OnClickListener {
                Log.d("iTunes", "Imageview of " + album.artistName + " is clicked")
            })
/*            setOnClickListener {
                Log.d("iTunes", "Clicked " + entity.artistName)
            }*/
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}