package com.azizutku.urlshortener.presentation.fragments.signin

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


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SigninFragment : Fragment() {

    companion object {
        fun newInstance() = SigninFragment()
    }

    private val viewModel: SigninViewModel by viewModels()
    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
    }

}