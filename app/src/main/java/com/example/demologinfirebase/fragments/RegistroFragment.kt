package com.example.demologinfirebase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.demologinfirebase.R
import com.example.demologinfirebase.databinding.FragmentRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class RegistroFragment : Fragment() {

    private lateinit var binding: FragmentRegistroBinding
    private lateinit var autenticacion: FirebaseAuth
    private lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autenticacion = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        with(binding) {
            val nombre = editTextNombre.text.toString().trim()
            val apellido = editTextApellido.text.toString().trim()
            val correo = editTextCorreo.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Todos los ccampos son requeridos", Toast.LENGTH_SHORT).show()
                return
            }
            autenticacion.createUserWithEmailAndPassword(correo, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Te haz registrado $nombre $apellido", Toast.LENGTH_SHORT).show()
                    val usuario = autenticacion.currentUser
                    val profileUpdate = UserProfileChangeRequest.Builder().setDisplayName("$nombre $apellido").build()
                    usuario?.updateProfile(profileUpdate)?.addOnCompleteListener { usuarioUpdate ->
                        if (usuarioUpdate.isSuccessful)
                            Log.d("DISPLAYNAME", "Display name asignado correctamente")
                    }
                    findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
                } else {
                    Toast.makeText(context, "El correo $correo ya estaba registrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}