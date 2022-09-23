package com.example.itunes100albums.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.itunes100albums.R
import com.example.itunes100albums.room.Entity
import org.w3c.dom.Text

import javax.inject.Inject

class FavoritesFragmentRecyclerViewAdapter @Inject constructor(
    private val glide : RequestManager
) : RecyclerView.Adapter<FavoritesFragmentRecyclerViewAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.albumRowImageView)
        val albumName = holder.itemView.findViewById<TextView>(R.id.albumRowArtNameText)
        val artistName = holder.itemView.findViewById<TextView>(R.id.albumRowArtistNameText)
        val releaseDate = holder.itemView.findViewById<TextView>(R.id.albumRowReleaseDate)
        val price = holder.itemView.findViewById<TextView>(R.id.rateTxtView)
        val album = albums[position]
        holder.itemView.apply {
            glide.load(album.imageUrl).into(imageView)
            albumName.text = "${album.name}"
            artistName.text = "${album.artistName}"
            releaseDate.text = "${album.releaseDate}"
            price.text = "${album.price}"

            imageView.setOnClickListener(View.OnClickListener {
                Log.d("iTunes", "Imageview of " + album.artistName + " is clicked")
            })

            //Use setOnClickListener to listen for clicks anywhere on the recycler row
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

}