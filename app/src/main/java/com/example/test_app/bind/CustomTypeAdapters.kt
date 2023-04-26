package com.example.test_app.bind

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken

class MutableStateFloatAdapter : TypeAdapter<MutableState<Float>>(){
    override fun write(out: com.google.gson.stream.JsonWriter?, value: MutableState<Float>?) {
        if (value == null){
            out?.nullValue()
        }else{
            out?.value(value.value)
        }
    }

    override fun read(jsonReader: JsonReader): MutableState<Float>?{
        if(jsonReader.peek() == JsonToken.NULL){
            jsonReader.nextNull()
            return null
        }
        return mutableStateOf(
            jsonReader.nextDouble().toFloat()
        )
    }
}

//class MutableStateMapAdapter:TypeAdapter<>