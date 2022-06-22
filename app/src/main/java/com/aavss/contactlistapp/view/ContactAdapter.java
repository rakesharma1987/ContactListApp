package com.aavss.contactlistapp.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aavss.contactlistapp.BR;
import com.aavss.contactlistapp.R;
import com.aavss.contactlistapp.databinding.ItemContactBinding;
import com.aavss.contactlistapp.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private ItemContactBinding binding;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_contact,
                viewGroup, false);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {

        contactViewHolder.onBind(contacts.get(i));
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final ItemContactBinding binding;
        ContactViewHolder(ItemContactBinding itemContactBinding) {
            super(itemContactBinding.getRoot());
            this.binding = itemContactBinding;
        }

        void onBind(Contact contact) {
            binding.setVariable(BR.contact, contact);
//            binding.setContact(contact);
            binding.executePendingBindings();
        }
    }
}
