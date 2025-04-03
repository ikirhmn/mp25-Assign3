package ikirhmn.example.tugaslogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        val inputEmail = view.findViewById<TextInputEditText>(R.id.input_email)
        val inputPassword = view.findViewById<TextInputEditText>(R.id.input_password)

        btnLogin.setOnClickListener {
            val email = inputEmail.text?.toString()?.trim()
            val password = inputPassword.text?.toString()?.trim()

            val sharedPrefs = requireActivity().getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
            val userJson = sharedPrefs.getString("user", null)

            if (!userJson.isNullOrEmpty()) {
                val user = Gson().fromJson(userJson, User::class.java)
                if (email == user.email && password == user.password) {
                    val intent = Intent(requireContext(), HomeActivity::class.java).apply {
                        putExtra("user", Gson().toJson(user)) // Kirim sebagai JSON
                    }
                    startActivity(intent)
                    requireActivity().finishAffinity()
                } else {
                    Toast.makeText(requireContext(), "Email atau password salah!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Belum ada user terdaftar!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
