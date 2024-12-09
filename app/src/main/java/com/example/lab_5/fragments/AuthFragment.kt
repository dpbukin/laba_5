package com.example.lab_5.fragments

import androidx.fragment.app.Fragment
import com.example.lab_5.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lab_5.databinding.FragmentAuthBinding
import com.example.lab_5.model.UserSession

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val TAG = "AuthFragment"

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding null exception")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAuthBinding.bind(view)

        val args = arguments?.let { AuthFragmentArgs.fromBundle(it) }
        val userLogin = args?.userLogin
        val userPass = args?.userPass

        if (!userLogin.isNullOrEmpty() && !userPass.isNullOrEmpty()) {
            binding.userLoginAuth.setText(userLogin)
            binding.userPassAuth.setText(userPass)
        }

        binding.buttonAuth.setOnClickListener {
            val login = binding.userLoginAuth.text.toString().trim()
            val pass = binding.userPassAuth.text.toString().trim()

            val currentUser = UserSession.authenticateUser(login, pass)

            if (currentUser != null) {
                Toast.makeText(context, "Пользователь ${currentUser.login} вошел", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_authFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Неверные данные для входа", Toast.LENGTH_SHORT).show()
            }
        }

        binding.linkToReg.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_regFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}

