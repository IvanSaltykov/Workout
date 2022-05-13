package com.example.connect.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.connect.R
import com.example.connect.Validator
import com.example.connect.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var binding: ActivitySignInBinding
    lateinit var auth: FirebaseAuth
    var validator = Validator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null)
                    firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        with(binding) {
            buttonSignUpSignIn.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
//                auth.signOut()
            }
//            buttonSignInSignIn.setOnClickListener {
//                if (validator.checkEmail(editTextEmailSignIn.text.toString()) &&
//                    validator.checkPassword(editTextPasswordSignIn.text.toString())) {
//                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
//                }
//            }
            binding.buttonGoogleSignIn.setOnClickListener {
                signInWithGoogle()
            }
            
        }
        signInAccount()
    }

    private fun getClient(): GoogleSignInClient {
        val client = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, client)
    }
    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful)
                signInAccount()
        }
    }

    private fun signInAccount() {
        if (auth.currentUser != null)
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
    }
}