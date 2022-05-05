package com.example.demologinfirebase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.demologinfirebase.R
import com.example.demologinfirebase.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var autenticacion: FirebaseAuth

    private lateinit var principalFragment: PrincipalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autenticacion = FirebaseAuth.getInstance()
        principalFragment = PrincipalFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
           login()
        }
        binding.buttonMostrarRegistrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }
    }

    private fun login() {
        val correo: String = binding.editTextCorreo.text.toString()
        val password: String = binding.editTextPassword.text.toString()
        if (correo == "" || password == "") {
            Toast.makeText(context, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()
            return
        }
        autenticacion.signInWithEmailAndPassword(correo, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Bienvenido usuario $correo", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_principalFragment)
            } else {
                Toast.makeText(context, "El correo $correo no está registrado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}