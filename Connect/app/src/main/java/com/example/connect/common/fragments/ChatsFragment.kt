package com.example.connect.common.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connect.common.adapters.MessageAdapter
import com.example.connect.databinding.FragmentChatsBinding
import com.example.connect.models.UserMessege
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatsFragment : Fragment() {
    lateinit var binding: FragmentChatsBinding
    lateinit var auth: FirebaseAuth
    lateinit var adapter: MessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatsBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        val database = Firebase.database
        val myref = database.getReference("Message")
        adapter = MessageAdapter()
        binding.buttonOk.setOnClickListener {
            if (binding.editTextNewMessage.text.toString() != "") {
                myref.child(myref.push().key ?: "1111")
                    .setValue(
                        UserMessege(
                            binding.editTextNewMessage.text.toString(),
                            auth.currentUser?.displayName
                        )
                    )
                binding.editTextNewMessage.text = null
            } else {
                binding.editTextNewMessage.error = "Поле не должно быть пустым"
            }
        }
        onChangeMessage(myref)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    private fun onChangeMessage(dref: DatabaseReference) {
        dref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = ArrayList<UserMessege>()
                    for (s in snapshot.children) {
                        val message = s.getValue(UserMessege::class.java)
                        if (message != null)
                            list.add(message)
                    }
                    adapter.submitList(list)

                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
        )
    }
}