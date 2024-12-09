package com.example.lab_5.fragments

import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lab_5.R
import com.example.lab_5.databinding.FragmentRegBinding
import com.example.lab_5.model.User
import com.example.lab_5.model.UserSession

class RegFragment : Fragment(R.layout.fragment_reg) {

    private val TAG = "RegFragment"

    private var _binding: FragmentRegBinding? = null

    private val binding get() = _binding?: throw IllegalStateException("Binding null exception")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegBinding.bind(view)

        binding.buttonReg.setOnClickListener {
            val login = binding.userLogin.text.toString().trim()
            val email = binding.userEmail.text.toString().trim()
            val pass = binding.userPass.text.toString().trim()

            if (login.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {

                val newUser = User(login, email, pass)
                UserSession.addUser(newUser)

                Toast.makeText(context, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()


                val action = RegFragmentDirections.actionRegFragmentToAuthFragment(
                    userLogin = login,
                    userPass = pass
                )

                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            }
        }

        binding.linkToAuth.setOnClickListener {
            findNavController().navigate(R.id.action_regFragment_to_authFragment)
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

