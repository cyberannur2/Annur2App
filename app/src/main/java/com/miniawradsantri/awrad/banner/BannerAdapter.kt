package com.miniawradsantri.awrad.banner
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.miniawradsantri.awrad.databinding.ItemBannerBinding


class BannerAdapter() : RecyclerView.Adapter<BannerAdapter.FragmentViewHolder>() {
    class FragmentViewHolder(itemView: ItemBannerBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(url: String) {
            with(binding) {
                Glide.with(itemView)
                    .load(url)
                    .into(ivBanner)
            }
        }
    }

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    private val imageRefs = mutableListOf<StorageReference>()

    private fun fetchImagesRefs() {
        val listRef = storageRef.child("banner")
        listRef.listAll().addOnSuccessListener { listResult ->
            listResult.items.forEach { item ->
                imageRefs.add(item)
            }
            notifyDataSetChanged()
        }.addOnFailureListener {
            it.printStackTrace()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentViewHolder {
        return FragmentViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = imageRefs.size

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        if (position < imageRefs.size) {
            val imageRef = imageRefs[position]
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                holder.bind(uri.toString())
            }.addOnFailureListener {
                it.printStackTrace()
            }
        }
    }

    init {
        fetchImagesRefs()
    }
}