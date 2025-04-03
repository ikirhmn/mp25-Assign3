package ikirhmn.example.tugaslogin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class RegisFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_regis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnRegis = view.findViewById<Button>(R.id.btn_register)
        val inputUsername = view.findViewById<TextInputEditText>(R.id.input_username)
        val inputEmail = view.findViewById<TextInputEditText>(R.id.input_email)
        val inputPassword = view.findViewById<TextInputEditText>(R.id.input_password)

        btnRegis.setOnClickListener {
            val username = inputUsername.text?.toString()?.trim()
            val email = inputEmail.text?.toString()?.trim()
            val password = inputPassword.text?.toString()?.trim()

            if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(username, email, password)
            val sharedPrefs = requireActivity().getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
            val editor = sharedPrefs.edit()
            val userJson = Gson().toJson(user)
            editor.putString("user", userJson)
            editor.apply()

            Toast.makeText(requireContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
        }
    }
}
