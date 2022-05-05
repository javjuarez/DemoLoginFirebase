package com.example.demologinfirebase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demologinfirebase.R
import com.example.demologinfirebase.databinding.FragmentPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class PrincipalFragment : Fragment() {

    private lateinit var binding: FragmentPrincipalBinding
    private lateinit var autenticacion: FirebaseAuth

    private var usuario: FirebaseUser? = null

    private lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autenticacion = FirebaseAuth.getInstance()

        usuario = autenticacion.currentUser

        loginFragment = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrincipalBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (usuario != null) {
            val displayName = if (usuario?.displayName.isNullOrEmpty()) usuario?.email else usuario?.displayName
            binding.textViewUser.text = "Usuario: $displayName"
        }
        binding.buttonLogOut.setOnClickListener {
            autenticacion.signOut()
            Toast.makeText(context, "El usuario cerró sesión", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_principalFragment_to_loginFragment)
        }
    }
}