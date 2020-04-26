package com.woowrale.jmvvm.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.woowrale.jmvvm.R;
import com.woowrale.jmvvm.data.model.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Context context;
    private List<Contact> contactList;
    private ContactsAdapterListener listener;

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        public TextView name;
        @BindView(R.id.phone)
        public TextView phone;
        @BindView(R.id.thumbnail)
        public ImageView thumbnail;

        public ContactsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactList.get(getAdapterPosition()));
                }
            });
        }
    }


    public ContactsAdapter(Context context, List<Contact> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_item, parent, false);

        return new ContactsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, final int position) {
        final Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());

        Glide.with(context)
                .load(contact.getProfileImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Contact contact);
    }
}
