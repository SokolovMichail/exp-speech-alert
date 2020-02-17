package ru.cib.expspeechalert.SpeechSynthesiser

import org.springframework.stereotype.Component

// Java code to convert text to speech

import java.util.Locale;
import javax.annotation.PostConstruct
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.SynthesizerProperties
import com.sun.swing.internal.plaf.synth.resources.synth
import com.sun.speech.freetts.audio.SingleFileAudioPlayer
import javax.sound.sampled.AudioFileFormat


@Component
class SpeechHook {

    lateinit var synthesizer : Synthesizer
    //lateinit var props :SynthesizerProperties

    @PostConstruct
    fun initialize()
    {
        System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.ru.ru" + ".cmu_us_kal.KevinVoiceDirectory")
        // Register Engine
        Central.registerEngineCentral(
                "com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral")
        // Create a Synthesizer
        synthesizer = Central.createSynthesizer(
                SynthesizerModeDesc(Locale.US))
        // Allocate synthesizer
        synthesizer.allocate()

        // Resume Synthesizer
        synthesizer.resume()

    }

    fun generate_server_name(text: String,path:String) {
        val desc = synthesizer.engineModeDesc as SynthesizerModeDesc
        val jsapiVoices = desc.getVoices()
        val jsapiVoice = jsapiVoices[0]

        /* Non-JSAPI modification of voice audio player
     */

            val freettsVoice = (jsapiVoice as com.sun.speech.freetts.jsapi.FreeTTSVoice).voice
            freettsVoice.audioPlayer = SingleFileAudioPlayer(path, AudioFileFormat.Type.WAVE)

        for (chr in text)
        {
            freettsVoice.speak(chr.toString())
            print(1)
        }
        freettsVoice.audioPlayer.close()

    }

    fun generate_message(text: String,path:String) {
        val desc = synthesizer.engineModeDesc as SynthesizerModeDesc
        val jsapiVoices = desc.getVoices()
        val jsapiVoice = jsapiVoices[0]

        /* Non-JSAPI modification of voice audio player
     */

        val freettsVoice = (jsapiVoice as com.sun.speech.freetts.jsapi.FreeTTSVoice).voice
        freettsVoice.audioPlayer = SingleFileAudioPlayer(path, AudioFileFormat.Type.WAVE)

        freettsVoice.speak(text)
        freettsVoice.audioPlayer.close()

    }
}