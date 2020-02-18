package ru.cib.expspeechalert.SpeechSynthesiser

import com.sun.speech.freetts.audio.SingleFileAudioPlayer
import org.springframework.stereotype.Component

// Java code to convert text to speech

import java.util.Locale;
import javax.annotation.PostConstruct
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.SynthesizerProperties
import com.sun.swing.internal.plaf.synth.resources.synth
import java.nio.file.Paths
import java.nio.file.Path

import javax.sound.sampled.AudioFileFormat


@Component
class SpeechHook {

    lateinit var synthesizer : Synthesizer
    val milCodeMap = mapOf<Char,String>(
            'a' to "Alpha",
            'b' to "Bravo",
            'c' to "Charlie",
            'd' to "Delta",
            'e' to  "Echo",
            'f' to "Foxtrot",
            'g' to "Golf",
            'h' to "Hotel",
            'i' to "India",
            'j' to "Juliet",
            'k' to "Kilo",
            'l' to "Lima",
            'm' to "Mike",
            'n' to "November",
            'o' to "Oscar",
            'p' to "Papa",
            'q' to "Quebec",
            'r' to "Romeo",
            's' to "Sierra",
            't' to "Tango",
            'u' to "Uniform",
            'v' to "Victor",
            'w' to "Whiskey",
            'x' to "X-ray",
            'z' to "Zulu"


    )
    //lateinit var props :SynthesizerProperties

    @PostConstruct
    fun initialize()
    {
        System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory")
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
        val path_tmp = Paths.get(path,text)
        /* Non-JSAPI modification of voice audio player
     */

        val freettsVoice = (jsapiVoice as com.sun.speech.freetts.jsapi.FreeTTSVoice).voice
        freettsVoice.audioPlayer = SingleFileAudioPlayer(path_tmp.toString(), AudioFileFormat.Type.WAVE)
        print(path_tmp.toString())
        for (chr in text)
        {
            freettsVoice.speak(chr.toString())
            //print(1)
        }
        freettsVoice.audioPlayer.close()

    }

    fun generate_message(text: String,path:String) {
        val desc = synthesizer.engineModeDesc as SynthesizerModeDesc
        val jsapiVoices = desc.getVoices()
        val jsapiVoice = jsapiVoices[0]
        val path_tmp = Paths.get(path,"message.wav")

        /* Non-JSAPI modification of voice audio player
     */

        val freettsVoice = (jsapiVoice as com.sun.speech.freetts.jsapi.FreeTTSVoice).voice
        freettsVoice.audioPlayer = SingleFileAudioPlayer(path, AudioFileFormat.Type.WAVE)

        freettsVoice.speak(text)
        freettsVoice.audioPlayer.close()

    }

    fun generate_server_name_mil(text: String,path:String) {
        val desc = synthesizer.engineModeDesc as SynthesizerModeDesc
        val jsapiVoices = desc.getVoices()
        val jsapiVoice = jsapiVoices[0]
        val path_tmp = Paths.get(path,text)
        /* Non-JSAPI modification of voice audio player
     */

        val freettsVoice = (jsapiVoice as com.sun.speech.freetts.jsapi.FreeTTSVoice).voice
        freettsVoice.audioPlayer = SingleFileAudioPlayer(path_tmp.toString(), AudioFileFormat.Type.WAVE)
        print(path_tmp.toString())
        for (chr in text)
        {
            if (milCodeMap.containsKey(chr))
            {
                freettsVoice.speak(milCodeMap[chr])
            }
            else
            {
                freettsVoice.speak(chr.toString())
            }
            //print(1)
        }
        freettsVoice.audioPlayer.close()

    }
}