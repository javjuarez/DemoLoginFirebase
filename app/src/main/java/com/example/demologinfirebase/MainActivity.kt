package com.example.demologinfirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.demologinfirebase.databinding.ActivityMainBinding
import com.example.demologinfirebase.fragments.LoginFragment
import com.example.demologinfirebase.fragments.PrincipalFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var autenticacion: FirebaseAuth

    private lateinit var pantallaPrincipalFragment: PrincipalFragment
    private lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pantallaPrincipalFragment = PrincipalFragment()
        loginFragment = LoginFragment()

        autenticacion = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = autenticacion.currentUser
        if (user != null) {
            // Continuar con la pantalla principal

            val navController =
                supportFragmentManager.primaryNavigationFragment?.findNavController()
            navController?.navigate(R.id.action_loginFragment_to_principalFragment)

        }
    }
}