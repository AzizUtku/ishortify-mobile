package com.azizutku.urlshortener.presentation.fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azizutku.urlshortener.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.azizutku.urlshortener.databinding.FragmentSigninBinding
import com.azizutku.urlshortener.databinding.FragmentSignupBinding
import com.azizutku.urlshortener.presentation.fragments.signin.SigninViewModel


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
    }

}