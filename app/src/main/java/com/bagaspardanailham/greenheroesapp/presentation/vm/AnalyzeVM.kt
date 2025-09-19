package com.bagaspardanailham.greenheroesapp.presentation.vm

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagaspardanailham.greenheroesapp.presentation.UiState
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

data class AnalyzeUiState(
    var apiResp: UiState<DiseaseResult> = UiState.Empty
)

@Serializable
data class DiseaseResult(
    val diseaseName: String = "",
    val description: String = "",
    val treatment: String = ""
)

class AnalyzeVM: ViewModel() {
    private val _state = MutableStateFlow(AnalyzeUiState())
    val state: StateFlow<AnalyzeUiState> = _state

    fun analyzeImage(image: Bitmap) = viewModelScope.launch(Dispatchers.IO) {
        val model = Firebase.ai(backend = GenerativeBackend.googleAI())
            .generativeModel("gemini-2.5-flash")
        // Provide a prompt that contains text
        val prompt = content {
            image(image)
            text("""
                Analyze the disease of the provided image. Give me the result in JSON like the following format. 
                {
                    "diseaseName": "",
                    "description": "",
                    "treatment": ""
                }
                
                Return only valid JSON, no explanations, no extra text
            """.trimIndent())
        }

        _state.update {
            it.copy(
                apiResp = UiState.Loading
            )
        }
// To generate text output, call generateContent with the text input
        try {
            val json = Json { ignoreUnknownKeys = true }
            val response = model.generateContent(prompt)
            val cleaned = cleanGeminiJson(response.text ?: "{}")
            val jsonResp = json.decodeFromString<DiseaseResult>(cleaned)
            _state.update {
                it.copy(
                    apiResp = UiState.Success(DiseaseResult(
                        diseaseName = jsonResp.diseaseName,
                        description = jsonResp.description,
                        treatment = jsonResp.treatment
                    ))
                )
            }
            println("GEMINI RESPONSE = ${response.text}")
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    apiResp = UiState.Error(e.message)
                )
            }
        }
    }

    fun cleanGeminiJson(raw: String): String {
        return raw
            .replace("```json", "")
            .replace("```", "")
            .trim()
    }
}