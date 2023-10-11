//package com.ug.route.ui.unused.six_boxes
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.gson.Gson
//import com.ug.route.ui.unused.six_boxes.ResetCode
//import com.ug.route.data.models.ForgetPasswordResponse
//import com.ug.route.data.repositories.Repository
//import com.ug.route.networking.dto_models.ResetPasswordDTO
//import com.ug.route.networking.dto_models.ValidationCodeDTO
//import com.ug.route.ui.code_validation_screen.CodeValidationState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class SixBoxesCodeValidationViewModel  @Inject constructor(
//    private val repository: Repository,
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//    private val _screenState = MutableStateFlow(
//        CodeValidationState(
//            resetCode = ResetCode(
//                digit1 = "",
//                digit2 = "",
//                digit3 = "",
//                digit4 = "",
//                digit5 = "",
//                digit6 = ""),
//            message = "",
//            isError = false,
//            isLoading = false,
//            email = checkNotNull(savedStateHandle["email"]),
//            launchedEffectKey = false,
//            isClickable = true)
//    )
//
//    val screenState = _screenState.asStateFlow()
//
//    fun onChangeDigit(index : Int, newDigit: String) {
//
//        when (index) {
//
//            0 -> _screenState.update { it.copy(resetCode = it.resetCode.copy(digit1 = newDigit)) }
//            1 -> _screenState.update { it.copy(resetCode = it.resetCode.copy(digit2 = newDigit)) }
//            2 -> _screenState.update { it.copy(resetCode = it.resetCode.copy(digit3 = newDigit)) }
//            3 -> _screenState.update { it.copy(resetCode = it.resetCode.copy(digit4 = newDigit)) }
//            4 -> _screenState.update { it.copy(resetCode = it.resetCode.copy(digit5 = newDigit)) }
//            5 -> _screenState.update { it.copy(resetCode = it.resetCode.copy(digit6 = newDigit)) }
//        }
//    }
//
//    fun updateMessageAndKeyOnClick(isClickable: Boolean) {
//        val newMessage = if (isClickable) "we've sent the code to your email" else _screenState.value.message
//        _screenState.update { it.copy(message = newMessage, launchedEffectKey = !it.launchedEffectKey) }
//    }
//
//    fun disableClickHere(){
//        _screenState.update { it.copy(isClickable = false) }
//    }
//
//    fun forgetPassword(){
//
//        viewModelScope.launch {
//
//            repository.forgetPassword(ResetPasswordDTO(email = _screenState.value.email))
//        }
//    }
//
//    fun codeValidation(){
//
//        if (checkInputError()) return
//        viewModelScope.launch {
//
//            _screenState.update { it.copy(isLoading = true) }
//
//            val response = repository.codeValidation(ValidationCodeDTO(codeProvider()))
//            val errorMessage = response.getErrorMessage()
//
//            if (response.isSuccessful) _screenState.update { it.copy(message = "Done ya ro7y") }
//            else _screenState.update { it.copy(message = errorMessage ?: "An error occurred") }
//
//            _screenState.update {it.copy(launchedEffectKey = !_screenState.value.launchedEffectKey,isLoading = false)}
//        }
//    }
//
//    private fun checkInputError(): Boolean {
//
//        val resetCode =  _screenState.value.resetCode
//        val isError = resetCode.digit1.isEmpty() ||
//                resetCode.digit2.isEmpty() ||
//                resetCode.digit3.isEmpty() ||
//                resetCode.digit4.isEmpty() ||
//                resetCode.digit5.isEmpty() ||
//                resetCode.digit6.isEmpty()
//        _screenState.update {it.copy(isError = isError)}
//        return  isError
//    }
//
//    private fun Response<*>.getErrorMessage(): String? {
//        val errorResponse: ForgetPasswordResponse? = errorBody()?.charStream()?.use {
//            Gson().fromJson(it, ForgetPasswordResponse::class.java)
//        }
//        return errorResponse?.message
//    }
//
//    private fun codeProvider() : String{
//
//        return  _screenState.value.resetCode.digit1 +
//                _screenState.value.resetCode.digit2 +
//                _screenState.value.resetCode.digit3 +
//                _screenState.value.resetCode.digit4 +
//                _screenState.value.resetCode.digit5 +
//                _screenState.value.resetCode.digit6
//    }
//
//    fun onInternetError(){
//        _screenState.update { it.copy(
//            message = "No Internet Connection",
//            launchedEffectKey = !_screenState.value.launchedEffectKey) }
//    }
//}