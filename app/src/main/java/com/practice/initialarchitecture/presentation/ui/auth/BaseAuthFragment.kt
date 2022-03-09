package com.practice.initialarchitecture.presentation.ui.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.practice.initialarchitecture.presentation.ui.auth.AuthViewModel

abstract class BaseAuthFragment constructor(
    layoutResource: Int
) : Fragment(layoutResource){

    val authViewModel: AuthViewModel by activityViewModels()

}