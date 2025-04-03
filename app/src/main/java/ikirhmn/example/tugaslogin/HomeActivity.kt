package ikirhmn.example.tugaslogin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Ambil data dari intent sebagai JSON, lalu konversi kembali ke User object
        val userJson = intent.getStringExtra("user")
        val user = if (userJson != null) Gson().fromJson(userJson, User::class.java) else null

        val username = user?.username ?: "User"
        val textWelcome = findViewById<TextView>(R.id.welcome)
        textWelcome.text = "Halo $username"
    }
}
