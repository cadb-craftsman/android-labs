package com.woowrale.kmvvm.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.woowrale.kmvvm.R
import com.woowrale.kmvvm.data.model.Contact

class ContactsAdapter(
    private val context: Context,
    private val contactList: List<Contact>,
    private val listener: ContactsAdapterListener
) :
    RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_row_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.name.setText(contact.name)
        holder.phone.setText(contact.phone)

        Glide.with(context)
            .load(contact.profileImage)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var name: TextView
        lateinit var phone: TextView
        lateinit var thumbnail: ImageView

        init {
            name = view.findViewById(R.id.name)
            phone = view.findViewById(R.id.phone)
            thumbnail = view.findViewById(R.id.thumbnail)
            view.setOnClickListener {
                listener.onContactSelected(contactList[adapterPosition])
            }
        }
    }

    interface ContactsAdapterListener {
        fun onContactSelected(contact: Contact)
    }
}