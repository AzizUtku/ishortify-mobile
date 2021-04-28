package com.azizutku.urlshortener.presentation.activity

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.core.Amplify
import com.azizutku.urlshortener.R
import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.data.model.Shot
import com.azizutku.urlshortener.databinding.ActivityMainBinding
import com.azizutku.urlshortener.extension.format
import com.azizutku.urlshortener.utils.Constants
import com.azizutku.urlshortener.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import kotlin.math.ceil

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var shotsData: List<Data>
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUI()
        subscribeObservers()
        initCognito()
        // signup()
        signin()
    }

    private fun signup() {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), "azizutku@email.com")
            .build()
        Amplify.Auth.signUp("username", "ABCCDE12345!a", options,
            { Timber.d("AuthQuickStart: Sign up succeeded: $it") },
            { Timber.e ("AuthQuickStart: Sign up failed $it") }
        )
    }

    private fun signin() {
        Amplify.Auth.signIn("username", "ABCCDE12345!a",
            { result ->
                if (result.isSignInComplete) {
                    Timber.d("AuthQuickStart: Sign in succeeded")
                } else {
                    Timber.d("AuthQuickStart: Sign in not complete")
                }
            },
            { Timber.e ("AuthQuickStart: Failed to sign in $it") }
        )

        Amplify.Auth.fetchAuthSession(
            {
                val session = it as AWSCognitoAuthSession
                val idToken = session.userPoolTokens.value?.idToken
                Log.i("AuthQuickStart: ", "Id: $idToken")
            },
            { Log.e("AuthQuickStart", "Failed to fetch session", it) }
        )
    }

    private fun initCognito() {
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.configure(applicationContext)
        Log.i("AmplifyQuickstart","AmplifyQuickstart: 1")
        Amplify.Auth.fetchAuthSession(
            { Timber.d("AmplifyQuickstart: Auth session = $it") },
            { Timber.e("AmplifyQuickstart: Failed to fetch auth session $it") }
        )
    }

    private fun initUI() {
        progressDialog = ProgressDialog(this).apply {
            setTitle(getString(R.string.desc_loading))
        }
    }

    private fun subscribeShotsData() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Data>> -> {
                    Timber.d("observed")
                    progressDialog.dismiss()
                    shotsData = dataState.data
                }
                is DataState.Error -> {
                    progressDialog.dismiss()
                    Timber.d("observed: ${dataState.exception.message}")
                }
                is DataState.Loading -> {
                    progressDialog.show()
                    Timber.d("observed: loading")
                }
                else -> {
                }
            }
        })
    }

    private fun subscribeObservers() {
        subscribeShotsData()
    }

}